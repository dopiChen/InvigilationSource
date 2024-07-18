package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.FinalLiist;
import com.example.mybatisplus.model.domain.Batch;
import com.example.mybatisplus.model.domain.FinalLiist;
import com.example.mybatisplus.model.domain.Signup;
import com.example.mybatisplus.model.dto.ResponseWIthPageInfo;
import com.example.mybatisplus.model.dto.PageDTO;
import com.example.mybatisplus.service.SignupService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "最终监考名单")
@Controller
@RequestMapping("/finalNameList")
public class FianlNameListController {
    @Autowired
    private SignupService signupService;

    //领导端获取最终监考名单
    @GetMapping("/{pageNum}")
    @ResponseBody
    @ApiOperation(value = "获取最终监考名单", notes = "主任端获取最终监考名单")
    public JsonResponse<ResponseWIthPageInfo> getFinalNameList(@PathVariable("pageNum") int pageNum) throws Exception {
        PageHelper.startPage(pageNum, 2);
        List<FinalLiist> list = signupService.getFinalNameList();
        if (list == null || list.isEmpty()) throw new Exception("未找到监名单考");
        PageInfo<FinalLiist> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        return JsonResponse.success(ResponseWIthPageInfo.builder().data(list).total(total).build());
    }

    //根据教师工号关键词搜索名单
    @GetMapping("/search/{keyword}/{pageNum}")
    @ResponseBody
    @ApiOperation(value = "根据教师工号关键词搜索名单", notes = "主任端根据教师工号关键词搜索名单")
    public JsonResponse<ResponseWIthPageInfo> searchFinalNameList(@PathVariable("keyword") String keyword, @PathVariable("pageNum") int pageNum) throws Exception {
        List<FinalLiist> list = signupService.getFinalNameList();
        if (list == null || list.isEmpty()) throw new Exception("未找到监名单考");
        PageHelper.startPage(pageNum, 2);
        List<FinalLiist> result = list.stream().filter(finalItem -> finalItem.getPersonnel().getUsername().contains(keyword)).collect(Collectors.toList());
        PageInfo<FinalLiist> pageInfo = new PageInfo<>(result);
        long total = pageInfo.getTotal();
        return JsonResponse.success(ResponseWIthPageInfo.builder().data(result).total(total).build());
    }

}
