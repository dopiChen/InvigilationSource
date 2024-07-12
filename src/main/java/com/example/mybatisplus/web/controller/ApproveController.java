package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.Signup;
import com.example.mybatisplus.service.SignupService;
import com.example.mybatisplus.service.impl.SignupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/batch")
public class ApproveController {
    @Autowired
    private SignupServiceImpl signupService;

    //根据不同usertype获取需要审批的名单
    @GetMapping("examine/{usertype}")
    @ResponseBody
    public JsonResponse<List<Signup>> getExamineSignUp(@PathVariable("usertype") int usertype){
        return JsonResponse.success(signupService.getExamineSignUp(usertype-1));
    }

   //同意报名
    @PostMapping("/approve")
    @ResponseBody
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
}
