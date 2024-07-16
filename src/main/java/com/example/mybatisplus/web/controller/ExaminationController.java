package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.ExaminationService;
import com.example.mybatisplus.model.domain.Examination;

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
@RequestMapping("/examination")
@Api(tags = "具体考试信息")
public class ExaminationController {

    private final Logger logger = LoggerFactory.getLogger( ExaminationController.class );

    @Autowired
    private ExaminationService examinationService;

    //获取指定监考批次全部考试信息
    @GetMapping ("/{batch_id}")
    @ResponseBody
    @ApiOperation( value="获取指定监考批次全部考试信息", notes="老师端获取指定监考批次全部考试信息" )
    public JsonResponse<List<Examination>> getExaminationByBatchId(@PathVariable("batch_id") String batch_id) throws Exception{
        QueryWrapper<Examination> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("batch_id", batch_id);
        List<Examination> examinationList = examinationService.list(queryWrapper);
        if (examinationList != null && !examinationList.isEmpty()) throw new Exception("未找到考试信息");
        return JsonResponse.success( examinationList);
    }

    //获取指定id考试信息
    @GetMapping( "/getDetail/{id}")
    @ResponseBody
    @ApiOperation( value="获取指定id考试信息", notes="获取指定id考试信息" )
    public JsonResponse<Examination> getExaminationById(@PathVariable("id") int id) {
        Examination examination = examinationService.getById(id);
        return JsonResponse.success( examination);
    }

}

