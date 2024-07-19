package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.SignupService;
import com.example.mybatisplus.model.domain.Signup;

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
@RequestMapping("/signup")
@Api(tags = "老师提交报名")
public class SignupController {

    private final Logger logger = LoggerFactory.getLogger( SignupController.class );

    @Autowired
    private SignupService signupService;
    /**
    * 描述：根据Id 查询
    *
    */

    //老师查询自己的报名记录
    @GetMapping("/getSignup/{username}")
    @ResponseBody
    @ApiOperation(value = "根据用户名查询报名记录",notes = "老师端根据用户名查询报名记录")
    public JsonResponse<List<Signup>> getSignup(@PathVariable("username") String username) throws Exception{
        QueryWrapper<Signup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        List<Signup> signupList = signupService.list(queryWrapper);
        if(signupList!= null){
         return  JsonResponse.success(signupList);
        }
        throw new Exception("未找到记录");
    }
    @GetMapping("/getOneSignup/{username}/{examId}")
    @ResponseBody
    @ApiOperation(value = "根据用户名和考试编号查询报名记录",notes = "老师端根据用户名和考场编号查询某条报名记录")
    public JsonResponse<Signup> getOneSignup(@PathVariable("username") String username,@PathVariable("examId") int examId){
        QueryWrapper<Signup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username).eq("exam_id",examId);
        Signup signup = signupService.getOne(queryWrapper);
        if(signup!= null){
            return  JsonResponse.success(signup);
        }
        return null;
    }
    // 老师自己报名提交审批
     @PostMapping("/addSignup")
     @ResponseBody
     @ApiOperation(value = "自主报名审批",notes = "老师端自己报名提交审批")
     public JsonResponse<String> addSignup(@RequestBody Signup signup) throws Exception{
        if (signup!= null){
            signupService.save(signup);
            return JsonResponse.success("报名成功");
        }
        else
        {
            throw new Exception("报名失败");
        }
     }
     //领导邀请老师进行报名
     @PostMapping("/addSignupByLeader")
     @ResponseBody
     @ApiOperation(value = "邀请报名审批",notes = "领导端邀请老师进行报名")
     public JsonResponse<String> addSignupByLeader(@RequestBody Signup signup) throws Exception {
         if (signup != null) {
             signupService.save(signup);
             return JsonResponse.success("报名成功");
         } else {
             throw new Exception("报名失败");
         }
     }
     //获取全部申请记录
}

