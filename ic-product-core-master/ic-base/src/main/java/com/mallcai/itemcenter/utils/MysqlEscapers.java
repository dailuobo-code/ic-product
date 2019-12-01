package com.mallcai.itemcenter.utils;

import com.google.common.escape.Escaper;
import com.google.common.escape.Escapers;

public class MysqlEscapers {

    public static Escaper mysqlEscaper() {
        return MYSQL_ESCAPER;
    }

    private static final Escaper MYSQL_ESCAPER =
            Escapers.builder()
                    .addEscape('%', "\\%")
                    .addEscape('_', "\\_")
                    .build();

    private MysqlEscapers() {
    }
}
