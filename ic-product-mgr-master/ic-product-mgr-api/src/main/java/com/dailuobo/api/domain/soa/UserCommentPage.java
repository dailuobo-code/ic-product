package com.dailuobo.api.domain.soa;

public class UserCommentPage extends SOAStandardPage<SOAOrderDetailVO> {
	private static final long serialVersionUID = -4613593794678217746L;
	private Integer commentCount;

	/**
	 * 获取  commentCount
	 * @return commentCount
	 */
	public Integer getCommentCount() {
		return commentCount;
	}

	/**
	 * 设置 commentCount
	 * @param commentCount
	 */
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
}
