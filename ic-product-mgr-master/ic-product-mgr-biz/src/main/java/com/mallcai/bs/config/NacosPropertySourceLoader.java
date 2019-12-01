package com.mallcai.bs.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.util.StringUtils;
import org.springframework.util.StringValueResolver;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


/**
 * @Author apple
 * @Date 2019-07-10 02:18
 * @Description
 */
public class NacosPropertySourceLoader extends PropertyPlaceholderConfigurer {

    private int systemPropertiesMode = SYSTEM_PROPERTIES_MODE_FALLBACK;
    private static final String JSON_TYPE = "json";
    private static final String PROPERTIES = "properties";
    private static final String YAML = "yaml";
    private static final String TEXT = "text";

    private String host = "";
    private String cityId = "";

    private String currentLoadFile="";

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
            throws BeansException {
        loadNacosProperties(props);
        StringValueResolver valueResolver = new NacosPropertySourceLoader.PlaceholderResolvingStringValueResolver(props);
        doProcessProperties(beanFactoryToProcess, valueResolver);
    }

    private void loadNacosProperties(Properties properties) throws BeanInitializationException {
        if (properties.size() == 0) {
            return;
        }
        if (!properties.isEmpty()) {
            host = properties.getProperty("config.host");
            cityId = properties.getProperty("Service.CityId");
        }
        if (!StringUtils.isEmpty(System.getProperty("Service.CityId"))) {
            cityId = System.getProperty("Service.CityId");
        }
        if (!StringUtils.isEmpty(System.getProperty("config.host"))) {
            host = System.getProperty("config.host");
        }
        if (StringUtils.isEmpty(host)) {
            throw new BeanInitializationException("配置中心服务地址不正确host:"+host);
        }
        Map<String, Object> source = new HashMap<>(64);
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            String originKey = (String) entry.getKey();
            if ("config.host".equals(originKey) || "Service.CityId".equals(originKey)) {
                continue;
            }
            String originValue = (String) entry.getValue();
            if (originValue.contains("${Service.CityId}")) {
                originValue = replaceCityId(originValue, cityId);
            }
            currentLoadFile = originValue;
            String[] originValueArr = originValue.split("/");
            JSONObject jsonObject = getContentType(originValueArr[3], originValueArr[2], originValueArr[1]);
            if (jsonObject == null) {
                throw new BeanInitializationException("未获取到远程配置中心配置,请检查" + "namespace:" + originValueArr[1] + ",group:" + originValueArr[2] + ",dataId:" + originValueArr[3]);
            }
            String configInfo = jsonObject.getString("content");
            if (StringUtils.isEmpty(configInfo)) {
                throw new BeanInitializationException("未获取到远程配置中心配置,请检查" + "namespace:" + originValueArr[1] + ",group:" + originValueArr[2] + ",dataId:" + originValueArr[3]);
            }
            if (TEXT.equals(jsonObject.getString("type"))) {
                source.put((String) entry.getKey(), configInfo);
            } else if (PROPERTIES.equals(jsonObject.getString("type"))) {
                parseProperties(source, configInfo);
            } else if (YAML.equals(jsonObject.getString("type"))) {
                parseYml(source, configInfo);
            } else {
                parseJson(source, configInfo);
            }
        }

