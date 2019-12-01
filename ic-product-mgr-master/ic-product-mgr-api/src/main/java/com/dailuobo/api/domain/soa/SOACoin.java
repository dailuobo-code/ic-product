package com.dailuobo.api.domain.soa;

import java.io.Serializable;

/**
 * Created by Aministartor on 2016/12/14.
 */
public class SOACoin implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 9137103496895067100L;
	private Integer coin;
    private Integer balance;
    private String record;

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
}
