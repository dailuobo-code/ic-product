package com.mallcai.bs.dao;

import com.dailuobo.api.domain.entity.City;
import com.mallcai.bs.mapper.CityMapper;
import com.dailuobo.api.domain.vo.DDLCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CityDao {
    @Autowired
    private CityMapper cityMapper;

    public List<City> selectAll(Map<String, Object> param) {
        return cityMapper.selectAll(param);
    }

    public void update(City city) {
        cityMapper.update(city);
    }

    public List<DDLCity> getDDLCities(Map<String, Object> param) {
        return cityMapper.getDDLCities(param);
    }


    /**
     * 通过城市IDlist查询城市.
     * @param cityIdList
     * @return
     */
    public List<City> selectCityByIdList(List<Integer> cityIdList){
        return cityMapper.selectCityByIdList(cityIdList);
    }

    public List<DDLCity> getCityNamesByCityIds(Map<String, Object> param) {
        return cityMapper.getCityNamesByCityIds(param);
    }

    public City getById(Integer id) {
        return cityMapper.getById(id);
    }
}