        if (!source.isEmpty()) {
            for (Map.Entry<String, Object> entry : source.entrySet()) {
                String value = entry.getValue().toString();
                if (value.contains("${Service.CityId}")) {
                    value = replaceCityId(value, cityId);
                }
                properties.setProperty(entry.getKey(), value);

            }
            properties.setProperty("Service.CityId", cityId);
        }
    }


    private JSONObject getContentType(String dataId, String group, String tenant) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String url = host + "/nacos/v1/cs/configs/getAllInfo";
        StringBuilder params;
        params = new StringBuilder();
        params.append("?dataId=").append(dataId).append("&group=").append(group).append("&tenant=").append(tenant);
        logger.info("get config url :" + url);
        HttpGet httpGet = new HttpGet(url + params);
        CloseableHttpResponse response = null;
        JSONObject jsonObject = null;
        try {
            response = httpClient.execute(httpGet);
            HttpEntity responseEntity = response.getEntity();
            if (null != responseEntity) {
                String result = EntityUtils.toString(responseEntity);
                if (null != result) {
                    jsonObject = JSON.parseObject(result);
                }
            }
        } catch (Exception e) {
            logger.error("get contentType error,errorMessage", e);
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.error("close httpClient/response error,errorMessage", e);
            }
        }
        return jsonObject;
    }

    private void parseProperties(Map<String, Object> result, String content) throws BeanInitializationException {
        Properties properties = new Properties();
        InputStream inputStream = new ByteArrayInputStream(content.getBytes());
        try {
            properties.load(inputStream);
        }catch (IOException e){
            throw  new BeanInitializationException("配置文件加载失败, content:"+content);
        }
        if(!properties.isEmpty()){
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                if (result.containsKey(entry.getKey())) {
                    throw new BeanInitializationException("配置中存在相同的key,请仔细检查,谢谢, key:"+entry.getKey()+",loadConfigPath:"+currentLoadFile);
                }
                result.put((String) entry.getKey(), entry.getValue());
            }
        }
    }

    private void parseJson(Map<String, Object> result, String content) throws BeanInitializationException {
        JSONObject jsonObject = JSON.parseObject(content);
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            if (result.containsKey(entry.getKey())) {
                throw new BeanInitializationException("配置中存在相同的key,请仔细检查,谢谢, key:"+entry.getKey()+",loadConfigPath:"+currentLoadFile);
            }
            result.putAll(jsonObject);
        }

    }

    private void parseYml(Map<String, Object> result, String content) throws BeanInitializationException {
        LoaderOptions options = new LoaderOptions();
        options.setAllowDuplicateKeys(false);
        Yaml yaml = new Yaml(options);
        InputStream inputStream = new ByteArrayInputStream(content.getBytes());
        Map<String, Object> source = yaml.load(inputStream);
        nestMapParse("", result, source);
    }

    private void nestMapParse(String prefix, Map<String, Object> result, Map<String, Object> map) throws BeanInitializationException {
        if (prefix.length() > 0) {
            prefix += ".";
        }
        for (Map.Entry entrySet : map.entrySet()) {
            if (entrySet.getValue() instanceof Map) {
                nestMapParse(prefix + entrySet.getKey(), result, (Map<String, Object>) entrySet.getValue());
            } else {
                String key = prefix + entrySet.getKey().toString();
                if (result.containsKey(key)) {
                    throw new BeanInitializationException("配置中存在相同的key,请仔细检查,谢谢, key:"+key+",loadConfigPath:"+currentLoadFile);
                }
                result.put(key, entrySet.getValue());
            }
        }
    }

    private static String replaceCityId(String originalString, String realValue) {
        if (StringUtils.isEmpty(originalString)) {
            return originalString;
        }
        if (!originalString.contains("$")) {
            return originalString;
        }
        String[] array = originalString.split("\\$");
        String subString;
        StringBuilder stringBuffer = new StringBuilder();
        for (String s : array) {
            if (!s.contains("{") || !s.contains("}")) {
                stringBuffer.append(s);
            } else {
                int beginIndex = s.indexOf("{");
                int endIndex = s.indexOf("}");
                String replacedString = s.substring(beginIndex + 1, endIndex);
                subString = s.substring(0, beginIndex) + getReplaceValue(replacedString, realValue) + s.substring(endIndex + 1);
                stringBuffer.append(subString);
            }
        }
        return stringBuffer.toString();
    }

    private static String getReplaceValue(String replacedString, String realValue) {
        if ("Service.CityId".equalsIgnoreCase(replacedString)) {
            return realValue;
        } else {
            return "";
        }
    }


    private class PlaceholderResolvingStringValueResolver implements StringValueResolver {

        private final PropertyPlaceholderHelper helper;

        private final PropertyPlaceholderHelper.PlaceholderResolver resolver;

        public PlaceholderResolvingStringValueResolver(Properties props) {
            this.helper = new PropertyPlaceholderHelper(
                    placeholderPrefix, placeholderSuffix, valueSeparator, ignoreUnresolvablePlaceholders);
            this.resolver = new NacosPropertySourceLoader.PropertyPlaceholderConfigurerResolver(props);
        }

        @Override
        public String resolveStringValue(String strVal) throws BeansException {
            String resolved = this.helper.replacePlaceholders(strVal, this.resolver);
            if (trimValues) {
                resolved = resolved.trim();
            }
            return (resolved.equals(nullValue) ? null : resolved);
        }
    }


    private class PropertyPlaceholderConfigurerResolver implements PropertyPlaceholderHelper.PlaceholderResolver {

        private final Properties props;

        private PropertyPlaceholderConfigurerResolver(Properties props) {
            this.props = props;
        }

        @Override
        public String resolvePlaceholder(String placeholderName) {
            return NacosPropertySourceLoader.this.resolvePlaceholder(placeholderName, props, systemPropertiesMode);
        }
    }

}
