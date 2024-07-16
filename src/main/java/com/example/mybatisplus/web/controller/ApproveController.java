package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.Signup;
import com.example.mybatisplus.model.domain.User;
import com.example.mybatisplus.service.SignupService;
import com.example.mybatisplus.service.UserService;
import com.example.mybatisplus.service.impl.SignupServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Api(tags = "领导审批报名")
@Controller
@RequestMapping("/batch")
public class ApproveController {
    @Autowired
    private SignupServiceImpl signupService;
    @Autowired
    private UserService userService;

    //根据不同usertype获取需要审批的名单
    @GetMapping("/examine/{username}")
    @ResponseBody
    @ApiOperation(value = "获取需要审批的名单", notes = "根据不同usertype获取需要审批的名单")
    public JsonResponse<List<Signup>> getExamineSignUp(@PathVariable("username") String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userService.getOne(queryWrapper);
        int usertype = user.getUsertype();
        return JsonResponse.success(signupService.getExamineSignUp(username, usertype - 1));
    }

    //同意报名
    @PostMapping("/approve/{username}/{examId}")
    @ResponseBody
    @ApiOperation(value = "同意报名", notes = "领导端同意报名")
    public JsonResponse allow(@PathVariable("username") String username, @PathVariable("examId") int examId) throws Exception {
        QueryWrapper<Signup> signupQueryWrapper = new QueryWrapper<>();
        signupQueryWrapper.eq("exam_id", examId).eq("username", username);
        Signup one = signupService.getOne(signupQueryWrapper);
        if (one != null) {
            signupService.allowSignUp(one);
            return JsonResponse.success(one);
        } else {
            throw new Exception("审批失败");
        }
    }

    //不同意报名
    @PostMapping("/disapprove/{username}/{examId}")
    @ResponseBody
    @ApiOperation(value = "不同意报名", notes = "领导端不同意报名")
    public JsonResponse disallow(@PathVariable("username") String username, @PathVariable("examId") int examId) throws Exception {
        QueryWrapper<Signup> signupQueryWrapper = new QueryWrapper<>();
        signupQueryWrapper.eq("exam_id", examId).eq("username", username);
        Signup one = signupService.getOne(signupQueryWrapper);
        if (one != null) {
            signupService.disallowSignUp(one);
            return JsonResponse.success(one);
        } else {
            throw new Exception("审批失败");
        }
    }

    //根据公号查询待审批名单
    @GetMapping("/examine/search/{username}/{usertype}/{keyword}")
    @ResponseBody
    @ApiOperation(value = "根据公号查询待审批名单", notes = "根据公号及其对应领导工号、等级以及搜索关键词查询待审批名单")
    public JsonResponse<List<Signup>> searchExamineSignUp(@PathVariable("username") String username, @PathVariable("usertype") int usertype, @PathVariable("keyword") String keyword) throws Exception {
        List<Signup> list = signupService.getExamineSignUp(username, usertype - 1);
        if (list == null || list.isEmpty()) throw new Exception("未找到待审批名单");
        List<Signup> result = list.stream().filter(signup -> signup.getUsername().contains(keyword)).collect(Collectors.toList());
        return JsonResponse.success(result);
    }
}
