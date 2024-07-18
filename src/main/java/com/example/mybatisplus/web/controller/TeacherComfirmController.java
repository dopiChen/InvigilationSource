package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.Batch;
import com.example.mybatisplus.model.domain.Signup;
import com.example.mybatisplus.model.dto.PageDTO;
import com.example.mybatisplus.service.SignupService;
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
    public JsonResponse<List<Signup>> getComfirmList(String username)  {
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
    //分叶查询
    @PostMapping("/allComfirms/search/{username}/{examId}")
    @ResponseBody
    @ApiOperation(value = "教师根据考试编号查询", notes = "根据考试编号返回对应通过的审批")
    public JsonResponse searchComfirms(@PathVariable("username") String username ,@PathVariable("examId") int examId,PageDTO pageDTO){
        Page<Signup> page=signupService.searchAllComfirmPageList(username, examId,pageDTO);
        if (page != null){
            return JsonResponse.success(page);
        }
        else {
            return JsonResponse.failure("查询结果为空");
        }
    }
    //分页展示
    @PostMapping("/allComfirms/{username}")
    @ResponseBody
    @ApiOperation(value = "教师所有通过的监考记录", notes = "根据老师用户名返回所有监考记录")
    public JsonResponse getAllComfirms(@PathVariable("username") String username, PageDTO pageDTO,Signup signup ){
        Page<Signup> page=signupService.allComfirmPageList(username,signup,pageDTO);
        if (page != null){
        return JsonResponse.success(page);
        }
        else {
            return JsonResponse.failure("查询结果为空");
        }
    }

}
