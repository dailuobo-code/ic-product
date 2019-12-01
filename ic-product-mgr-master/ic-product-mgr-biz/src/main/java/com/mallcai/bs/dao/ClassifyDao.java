package com.mallcai.bs.dao;

import com.dailuobo.api.domain.entity.Classify;
import com.dailuobo.api.domain.entity.ProductClassify;
import com.dailuobo.api.domain.vo.DDLClassify;
import com.mallcai.bs.mapper.ClassifyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ClassifyDao {
    @Autowired
    private ClassifyMapper classifyMapper;

    public void add(Classify classify) {
        classifyMapper.add(classify);
    }

    public void delete(List<Integer> ids) {
        classifyMapper.delete(ids);
    }

    public void update(Classify classify) {
        classifyMapper.update(classify);
    }

    public List<Classify> selectAll(Map<String, Object> param) {
        return classifyMapper.selectAll(param);
    }
    public List<Classify> selectClass(Map<String, Object> param) {
        return classifyMapper.selectClass(param);
    }

    public List<DDLClassify> getDDLClassifies(Map<String, Object> param) {
        return classifyMapper.getDDLClassifies(param);
    }

    public java.util.List<ProductClassify> getProductClassfyFirst() {
        return classifyMapper.getProductClassfyFirst();
    }

    public java.util.List<ProductClassify> getProductClassifySecond(Integer classifyId) {
        return classifyMapper.getProductClassifySecond(classifyId);
    }

    public java.util.List<ProductClassify> getProductClassifySecondAll() {
        return classifyMapper.getProductClassifySecondAll();
    }

    public List<Classify> selectClassifyById(Integer id) {
        return classifyMapper.selectClassifyById(id);
    }

    public ProductClassify getById(Integer classifyId) {
        return classifyMapper.getById(classifyId);
    }

    /**
     * 批量查询类目
     * @param classifyIds
     * @return
     */
    public List<ProductClassify> getByClassifyIds(List<Integer> classifyIds){
        return classifyMapper.getByClassifyIds(classifyIds);
    }
}
