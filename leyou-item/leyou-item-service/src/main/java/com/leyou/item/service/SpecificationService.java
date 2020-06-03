package com.leyou.item.service;

import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *
 */
@Service
public class SpecificationService {

    @Autowired
    private SpecGroupMapper specGroupMapper;

    @Autowired
    private SpecParamMapper specParamMapper;

    /**
     * @ClassName SpecificationService
     * 根据分类id查询分组
     * @param cid
     * @Return : java.util.List<com.leyou.item.pojo.SpecGroup>
    */
    public List<SpecGroup> queryGroupsByCid(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        return this.specGroupMapper.select(specGroup);
    }
    /**
     * @ClassName SpecificationService
     *根据条件查询规格参数
     * @param gid
     * @Return : java.util.List<com.leyou.item.pojo.SpecParam>
    */
    public List<SpecParam> queryParams(Long gid, Long cid, Boolean generic, Boolean searching) {
        SpecParam record = new SpecParam();
        record.setGroupId(gid);
        record.setCid(cid);
        record.setGeneric(generic);
        record.setSearching(searching);
        return this.specParamMapper.select(record);
    }

    /**
     * @ClassName SpecificationService
     * 查询规格参数组，及组内参数
     * @param cid
     * @Return : java.util.List<com.leyou.item.pojo.SpecGroup>
    */
    public List<SpecGroup> querySpecsByCid(Long cid) {
        // 查询规格组
        List<SpecGroup> groups = this.queryGroupsByCid(cid);
        groups.forEach(g -> {
            // 查询组内参数
            g.setParams(this.queryParams( g.getId(), null, null, null));
        });
        return groups;
    }

//    public List<SpecGroup> queryGroupWithParam(Long cid) {
//        return null;
//    }
}
