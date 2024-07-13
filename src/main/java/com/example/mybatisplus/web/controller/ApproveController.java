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


@Api(tags = "审批报名")
@Controller
@RequestMapping("/batch")
public class ApproveController {
    @Autowired
    private SignupServiceImpl signupService;
    @Autowired
    private UserService userService;
    //根据不同usertype获取需要审批的名单
    @GetMapping("examine/{username}")
    @ResponseBody
    @ApiOperation(value = "获取需要审批的名单", notes = "根据不同usertype获取需要审批的名单")
    public JsonResponse<List<Signup>> getExamineSignUp(@PathVariable("username") String username){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user=userService.getOne(queryWrapper);
        int usertype=user.getUsertype();
        return JsonResponse.success(signupService.getExamineSignUp(username,usertype-1));
    }

   //同意报名
    @PostMapping("/approve")
    @ResponseBody
    @ApiOperation(value = "同意报名", notes = "领导端同意报名")
    public JsonResponse allow(@RequestBody Signup signup){
        QueryWrapper<Signup> signupQueryWrapper=new QueryWrapper<>();
        signupQueryWrapper.eq("exam_id",signup.getExamId()).eq("username",signup.getUsername());
        Signup  one= signupService.getOne(signupQueryWrapper);
        if(one!=null){
            signupService.allowSignUp(one);
            return JsonResponse.success(one);
        }else{
            return JsonResponse.failure("审批失败");
        }
    }
    //不同意报名
     @PostMapping("/disapprove")
     @ResponseBody
     @ApiOperation(value = "不同意报名", notes = "领导端不同意报名")
     public JsonResponse disallow(@RequestBody Signup signup){
        QueryWrapper<Signup> signupQueryWrapper=new QueryWrapper<>();
        signupQueryWrapper.eq("exam_id",signup.getExamId()).eq("username",signup.getUsername());
        Signup  one= signupService.getOne(signupQueryWrapper);
        if(one!=null){
            signupService.disallowSignUp(one);
            return JsonResponse.success(one);
        }else{
            return JsonResponse.failure("审批失败");
        }
     }
}
