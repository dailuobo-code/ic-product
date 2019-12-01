package com.mallcai.itemcenter.base.model;

/**
 * CoreItemSymbol
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/24 22:09<br/>
 */
public interface CoreItemWriteSymbol extends CoreDomainSymbol {
    void setTax(String tax);
    void setTaxCode(String taxCode);
}
