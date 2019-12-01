package com.mallcai.itemcenter.api.request;

import com.mallcai.itemcenter.api.ApiBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * AbstractRequest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/14 10:56<br/>
 */
@Data
public abstract class AbstractRequest extends ApiBean {
    private final long serialVersionUID = -5302743869946505523L;

    @ApiModelProperty(hidden = true)
    private String reqId;
    @ApiModelProperty(hidden = true)
    private String callerIp;
    @ApiModelProperty(hidden = true)
    private String callerType;
    @ApiModelProperty(hidden = true)
    private Map<String, String> extra;
    @ApiModelProperty(hidden = true)
    private Long updatedBy;

    public void checkParam() {}


    public void isBlank(String string, String msg) {
        if (StringUtils.isNotBlank(string)) {
            throw new IllegalArgumentException(msg);
        }
    }

    public void notBlank(String string, String msg) {
        if (StringUtils.isBlank(string)) {
            throw new IllegalArgumentException(msg);
        }
    }

    public void notEmpty(Collection collection, String msg) {
        if (null == collection || collection.isEmpty()) {
            throw new IllegalArgumentException(msg);
        }
    }

    public void nonBlankElements(Collection<String> collection, String elementMsg) {
        Iterator var2 = collection.iterator();

        while(var2.hasNext()) {
            String str = (String)var2.next();
            notBlank(str, elementMsg);
        }

    }

    public void nonNull(Object object, String msg) {
        if (null == object) {
            throw new IllegalArgumentException(msg);
        }
    }

    public void isNull(Object object, String msg) {
        if (null != object) {
            throw new IllegalArgumentException(msg);
        }
    }

    public void expectTrue(boolean boolExpression, String falseMsg) {
        if (!boolExpression) {
            throw new IllegalArgumentException(falseMsg);
        }
    }

    public void expectFalse(boolean boolExpression, String trueMsg) {
        if (boolExpression) {
            throw new IllegalArgumentException(trueMsg);
        }
    }

    public void expectAnyFalse(String msg, Boolean... booleans) throws IllegalArgumentException {
        if (Arrays.stream(booleans).allMatch((t) -> {
            return t;
        })) {
            throw new IllegalArgumentException(msg);
        }
    }

    public void expectInRange(Collection collection, int minElements, int maxElements, String outRangeMsg) {
        int elements = collection.size();
        if (elements < minElements || elements > maxElements) {
            throw new IllegalArgumentException(outRangeMsg);
        }
    }

    public void expectInRange(String string, int minLength, int maxLength, String msg) {
        if (StringUtils.isBlank(string) || string.length() < minLength || string.length() > maxLength) {
            throw new IllegalArgumentException(msg);
        }
    }

    public void expectDateStrWithPattern(String sDate, String pattern, String msg) {
        Date outDate = null;
        if (StringUtils.isBlank(sDate)) {
            throw new IllegalArgumentException(msg);
        } else if (!StringUtils.isBlank(pattern)) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);

            try {
                df.parse(sDate);
            } catch (ParseException var6) {
                throw new IllegalArgumentException(msg);
            }
        }
    }
}
