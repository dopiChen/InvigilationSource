package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.User;
import com.example.mybatisplus.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/positionChange")
@Api(value = "职位状态变动", tags = "副院长变动教师职位和账号状态")
public class PositionChangeController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "副院长变动教师职位", notes = "副院长端改变角色类型")
    @PostMapping("/changePosition/{username}/{usertype}")
    public JsonResponse changePosition(@PathVariable("username") String username, @PathVariable("usertype") int usertype){
        User one=userService.getById(username);
        if(one!=null) {
            one.setUserType(usertype);
            userService.updateById(one);
            return JsonResponse.success("修改成功");
        }
        return JsonResponse.failure("修改失败");
    }

    @ApiOperation(value = "副院长变动教师账号状态", notes = "副院长端改变账号状态")
    @PostMapping("/changeStatus/{username}/{status}")
    public JsonResponse changeStatus(@PathVariable("username") String username, @PathVariable("status") boolean status){
        User one=userService.getById(username);
        if(one!=null) {
            one.setStatus(status);
            userService.updateById(one);
            return JsonResponse.success("修改成功");
        }
        return JsonResponse.failure("修改失败");
    }
}
