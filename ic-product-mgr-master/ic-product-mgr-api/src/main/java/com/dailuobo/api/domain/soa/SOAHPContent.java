package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOAHPContent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3694447145607615309L;

	/**
	 * 主键
	 */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 副标题
     */
    private String subTitle;

    /**
     * 关键字，多个以半角逗号分隔
     */
    private String keyWord;

    /**
     * 内容
     */
    private String content;
    
    /**
     * 访问量，默认为0
     */
    private Integer visitCount;

    /**
     * 是否删除标记，0-正常，1-已删除
     */
    private String isDelete;
    
    /**
     * 创建时间
     */
    private long createTime;
    
    /**
     * 修改时间
     */
    private long updateTime;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle == null ? null : subTitle.trim();
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	
}
