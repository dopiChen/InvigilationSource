package com.example.mybatisplus.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.BatchService;
import com.example.mybatisplus.model.domain.Batch;

import java.util.List;


/**
 *
 *  前端控制器
 *
 *
 * @author clt
 * @since 2024-07-12
 * @version v1.0
 */
@Controller
@RequestMapping("/batch")
public class BatchController {

    private final Logger logger = LoggerFactory.getLogger( BatchController.class );

    @Autowired
    private BatchService batchService;
    //获取全部批次
    @RequestMapping("/AllBatches")
    @ResponseBody
    public JsonResponse<List<Batch>> getAllBatches() {
        return JsonResponse.success( batchService.list());
    }
    //创建批次
    @PostMapping("/createBatch")
    @ResponseBody
    public JsonResponse<Boolean> createBatch(@RequestBody Batch batch) {
        return JsonResponse.success(batchService.save(batch));
    }
    //根据批次关键词查询
     @GetMapping("/searchBatch/{keyword}")
    @ResponseBody
     public JsonResponse<List<Batch>> searchBatch(@PathVariable("keyword") String keyword) {
        return JsonResponse.success(batchService.searchBatch(keyword));
     }

}

