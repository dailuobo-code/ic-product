package com.mallcai.bs.mapper;

import java.util.List;
import java.util.Map;

import com.mallcai.bs.common.CustomDataSource;
import com.dailuobo.api.domain.entity.City;
import com.dailuobo.api.domain.vo.DDLCity;
import org.apache.ibatis.annotations.Param;

public interface CityMapper {
    List<City> selectAll(Map<String, Object> param);

    @CustomDataSource(CustomDataSource.GLOBAL)
    void update(City city);

    List<DDLCity> getDDLCities(Map<String, Object> param);

    List<DDLCity> getCityNamesByCityIds(Map<String, Object> param);

    /**
     * 通过城市IDlist查询城市.
     * @param cityIdList
     * @return
     */
    List<City> selectCityByIdList(@Param("cityIdList") List<Integer> cityIdList);

    /**
     * 单查
     * @param id
     * @return
     */
    City getById(Integer id);
}
