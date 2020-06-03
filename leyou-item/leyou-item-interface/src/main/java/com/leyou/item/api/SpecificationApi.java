package com.leyou.item.api;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("spec")
public interface SpecificationApi {

    /**
     * @ClassName SpecificationApi
     *根据条件查询规格参数
     * @param gid
     * @param cid
     * @param generic
     * @param searching
     * @Return : java.util.List<com.leyou.item.pojo.SpecParam>
    */
    @GetMapping("params")
    List<SpecParam> queryParams(
            @RequestParam(value = "gid", required = false) Long gid,
            @RequestParam(value = "cid", required = false) Long cid,
            @RequestParam(value = "generic", required = false) Boolean generic,
            @RequestParam(value = "searching", required = false) Boolean searching
    );

    /**
     * @ClassName SpecificationApi
     *根据分类id查询参数组
     * @param cid
     * @Return : org.springframework.http.ResponseEntity<java.util.List<com.leyou.item.pojo.SpecGroup>>
    */
    @GetMapping("groups/{cid}")
    List<SpecGroup> queryGroupsByCid(@PathVariable("cid") Long cid);


    // 查询规格参数组，及组内参数
    @GetMapping("{cid}")
    List<SpecGroup> querySpecsByCid(@PathVariable("cid") Long cid);


}