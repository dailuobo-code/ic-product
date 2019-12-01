package com.mallcai.bs.mapper;

import com.mallcai.bs.common.CustomDataSource;
import com.dailuobo.api.domain.entity.Classify;
import com.dailuobo.api.domain.entity.ProductClassify;
import com.dailuobo.api.domain.vo.DDLClassify;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ClassifyMapper {

    @CustomDataSource(CustomDataSource.GLOBAL)
    void add(Classify classify);

    @CustomDataSource(CustomDataSource.GLOBAL)
    void delete(List<Integer> ids);

    @CustomDataSource(CustomDataSource.GLOBAL)
    void update(Classify classify);
    @CustomDataSource(CustomDataSource.GLOBAL)
    List<Classify> selectAll(Map<String, Object> param);
    List<Classify> selectClass(Map<String, Object> param);

    List<DDLClassify> getDDLClassifies(Map<String, Object> param);

    public List<ProductClassify> getProductClassfyFirst();

    public java.util.List<ProductClassify> getProductClassifySecond(Integer classifyId);

    public java.util.List<ProductClassify> getProductClassifySecondAll();

    List<Classify> selectClassifyById(Integer id);

    @CustomDataSource(CustomDataSource.GLOBAL)
    ProductClassify getById(Integer id);

    List<ProductClassify> getByClassifyIds(@Param("classifyIds") List<Integer> classifyIds);

}