package com.dailuobo.api.domain.soa;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: gaodong
 * @Date: 2018/11/14 10:55
 * @Description:检测数据库的事务实体类
 */
public class CheckDBTxcDto implements Serializable {
    private static final long serialVersionUID = 1;
  //事务ID
  private Long trxId;
    //事务状态
  private String trxState;
    //事务需要等待的资源
  private String trxRequestedLockId;
    //事务开始时间
  private Date trxStarted;
    //事务使用表
  private Integer trxTablesInUse;
    //事务拥有锁
  private Integer trxTablesLocked;
    //事务锁定行
  private Integer trxRowsLocked;
    //事务更改行
  private Integer trxRowsModified;

    public Long getTrxId() {
        return trxId;
    }

    public void setTrxId(Long trxId) {
        this.trxId = trxId;
    }

    public String getTrxState() {
        return trxState;
    }

    public void setTrxState(String trxState) {
        this.trxState = trxState;
    }

    public String getTrxRequestedLockId() {
        return trxRequestedLockId;
    }

    public void setTrxRequestedLockId(String trxRequestedLockId) {
        this.trxRequestedLockId = trxRequestedLockId;
    }

    public Date getTrxStarted() {
        return trxStarted;
    }

    public void setTrxWaitStarted(Date trxStarted) {
        this.trxStarted = trxStarted;
    }

    public Integer getTrxTablesInUse() {
        return trxTablesInUse;
    }

    public void setTrxTablesInUse(Integer trxTablesInUse) {
        this.trxTablesInUse = trxTablesInUse;
    }

    public Integer getTrxTablesLocked() {
        return trxTablesLocked;
    }

    public void setTrxTablesLocked(Integer trxTablesLocked) {
        this.trxTablesLocked = trxTablesLocked;
    }

    public Integer getTrxRowsLocked() {
        return trxRowsLocked;
    }

    public void setTrxRowsLocked(Integer trxRowsLocked) {
        this.trxRowsLocked = trxRowsLocked;
    }

    public Integer getTrxRowsModified() {
        return trxRowsModified;
    }

    public void setTrxRowsModified(Integer trxRowsModified) {
        this.trxRowsModified = trxRowsModified;
    }
}
