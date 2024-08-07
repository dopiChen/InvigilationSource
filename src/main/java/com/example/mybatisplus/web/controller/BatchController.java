package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.model.domain.Examination;
import com.example.mybatisplus.model.dto.PageDTO;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.Batch;
import com.example.mybatisplus.model.dto.DeleteDTO;
import com.example.mybatisplus.service.BatchService;
import com.example.mybatisplus.service.ExaminationService;
import com.example.mybatisplus.service.SignupService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 前端控制器
 *
 * @author clt
 * @version v1.0
 * @since 2024-07-12
 */
@Api(tags = "批次管理")
@RestController
@RequestMapping("/batch")
public class BatchController {

    private final Logger logger = LoggerFactory.getLogger(BatchController.class);

    @Autowired
    private BatchService batchService;
    @Autowired
    private ExaminationService examinationService;

    //获取全部批次
    @GetMapping("/AllBatches")
    @ResponseBody
    @ApiOperation(value = "获取全部批次", notes = "教师端获取全部批次")
    public JsonResponse<List<Batch>> getAllBatches( @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) throws Exception {
        PageHelper.startPage(page, size);
        List<Batch> list = batchService.list();
        if (list == null || list.isEmpty()) throw new Exception("未找到批次");
        PageInfo<Batch> pageInfo = new PageInfo<>(list);
        return JsonResponse.success(pageInfo.getList());
    }

    //创建批次
    @PostMapping("/createBatch")
    @ResponseBody
    @ApiOperation(value = "创建批次", notes = "科长端创建批次")
    public JsonResponse<Boolean> createBatch(@RequestBody Batch batch) {

        return JsonResponse.success(batchService.save(batch));
    }

    //根据批次关键词查询
    @GetMapping("/searchBatch/{keyword}")
    @ResponseBody
    @ApiOperation(value = "根据批次关键词查询", notes = "教师端根据批次关键词查询")
    public JsonResponse<List<Batch>> searchBatch(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size,@PathVariable("keyword") String keyword) throws Exception {
        PageHelper.startPage(page, size);
        List<Batch> list = batchService.list();
        List<Batch> result = list.stream().filter(batch -> batch.getBatchName().contains(keyword)).collect(Collectors.toList());
        if (result == null || result.isEmpty()) throw new Exception("未找到批次");
        PageInfo<Batch> pageInfo = new PageInfo<>(result);
        return JsonResponse.success(pageInfo.getList());
    }

    @GetMapping("pageList")
    @ResponseBody
    public JsonResponse pageList(Batch batch, PageDTO dto){
        Page<Batch> page=batchService.pageList(batch,dto);
        return JsonResponse.success(page);
    }

    @GetMapping("/getBatch/{batchId}")
    @ResponseBody
    @ApiOperation(value = "根据批次id查询", notes = "教师端根据批次id获取具体批次信息")

    public JsonResponse<Batch> getBatch(@PathVariable("batchId") int batchId) {
        return JsonResponse.success(batchService.getById(batchId));
    }
    @GetMapping("/removeById")
    @ResponseBody
    public JsonResponse removeById(long id){
        List<Integer> e=examinationService.getByBatchId(id);
        boolean a=examinationService.removeByIds(e);
        boolean b = batchService.removeById(id);
        return JsonResponse.success(b&&a);
    }
    @PostMapping("/removeByIds")
    public JsonResponse removeByIds(@RequestBody DeleteDTO dto){
        for(Long id : dto.getIds()){
            List<Integer> e=examinationService.getByBatchId(id);
            boolean a=examinationService.removeByIds(e);
            boolean b=batchService.removeById(id);
            if(a||b==false)
                return JsonResponse.success(false);
        }
        return JsonResponse.success(true);
    }

}

