package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.Signup;
import com.example.mybatisplus.model.domain.User;
import com.example.mybatisplus.model.dto.ResponseWIthPageInfo;
import com.example.mybatisplus.service.SignupService;
import com.example.mybatisplus.service.UserService;
import com.example.mybatisplus.service.impl.SignupServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    @GetMapping("/examine/{username}/{pageSize}/{pageNum}")
    @ResponseBody
    @ApiOperation(value = "获取需要审批的名单", notes = "根据不同usertype获取需要审批的名单--分页")
    public JsonResponse<ResponseWIthPageInfo> getExamineSignUp(@PathVariable("username") String username, @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userService.getOne(queryWrapper);
        int usertype = user.getUsertype();
        PageHelper.startPage(pageNum, pageSize);
        List<Signup> list = signupService.getExamineSignUp(username, usertype - 1);
        PageInfo<Signup> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        return JsonResponse.success(ResponseWIthPageInfo.builder().data(list).total(total).build());
    }

    @GetMapping("/examine/{username}/{pageSize}/{pageNum}/{keyword}")
    @ResponseBody
    @ApiOperation(value = "搜索需要审批的名单", notes = "根据不同usertype获取需要审批的名单--分页")
    public JsonResponse<ResponseWIthPageInfo> getExamineSignUpByKeyword(@PathVariable("username") String username, @PathVariable("pageNum") int pageNum, @PathVariable("keyword") String keyword, @PathVariable("pageSize") int pageSize) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userService.getOne(queryWrapper);
        int usertype = user.getUsertype();
        PageHelper.startPage(pageNum, pageSize);
        List<Signup> list = signupService.getExamineSignUpByKeyword(username, usertype - 1, keyword);

        PageInfo<Signup> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        return JsonResponse.success(ResponseWIthPageInfo.builder().data(list).total(total).build());
    }

    //根据不同usertype获取已同意的名单
    @GetMapping("/approved/{username}/{pageSize}/{pageNum}")
    @ResponseBody
    @ApiOperation(value = "获取已同意的名单", notes = "根据不同usertype获取已同意的名单")
    public JsonResponse<ResponseWIthPageInfo> getApprovedList(@PathVariable("username") String username, @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userService.getOne(queryWrapper);
        int usertype = user.getUsertype();
        PageHelper.startPage(pageNum, pageSize);
        List<Signup> list = signupService.getExamineSignUp(username, usertype);
        PageInfo<Signup> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        return JsonResponse.success(ResponseWIthPageInfo.builder().data(list).total(total).build());
    }

    @GetMapping("/approved/{username}/{pageSize}/{pageNum}/{keyword}")
    @ResponseBody
    @ApiOperation(value = "搜索已同意的名单", notes = "根据不同usertype获取已同意的名单")
    public JsonResponse<ResponseWIthPageInfo> getApprovedListByKeyword(@PathVariable("username") String username, @PathVariable("pageNum") int pageNum, @PathVariable("keyword") String keyword, @PathVariable("pageSize") int pageSize) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userService.getOne(queryWrapper);
        int usertype = user.getUsertype();
        PageHelper.startPage(pageNum, pageSize);
        List<Signup> list = signupService.getExamineSignUpByKeyword(username, usertype, keyword);
        List<Signup> result = list.stream().filter(signup -> signup.getUsername().contains(keyword)).collect(Collectors.toList());
        PageInfo<Signup> pageInfo = new PageInfo<>(result);
        long total = pageInfo.getTotal();
        return JsonResponse.success(ResponseWIthPageInfo.builder().data(result).total(total).build());
    }

    //根据不同usertype获取不同意的名单
    @GetMapping("/disapproved/{username}/{pageSize}/{pageNum}")
    @ResponseBody
    @ApiOperation(value = "获取不同意的名单", notes = "根据不同usertype获取不同意的名单")
    public JsonResponse<ResponseWIthPageInfo> getDisapprovedList(@PathVariable("username") String username, @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userService.getOne(queryWrapper);
        int usertype = user.getUsertype();
        PageHelper.startPage(pageNum, pageSize);
        List<Signup> list = signupService.getDisapprovedList(username, usertype - 1);
        PageInfo<Signup> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        return JsonResponse.success(ResponseWIthPageInfo.builder().data(list).total(total).build());
    }

    @GetMapping("/disapproved/{username}/{pageSize}/{pageNum}/{keyword}")
    @ResponseBody
    @ApiOperation(value = "搜索不同意的名单", notes = "根据不同usertype获取不同意的名单")
    public JsonResponse<ResponseWIthPageInfo> getDisapprovedList(@PathVariable("username") String username, @PathVariable("pageNum") int pageNum, @PathVariable("keyword") String keyword, @PathVariable("pageSize") int pageSize) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userService.getOne(queryWrapper);
        int usertype = user.getUsertype();
        PageHelper.startPage(pageNum, pageSize);
        List<Signup> list = signupService.getDisapprovedListByKeyword(username, usertype - 1,keyword);

        PageInfo<Signup> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        return JsonResponse.success(ResponseWIthPageInfo.builder().data(list).total(total).build());
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
    @PostMapping("/disapprove/{username}/{examId}/{reason}")
    @ResponseBody
    @ApiOperation(value = "不同意报名", notes = "领导端不同意报名")
    public JsonResponse disallow(@PathVariable("username") String username, @PathVariable("examId") int examId, @PathVariable("reason") String reason) throws Exception {
        QueryWrapper<Signup> signupQueryWrapper = new QueryWrapper<>();
        signupQueryWrapper.eq("exam_id", examId).eq("username", username);
        Signup one = signupService.getOne(signupQueryWrapper);
        if (one != null) {
            signupService.disallowSignUp(one, reason);
            return JsonResponse.success(one);
        } else {
            throw new Exception("审批失败");
        }
    }
}
