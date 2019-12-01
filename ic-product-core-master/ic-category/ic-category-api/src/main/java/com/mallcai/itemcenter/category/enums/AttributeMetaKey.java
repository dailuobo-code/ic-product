package com.mallcai.itemcenter.category.enums;

import com.google.common.primitives.Doubles;
import com.mallcai.itemcenter.utils.DateUtil;
import com.mallcai.itemcenter.utils.Splitters;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * AttributeMetaKey
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/10 14:51<br/>
 */
public enum AttributeMetaKey {


    REQUIRED { //是否必填

        @Override
        public boolean validate(String attrVal, String metaValue, List<String> context) {
            //如果metaValue为空, 或者非必填, 则返回真
            if (!StringUtils.hasText(metaValue) || !Boolean.parseBoolean(metaValue)) {
                return true;
            }
            return StringUtils.hasText(attrVal);
        }
    },

    IMPORTANT { //是否关键属性

        @Override
        public boolean validate(String attrVal, String metaValue, List<String> context) {
            //如果metaValue为空, 或者非必填, 则返回真
            if (!StringUtils.hasText(metaValue) || !Boolean.valueOf(metaValue)) {
                return true;
            }
            return StringUtils.hasText(attrVal); //关键属性也是必填属性
        }
    },

    VALUE_TYPE { //允许的值类型

        @Override
        public boolean validate(String attrVal, String metaValue, List<String> context) {
            if (!StringUtils.hasText(attrVal)) { // 如果值为空, 则应该交给REQUIRED来处理
                return true;
            }
            //如果没有设置允许的值类型, 则返回真
            if (!StringUtils.hasText(metaValue)) {
                return true;
            }
            metaValue = Splitters.UNDERSCORE.splitToList(metaValue).get(0);
            switch (metaValue) {
                case "STRING":
                    return true;
                case "NUMBER":
                    return Doubles.tryParse(attrVal) != null;
                case "DATE":
                    return DateUtil.isValidDate(attrVal);
                default:
                    return false;
            }
        }
    },

    //是否可用作销售属性
    SKU_CANDIDATE {

        @Override
        public boolean validate(String attrVal, String metaValue, List<String> context) {
            return true;
        }
    },

    //是否在商品详情的sku区展示该属性
    PSEUDO_SKU_CANDIDATE {

        @Override
        public boolean validate(String attrVal, String metaValue, List<String> context) {
            return true;
        }
    },

    //该属性是否进搜索引擎, 对于属性校验而言， 应该都是有效的
    SEARCHABLE {

        @Override
        public boolean validate(String attrVal, String metaValue, List<String> context) {
            return true;
        }

    },

    USER_DEFINED { //是否允许用户自定义

        @Override
        public boolean validate(String attrVal, String metaValue, List<String> context) {
            if (!StringUtils.hasText(attrVal)) { // 如果值为空, 则应该交给REQUIRED来处理
                return true;
            }
            //如果metaValue为空, 或者允许用户自定义, 则返回true
            if (!StringUtils.hasText(metaValue) || Boolean.valueOf(metaValue)) {
                return true;
            } else { //如果不允许用户自定义, 则校验是否处于允许范围
                return context.contains(attrVal);
            }
        }
    };


    /**
     * 检查是否输入的属性值是否满足规则
     *
     * @param attrVal   待校验的属性值
     * @param metaValue 属性规则的元信息中的值
     * @param context   允许的值范围
     * @return 是否满足需求
     */
    public abstract boolean validate(String attrVal, String metaValue, List<String> context);


}
