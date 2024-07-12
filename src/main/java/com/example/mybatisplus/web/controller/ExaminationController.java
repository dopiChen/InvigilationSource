package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
public class ExaminationController {

    private final Logger logger = LoggerFactory.getLogger( ExaminationController.class );

    @Autowired
    private ExaminationService examinationService;

    //获取指定监考批次全部考试信息
    @RequestMapping("/{batch_id}")
    @ResponseBody
    public JsonResponse<List<Examination>> getExaminationByBatchId(@PathVariable("batch_id") String batch_id) {
        QueryWrapper<Examination> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("batch_id", batch_id);
        List<Examination> examinationList = examinationService.list(queryWrapper);
        return JsonResponse.success( examinationList);
    }
}

