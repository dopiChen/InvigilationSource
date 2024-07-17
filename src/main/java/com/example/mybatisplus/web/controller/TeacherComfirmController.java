package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.Batch;
import com.example.mybatisplus.model.domain.Signup;
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

@Controller
@RequestMapping("/getComfirm")
@Api(tags = "教师确认参加")
public class TeacherComfirmController {
    @Autowired
    private SignupService   signupService;

    @GetMapping("/allNotComfirms/{username}")
    @ResponseBody
    @ApiOperation(value = "通知教师确认参加", notes = "根据老师用户名返回已经通过的审批要求确认")
    public JsonResponse<List<Signup>> getComfirmList(@PathVariable("username") String username)  {
        List<Signup> list = signupService.getComfirmList(username);
        return JsonResponse.success(list);
    }

    @PostMapping("/Docomfirm/{username}/{examId}")
    @ResponseBody
    @ApiOperation(value = "教师确认参加", notes = "根据老师用户名和考试id确认参加监考")
    public JsonResponse<Signup> doComfirm(@PathVariable("username") String username, @PathVariable("examId") int examId) throws Exception{
        QueryWrapper<Signup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username).eq("exam_id",examId);
        Signup signup = signupService.getOne(queryWrapper);
        if (signup != null){
            UpdateWrapper<Signup> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("username",username).eq("exam_id",examId).set("is_comfirm",1);
            signupService.update(updateWrapper);
            return JsonResponse.success(signup);
        }

        throw new Exception("确认失败");
    }

    @GetMapping("/allComfirms/{username}")
    @ResponseBody
    @ApiOperation(value = "教师所有通过的监考记录", notes = "根据老师用户名返回所有监考记录")
    public JsonResponse<List<Signup>> getAllComfirms( @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size,@PathVariable("username") String username){
        PageHelper.startPage(page, size);
        List<Signup> list = signupService.getAllComfirms(username);
        PageInfo<Signup> pageInfo = new PageInfo<>(list);
        return JsonResponse.success(pageInfo.getList());
    }

    @GetMapping("/allComfirms/search/{username}/{examId}")
    @ResponseBody
    @ApiOperation(value = "教师根据考试编号查询", notes = "根据考试编号返回对应通过的审批")
    public JsonResponse<List<Signup>> searchComfirms(@PathVariable("username") String username ,@PathVariable("examId") int examId,
                                                     @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size){
        PageHelper.startPage(page, size);
        List<Signup> list = signupService.getAllComfirms(username);
        List<Signup> result = list.stream().filter(item -> item.getExamId() == examId).collect(Collectors.toList());
        PageInfo<Signup> pageInfo = new PageInfo<>(result);
        return JsonResponse.success(pageInfo.getList());
    }

}
