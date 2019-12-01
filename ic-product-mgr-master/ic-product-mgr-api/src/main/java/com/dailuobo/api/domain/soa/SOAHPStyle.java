package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOAHPStyle implements Serializable {

	private static final long serialVersionUID = -8131347257803372891L;
	
	private Integer id;
	private Integer cityId;
	private Integer storeId;
	private String name;
	private String topRgb;// 首页顶部背景色
	private String tileBg;// 磁贴区域背景图片
	private String menuBg;// 底部菜单背景图片
	private String btnHomeFocusBg;//首页按钮获得焦点时背景图片
	private String btnHomeBlurBg;//首页按钮失去焦点时背景图片
	private String btnCategoryFocusBg;//分类按钮获得焦点时背景图片
	private String btnCategoryBlurBg;//分类按钮失去焦点时背景图片
	private String btnCartFocusBg;//购物车按钮获得焦点时背景图片
	private String btnCartBlurBg;//购物车按钮失去焦点时背景图片
	private String btnPickupFocusBg;//取货按钮获得焦点时背景图片
	private String btnPickupBlurBg;//取货按钮失去焦点时背景图片
	private String btnAboutmeFocusBg;//我的按钮获得焦点时背景图片
	private String btnAboutmeBlurBg;//我的按钮失去焦点时背景图片
	private String createTime;
	private String updateTime;
	
	/**
	 * 获取  id
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置 id
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取  cityId
	 * @return cityId
	 */
	public Integer getCityId() {
		return cityId;
	}
	/**
	 * 设置 cityId
	 * @param cityId
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	/**
	 * 获取  storeId
	 * @return storeId
	 */
	public Integer getStoreId() {
		return storeId;
	}
	/**
	 * 设置 storeId
	 * @param storeId
	 */
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	/**
	 * 获取  name
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置 name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取  topRgb
	 * @return topRgb
	 */
	public String getTopRgb() {
		return topRgb;
	}
	/**
	 * 设置 topRgb
	 * @param topRgb
	 */
	public void setTopRgb(String topRgb) {
		this.topRgb = topRgb;
	}
	/**
	 * 获取  tileBg
	 * @return tileBg
	 */
	public String getTileBg() {
		return tileBg;
	}
	/**
	 * 设置 tileBg
	 * @param tileBg
	 */
	public void setTileBg(String tileBg) {
		this.tileBg = tileBg;
	}
	/**
	 * 获取  menuBg
	 * @return menuBg
	 */
	public String getMenuBg() {
		return menuBg;
	}
	/**
	 * 设置 menuBg
	 * @param menuBg
	 */
	public void setMenuBg(String menuBg) {
		this.menuBg = menuBg;
	}
	/**
	 * 获取  btnHomeFocusBg
	 * @return btnHomeFocusBg
	 */
	public String getBtnHomeFocusBg() {
		return btnHomeFocusBg;
	}
	/**
	 * 设置 btnHomeFocusBg
	 * @param btnHomeFocusBg
	 */
	public void setBtnHomeFocusBg(String btnHomeFocusBg) {
		this.btnHomeFocusBg = btnHomeFocusBg;
	}
	/**
	 * 获取  btnHomeBlurBg
	 * @return btnHomeBlurBg
	 */
	public String getBtnHomeBlurBg() {
		return btnHomeBlurBg;
	}
	/**
	 * 设置 btnHomeBlurBg
	 * @param btnHomeBlurBg
	 */
	public void setBtnHomeBlurBg(String btnHomeBlurBg) {
		this.btnHomeBlurBg = btnHomeBlurBg;
	}
	/**
	 * 获取  btnCategoryFocusBg
	 * @return btnCategoryFocusBg
	 */
	public String getBtnCategoryFocusBg() {
		return btnCategoryFocusBg;
	}
	/**
	 * 设置 btnCategoryFocusBg
	 * @param btnCategoryFocusBg
	 */
	public void setBtnCategoryFocusBg(String btnCategoryFocusBg) {
		this.btnCategoryFocusBg = btnCategoryFocusBg;
	}
	/**
	 * 获取  btnCategoryBlurBg
	 * @return btnCategoryBlurBg
	 */
	public String getBtnCategoryBlurBg() {
		return btnCategoryBlurBg;
	}
	/**
	 * 设置 btnCategoryBlurBg
	 * @param btnCategoryBlurBg
	 */
	public void setBtnCategoryBlurBg(String btnCategoryBlurBg) {
		this.btnCategoryBlurBg = btnCategoryBlurBg;
	}
	/**
	 * 获取  btnCartFocusBg
	 * @return btnCartFocusBg
	 */
	public String getBtnCartFocusBg() {
		return btnCartFocusBg;
	}
	/**
	 * 设置 btnCartFocusBg
	 * @param btnCartFocusBg
	 */
	public void setBtnCartFocusBg(String btnCartFocusBg) {
		this.btnCartFocusBg = btnCartFocusBg;
	}
	/**
	 * 获取  btnCartBlurBg
	 * @return btnCartBlurBg
	 */
	public String getBtnCartBlurBg() {
		return btnCartBlurBg;
	}
	/**
	 * 设置 btnCartBlurBg
	 * @param btnCartBlurBg
	 */
	public void setBtnCartBlurBg(String btnCartBlurBg) {
		this.btnCartBlurBg = btnCartBlurBg;
	}
	/**
	 * 获取  btnPickupFocusBg
	 * @return btnPickupFocusBg
	 */
	public String getBtnPickupFocusBg() {
		return btnPickupFocusBg;
	}
	/**
	 * 设置 btnPickupFocusBg
	 * @param btnPickupFocusBg
	 */
	public void setBtnPickupFocusBg(String btnPickupFocusBg) {
		this.btnPickupFocusBg = btnPickupFocusBg;
	}
	/**
	 * 获取  btnPickupBlurBg
	 * @return btnPickupBlurBg
	 */
	public String getBtnPickupBlurBg() {
		return btnPickupBlurBg;
	}
	/**
	 * 设置 btnPickupBlurBg
	 * @param btnPickupBlurBg
	 */
	public void setBtnPickupBlurBg(String btnPickupBlurBg) {
		this.btnPickupBlurBg = btnPickupBlurBg;
	}
	/**
	 * 获取  btnAboutmeFocusBg
	 * @return btnAboutmeFocusBg
	 */
	public String getBtnAboutmeFocusBg() {
		return btnAboutmeFocusBg;
	}
	/**
	 * 设置 btnAboutmeFocusBg
	 * @param btnAboutmeFocusBg
	 */
	public void setBtnAboutmeFocusBg(String btnAboutmeFocusBg) {
		this.btnAboutmeFocusBg = btnAboutmeFocusBg;
	}
	/**
	 * 获取  btnAboutmeBlurBg
	 * @return btnAboutmeBlurBg
	 */
	public String getBtnAboutmeBlurBg() {
		return btnAboutmeBlurBg;
	}
	/**
	 * 设置 btnAboutmeBlurBg
	 * @param btnAboutmeBlurBg
	 */
	public void setBtnAboutmeBlurBg(String btnAboutmeBlurBg) {
		this.btnAboutmeBlurBg = btnAboutmeBlurBg;
	}
	/**
	 * 获取  createTime
	 * @return createTime
	 */
	public String getCreateTime() {
		return createTime;
	}
	/**
	 * 设置 createTime
	 * @param createTime
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取  updateTime
	 * @return updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置 updateTime
	 * @param updateTime
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
