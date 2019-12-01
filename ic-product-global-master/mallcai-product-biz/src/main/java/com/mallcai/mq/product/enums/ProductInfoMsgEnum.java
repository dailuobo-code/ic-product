package com.mallcai.mq.product.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Benson
 * @projectName app-admin
 * @packageName com.mallcai.bs.mq.product.enums
 * @description TODO
 * @date 2019-06-17 10:14
 */

@Getter
@AllArgsConstructor
public enum ProductInfoMsgEnum {
	
	add(1, "新增"), edit(2, "修改");
	private Integer code;
	private String desc;
}
