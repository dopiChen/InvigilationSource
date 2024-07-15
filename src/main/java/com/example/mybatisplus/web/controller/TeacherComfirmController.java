package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.Signup;
import com.example.mybatisplus.service.SignupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/getComfirm")
@Api(tags = "教师确认参加")
public class TeacherComfirmController {
    @Autowired
    private SignupService   signupService;

    @GetMapping("/allNotComfirms/{username}")
    @ResponseBody
    @ApiOperation(value = "通知教师确认参加", notes = "根据老师用户名返回已经通过的审批要求确认")
    public JsonResponse<List<Signup>> getComfirmList(String username){
        List<Signup> list = signupService.getComfirmList(username);
        return JsonResponse.success(list);
    }

    @PostMapping("/Docomfirm/{username}/{examId}")
    @ResponseBody
    @ApiOperation(value = "教师确认参加", notes = "根据老师用户名和考试id确认参加监考")
    public JsonResponse<Signup> doComfirm(@PathVariable("username") String username, @PathVariable("examId") int examId){
        QueryWrapper<Signup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username).eq("exam_id",examId);
        Signup signup = signupService.getOne(queryWrapper);
        if (signup != null){
            UpdateWrapper<Signup> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("username",username).eq("exam_id",examId).set("is_comfirm",1);
            signupService.update(updateWrapper);
            return JsonResponse.success(signup);
        }

        return JsonResponse.failure("没有找到该记录");
    }

}
