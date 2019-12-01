package com.dailuobo.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class City.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class City extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** The city id. */
	private Integer cityId;
	
	/** The city name. */
	private String cityName;
	
	/** The address. */
	private String address;
	
	/** The telephone. */
	private String telephone;
	
	/** The manager. */
	private String manager;
	
	/** The employee num. */
	private Integer employeeNum;
	
	/** The store num. */
	private Integer storeNum;
	
	/** The warehouse num. */
	private Integer warehouseNum;
	
	/** The logistics num. */
	private Integer logisticsNum;
	
	/** The status. */
	private Integer status;
	
	/** The open date. */
	private Date openDate;
	

	/** The logistics num. */
	private Integer isLimit;
	
	/** The open saleName. */
	private String saleName;

	public String getSaleName() {
		return saleName;
	}

	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}

	/**
	 * Instantiates a new city.
	 */
	public City() {
	}

	/**
	 * Instantiates a new city.
	 *
	 * @param cityId the city id
	 */
	public City(Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * Gets the city id.
	 *
	 * @return the city id
	 */
	public Integer getCityId() {
		return cityId;
	}

	/**
	 * Sets the city id.
	 *
	 * @param cityId the new city id
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * Gets the city name.
	 *
	 * @return the city name
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * Sets the city name.
	 *
	 * @param cityName the new city name
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the telephone.
	 *
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * Sets the telephone.
	 *
	 * @param telephone the new telephone
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * Gets the manager.
	 *
	 * @return the manager
	 */
	public String getManager() {
		return manager;
	}

	/**
	 * Sets the manager.
	 *
	 * @param manager the new manager
	 */
	public void setManager(String manager) {
		this.manager = manager;
	}

	/**
	 * Gets the employee num.
	 *
	 * @return the employee num
	 */
	public Integer getEmployeeNum() {
		return employeeNum;
	}

	/**
	 * Sets the employee num.
	 *
	 * @param employeeNum the new employee num
	 */
	public void setEmployeeNum(Integer employeeNum) {
		this.employeeNum = employeeNum;
	}

	/**
	 * Gets the store num.
	 *
	 * @return the store num
	 */
	public Integer getStoreNum() {
		return storeNum;
	}

	/**
	 * Sets the store num.
	 *
	 * @param storeNum the new store num
	 */
	public void setStoreNum(Integer storeNum) {
		this.storeNum = storeNum;
	}

	/**
	 * Gets the warehouse num.
	 *
	 * @return the warehouse num
	 */
	public Integer getWarehouseNum() {
		return warehouseNum;
	}

	/**
	 * Sets the warehouse num.
	 *
	 * @param warehouseNum the new warehouse num
	 */
	public void setWarehouseNum(Integer warehouseNum) {
		this.warehouseNum = warehouseNum;
	}

	/**
	 * Gets the logistics num.
	 *
	 * @return the logistics num
	 */
	public Integer getLogisticsNum() {
		return logisticsNum;
	}

	/**
	 * Sets the logistics num.
	 *
	 * @param logisticsNum the new logistics num
	 */
	public void setLogisticsNum(Integer logisticsNum) {
		this.logisticsNum = logisticsNum;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * Gets the open date.
	 *
	 * @return the open date
	 */
	public Date getOpenDate() {
		return openDate;
	}

	/**
	 * Sets the open date.
	 *
	 * @param openDate the new open date
	 */
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Integer getIsLimit() {
		return isLimit;
	}

	public void setIsLimit(Integer isLimit) {
		this.isLimit = isLimit;
	}
}
