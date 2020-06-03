package com.leyou.item.controller;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

/**
 *
 */
@Controller
@RequestMapping("spec")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;

    /**
     * @ClassName SpecificationController
     *根据分类id查询参数组
     * @param cid
     * @Return : org.springframework.http.ResponseEntity<java.util.List<com.leyou.item.pojo.SpecGroup>>
    */
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupsByCid(@PathVariable("cid")Long cid){

        List<SpecGroup> groups = this.specificationService.queryGroupsByCid(cid);
        if (CollectionUtils.isEmpty(groups)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(groups);
    }

    /**
     * @ClassName SpecificationController
     * 查询规格参数组，及组内参数
     * @param cid
     * @Return : org.springframework.http.ResponseEntity<java.util.List<com.leyou.item.pojo.SpecGroup>>
     */
    @GetMapping("{cid}")
    public ResponseEntity<List<SpecGroup>> querySpecsByCid(@PathVariable("cid") Long cid){
        List<SpecGroup> list = this.specificationService.querySpecsByCid(cid);
        if(list == null || list.size() == 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }

    /**
     * @ClassName SpecificationController
     *根据条件查询规格参数
     * @param gid
     * @Return : org.springframework.http.ResponseEntity<java.util.List<com.leyou.item.pojo.SpecParam>>
    */
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> quertParams(
            @RequestParam(value = "gid" , required = false)Long gid,
            @RequestParam(value = "cid", required = false)Long cid,
            @RequestParam(value = "generic", required = false)Boolean generic,
            @RequestParam(value = "searching", required = false)Boolean searching
            ){
        List<SpecParam> params=this.specificationService.queryParams(gid, cid, generic, searching);
        if (CollectionUtils.isEmpty(params)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(params);
    }

    /**
     * @ClassName SpecificationController
     *查询规格参数组，及组内参数
     * @param cid
     * @Return : org.springframework.http.ResponseEntity<java.util.List<com.leyou.item.pojo.SpecGroup>>
    */
//    @GetMapping("group/param/{cid}")
//    public ResponseEntity<List<SpecGroup>> queryGroupWithParam(@PathVariable("cid")Long cid){
//        List<SpecGroup> groups = this.specificationService.queryGroupWithParam(cid);
//        if (CollectionUtils.isEmpty(groups)){
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(groups);
//    }

}
