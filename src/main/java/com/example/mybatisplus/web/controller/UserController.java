package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mybatisplus.common.utls.SessionUtils;
import com.example.mybatisplus.model.domain.*;
import com.example.mybatisplus.service.impl.SmsService;
import com.example.mybatisplus.service.impl.VerificationCodeService;
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
    @Autowired
    private SmsService smsService;
    @Autowired
    private VerificationCodeService verificationCodeService;

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
            throw new Exception("登录失败");
        }
    }

    @GetMapping(value = "/sendSmsCode/{phone}")
    @ResponseBody
    @ApiOperation(value = "发送短信验证码", notes = "发送短信验证码到指定手机号")
    public JsonResponse sendSmsCode(@PathVariable("phone") String phone) {
        // 检查手机号是否合法
        if (!phone.matches("^1[3-9]\\d{9}$")) {
            return JsonResponse.failure("手机号格式不正确");
        }
        String code = generateCode();
        try {
            smsService.sendSms(phone, code);
            verificationCodeService.storeCode(phone, code);
            return JsonResponse.success("验证码已发送");
        } catch (Exception e) {
            logger.error("发送验证码失败", e);
            return JsonResponse.failure("发送验证码失败");
        }
    }
    @PostMapping(value = "/loginWithSms")
    @ResponseBody
    @ApiOperation(value = "短信验证码登录", notes = "通过短信验证码进行登录")
    public JsonResponse loginWithSms(@RequestBody LoginRequest request) {
        String phone = request.getPhone();
        String code = request.getCode();
        if (verificationCodeService.verifyCode(phone, code)) {
            User user = userService.findByPhone(phone); // 根据手机号查找用户
            if (user != null) {
                SessionUtils.saveCurrentUserInfo(user);
                return JsonResponse.success(user);
            } else {
                return JsonResponse.failure("用户不存在");
            }
        } else {
            return JsonResponse.failure("验证码错误或已过期");
        }
    }

    @PostMapping(value = "/comfirmWithSms")
    @ResponseBody
    @ApiOperation(value = "短信验证码确定用户", notes = "通过手机号和用户名核实用户")
    public JsonResponse comfirmWithSms(@RequestBody ComfirmUserByPhone request) {
        String phone = request.getPhone();
        String code = request.getCode();
        String username=request.getUsername();
        if (verificationCodeService.verifyCode(phone, code)) {
            User user = userService.findByPhone(phone); // 根据手机号查找用户
            if (user != null) {
                if(user.getUsername()==username){
                    return JsonResponse.success(user);
                }
                else {
                    return JsonResponse.failure("手机号与用户名不符合");
                }
            } else {
                return JsonResponse.failure("用户不存在");
            }
        } else {
            return JsonResponse.failure("验证码错误或已过期");
        }
    }
    @PostMapping(value = "/resetPassword")
    @ResponseBody
    @ApiOperation(value = "重置密码", notes = "通过手机号和验证码重置密码")
    public JsonResponse resetPassword(@RequestBody ResetPassWord request) {
        String username=request.getUsername();
        String password=request.getPassword();
         UpdateWrapper<User> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("username",username).set("password",password);
        userService.update(updateWrapper);
        return JsonResponse.success(userService.getById(username));
    }
    private String generateCode() {
        // 生成六位数验证码
        return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
    }



}

