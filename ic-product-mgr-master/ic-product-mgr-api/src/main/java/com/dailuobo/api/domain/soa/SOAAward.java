package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOAAward implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5963826889922299750L;
	
	/**
	 * 奖品id
	 */
	private Integer id;

	/**
	 * 奖品名称
	 */
    private String prizeName;

    /**
     * 奖品icon
     */
    private String prizeIcon;

    /**
     * 奖品类型
     */
    private Integer prizeType;
    
    /**
     * 中奖奖品总数量
     */
    private Integer num;
    
    /**
     * 是否合成的实物 
     */
    private Integer compose;

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public String getPrizeIcon() {
		return prizeIcon;
	}

	public void setPrizeIcon(String prizeIcon) {
		this.prizeIcon = prizeIcon;
	}

	public Integer getPrizeType() {
		return prizeType;
	}

	public void setPrizeType(Integer prizeType) {
		this.prizeType = prizeType;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCompose() {
		return compose;
	}

	public void setCompose(Integer compose) {
		this.compose = compose;
	}
    
}