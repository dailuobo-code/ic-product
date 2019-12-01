package com.mallcai.bs.service;

import com.aliyun.oss.OSSClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Configuration
public class OssService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // endpoint是访问OSS的域名。如果您已经在OSS的控制台上 创建了Bucket，请在控制台上查看域名。
    // 如果您还没有创建Bucket，endpoint选择请参看文档中心的“开发人员指南 > 基本概念 > 访问域名”，
    // 链接地址是：https://help.aliyun.com/document_detail/oss/user_guide/oss_concept/endpoint.html?spm=5176.docoss/user_guide/endpoint_region
    // endpoint的格式形如“http://oss-cn-hangzhou.aliyuncs.com/”，注意http://后不带bucket名称，
    // 比如“http://bucket-name.oss-cn-hangzhou.aliyuncs.com”，是错误的endpoint，请去掉其中的“bucket-name”。
    private  String endpoint;

    // accessKeyId和accessKeySecret是OSS的访问密钥，您可以在控制台上创建和查看，
    // 创建和查看访问密钥的链接地址是：https://ak-console.aliyun.com/#/。
    // 注意：accessKeyId和accessKeySecret前后都没有空格，从控制台复制时请检查并去除多余的空格。
    private   String accessKeyId ;
    private   String accessKeySecret ;

    // Bucket用来管理所存储Object的存储空间，详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
    // Bucket命名规范如下：只能包括小写字母，数字和短横线（-），必须以小写字母或者数字开头，长度必须在3-63字节之间。
    //一个bucketName 对应一个域名
    private  String bucketName;

    //存放文件父路径（测试库为/test   正式库为/dailuobo）
    private   String fileHome;

    private   String fileUrl;

    //存放文件父路径 固定目录(测试库为/testMgr   正式库为/mgr)
    private  String fixedFileHome;

    public  String getFileUrl() {
        return fileUrl;
    }

    @Value("${fileUrl}")
    public  void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public  String getFileHome() {
        return fileHome;
    }

    @Value("${fileHome}")
    public void setFileHome(String fileHome) {
        this.fileHome = fileHome;
    }

    public  String getEndpoint() {
        return endpoint;
    }

    @Value("${endpoint}")
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public  String getAccessKeyId() {
        return accessKeyId;
    }

    @Value("${accessKeyId}")
    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public  String getAccessKeySecret() {
        return accessKeySecret;
    }

    @Value("${accessKeySecret}")
    public  void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public  String getBucketName() {
        return bucketName;
    }

    @Value("${bucketName}")
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getFixedFileHome() {
        return fixedFileHome;
    }

    @Value("${fixedFileHome}")
    public  void setFixedFileHome(String fixedFileHome) {
        this.fixedFileHome = fixedFileHome;
    }

    public String upload( String fileName, InputStream inputStream) {

        try {
            // 创建OSSClient实例。
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

            Calendar calendar = GregorianCalendar.getInstance();
            String year = String.valueOf(calendar.get(Calendar.YEAR));
            String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
            String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

            fileName = fileHome  + year + "/" + month + "/" + day + "/" + fileName;

            //ossClient.putObject(OssService.getBucketName(), "2019/04/11/haha.png", inputStream);
            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSSClient。
            inputStream.close();

            // 关闭OSSClient。
            ossClient.shutdown();
            return fileUrl + "/" + fileName;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 固定目录，不按照时间分文件目录
     * @param fileName
     * @param inputStream
     * @return
     */
    public String uploadToNew( String fileName, InputStream inputStream) {

        try {
            // 创建OSSClient实例。
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

            fileName = fixedFileHome + fileName;

            //ossClient.putObject(OssService.getBucketName(), "2019/04/11/haha.png", inputStream);
            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSSClient。
            inputStream.close();

            // 关闭OSSClient。
            ossClient.shutdown();
            return fileUrl + "/" + fileName;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
