package com.mallcai.bs.mapper;

import com.dailuobo.api.domain.entity.CityProduct;
import com.dailuobo.api.domain.entity.HqProduct;
import com.dailuobo.api.domain.entity.HqProductTax;
import com.dailuobo.api.domain.soa.*;
import com.dailuobo.api.domain.soa.city.CityProductDto;
import com.dailuobo.api.domain.soa.city.SalesSpecDto;
import com.mallcai.backend.common.dao.vo.StorageStore;
import com.mallcai.bs.model.*;
import com.mallcai.service.vo.ic.common.StorageBannerProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SOAHPMapper {
	/**
	 * 获取单个banner的明细
	 * @param allBannerIdList
	 * @return
	 */
	public StorageBannerPool getById(@Param("bannerId")Integer bannerId);

	/**
	 * 获取城市取货点信息
	 * @param cityId
	 * @return
	 */
	public List<StorageHPBanner> getCityHPBannerList(@Param("cityId") Integer cityId);

	public List<StorageStore> getCityStoreList(Integer cityId);

	/**
	 * 获取banner的明细
	 * @param allBannerIdList
	 * @return
	 */
	public List<StorageBannerPool> getSpecifyIds(@Param("bannerIds")String bannerIds);

	public List<StorageBannerProduct> getByBannerId(Integer bannerId);

	public List<SOAStoreHeadline> getStoreHeadlineByCityId(Integer cityId);

	public List<SOAHeadline> getHeadlineByCityId(Integer cityId);

	List<StorageHPProduct> getCityHPProductList(@Param("cityId") Integer cityId);

	public List<StorageHPTile> getCityHPTileList(@Param("cityId") Integer cityId);

	public List<StorageBannerProduct> getHPBannerProductListAll();

	public List<StorageHPProduct> getCityHPProductListAll();

	public List<SOAHeadlineProduct> getHeadlineProductListAll();

	public List<CityProductDto> getProductByCityHqProductId(Integer hqProductId);

	public List<SalesSpecDto> getProductSpecByHqProductId(Integer hqProductId);

	public List<StorageBannerProduct> getHPBannerProductList(Integer cityId);

	public List<SOAHeadlineProduct> getHeadlineProductListByCityId(Integer cityId);

	public SOAHPStyleRel getHPStyleRel(@Param("cityId")Integer cityId, @Param("storeId")Integer storeId);

	public SOAHPStyle getHPStyle(Integer id);

	public StorageTilePool getTilePoolById(@Param("tileId")Integer tileId);

	public List<HqProductTax> getProductTaxByCode(@Param("codes") List<String> codes);

	List<HqProduct> getByProductNos(@Param("productNOs") List<String> productNOs);

    List<HqProduct> getByHqProductNames(@Param("hqProductNames") List<String> hqProductNames);
}
