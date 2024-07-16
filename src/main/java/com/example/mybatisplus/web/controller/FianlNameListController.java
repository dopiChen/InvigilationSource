package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.Signup;
import com.example.mybatisplus.service.SignupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "最终监考名单")
@Controller
@RequestMapping("/finalNameList")
public class FianlNameListController {
    @Autowired
    private SignupService signupService;

    //领导端获取最终监考名单
    @GetMapping()
    @ResponseBody
    @ApiOperation(value = "获取最终监考名单", notes = "主任端获取最终监考名单")
    public JsonResponse<List<Signup>> getFinalNameList() throws Exception {
        List<Signup> list = signupService.getFinalNameList();
        if (list == null || list.isEmpty()) throw new Exception("未找到监名单考");
        return JsonResponse.success(list);
    }
    //根据教师工号关键词搜索名单
    @GetMapping("/search/{keyword}")
    @ResponseBody
    @ApiOperation(value = "根据教师工号关键词搜索名单", notes = "主任端根据教师工号关键词搜索名单")
    public JsonResponse<List<Signup>> searchFinalNameList(String keyword) throws Exception {
        List<Signup> list = signupService.getFinalNameList();
        if (list==null || list.isEmpty()) throw new Exception("未找到监名单考");
        List<Signup> result=list.stream().filter(signup -> signup.getUsername().contains(keyword)).collect(Collectors.toList());
        return JsonResponse.success(result);
    }

}
