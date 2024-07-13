package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.common.utls.SessionUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.UserService;
import com.example.mybatisplus.model.domain.User;


/**
 *
 *  前端控制器
 *
 *
 * @author clt
 * @since 2024-07-11
 * @version v1.0
 */
@Controller
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger( UserController.class );

    @Autowired
    private UserService userService;

    /**
    * 描述：根据Id 查询
    *
    */
    @PostMapping(value = "/login")
    @ResponseBody
    @ApiOperation(value = "用户登录",notes = "根据用户名和密码查询用户")
    public JsonResponse getById(@RequestBody User user)throws Exception {
        User  one =  userService.login(user);
        if(one!=null){
            SessionUtils.saveCurrentUserInfo(one);
            return JsonResponse.success(one);

        }else{
            return JsonResponse.failure("用户名或密码错误");
        }
    }
}

