package com.mallcai.itemcenter.utils;

import com.google.common.base.Splitter;

/**
 * Splitters
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019-07-09 13:51<br/>
 */
public class Splitters {
    public static final Splitter DOT = Splitter.on(".").omitEmptyStrings().trimResults();
    public static final Splitter COMMA = Splitter.on(",").omitEmptyStrings().trimResults();
    public static final Splitter COLON = Splitter.on(":").omitEmptyStrings().trimResults();
    public static final Splitter SEMCOL = Splitter.on(";").omitEmptyStrings().trimResults();
    public static final Splitter AT = Splitter.on("@").omitEmptyStrings().trimResults();
    public static final Splitter SLASH = Splitter.on("/").omitEmptyStrings().trimResults();
    public static final Splitter SPACE = Splitter.on(" ").omitEmptyStrings().trimResults();
    public static final Splitter UNDERSCORE = Splitter.on("_").omitEmptyStrings().trimResults();
}
