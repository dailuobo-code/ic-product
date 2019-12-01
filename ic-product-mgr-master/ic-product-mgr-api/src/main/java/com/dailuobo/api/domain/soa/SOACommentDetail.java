package com.dailuobo.api.domain.soa;

import java.io.Serializable;
import java.util.List;

public class SOACommentDetail implements Serializable {
	private static final long serialVersionUID = -223180993107394613L;
	private String evaluateContent;
	private String evaluateUrls;
	private String replyContent;
	private String replyUrls;
	private String evaluateTime;
	private String replyTime;

	public String getEvaluateTime() {
		return evaluateTime;
	}

	public void setEvaluateTime(String evaluateTime) {
		this.evaluateTime = evaluateTime;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	private Integer score;
	
	private List<SOAOrderDetail> details;

	/**
	 * 获取  evaluateContent
	 * @return evaluateContent
	 */
	public String getEvaluateContent() {
		return evaluateContent;
	}

	/**
	 * 设置 evaluateContent
	 * @param evaluateContent
	 */
	public void setEvaluateContent(String evaluateContent) {
		this.evaluateContent = evaluateContent;
	}

	/**
	 * 获取  evaluateUrls
	 * @return evaluateUrls
	 */
	public String getEvaluateUrls() {
		return evaluateUrls;
	}

	/**
	 * 设置 evaluateUrls
	 * @param evaluateUrls
	 */
	public void setEvaluateUrls(String evaluateUrls) {
		this.evaluateUrls = evaluateUrls;
	}

	/**
	 * 获取  replyContent
	 * @return replyContent
	 */
	public String getReplyContent() {
		return replyContent;
	}

	/**
	 * 设置 replyContent
	 * @param replyContent
	 */
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	/**
	 * 获取  replyUrls
	 * @return replyUrls
	 */
	public String getReplyUrls() {
		return replyUrls;
	}

	/**
	 * 设置 replyUrls
	 * @param replyUrls
	 */
	public void setReplyUrls(String replyUrls) {
		this.replyUrls = replyUrls;
	}

	/**
	 * 获取  details
	 * @return details
	 */
	public List<SOAOrderDetail> getDetails() {
		return details;
	}

	/**
	 * 设置 details
	 * @param details
	 */
	public void setDetails(List<SOAOrderDetail> details) {
		this.details = details;
	}
}
