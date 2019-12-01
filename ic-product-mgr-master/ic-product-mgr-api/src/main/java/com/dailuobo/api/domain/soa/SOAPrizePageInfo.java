package com.dailuobo.api.domain.soa;

import java.io.Serializable;
import java.util.List;

public class SOAPrizePageInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6572519795191264636L;
	
	private String nickname;
	
	private int points;
	
	private List<SOAPrize> pirzes;
	
	private SOAAddress address;
	
	/**
	 * 单次抽奖消费积分数量
	 */
	private int costOne;
	
	/**
	 * 多连抽消耗积分数量
	 */
	private int costMore;
	
	/**
	 * 多连抽次数
	 */
	private int num;
	
	private long startTime;
	
	private long endTime;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public List<SOAPrize> getPirzes() {
		return pirzes;
	}

	public void setPirzes(List<SOAPrize> pirzes) {
		this.pirzes = pirzes;
	}

	public SOAAddress getAddress() {
		return address;
	}

	public void setAddress(SOAAddress address) {
		this.address = address;
	}

	public int getCostOne() {
		return costOne;
	}

	public void setCostOne(int costOne) {
		this.costOne = costOne;
	}

	public int getCostMore() {
		return costMore;
	}

	public void setCostMore(int costMore) {
		this.costMore = costMore;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

}
