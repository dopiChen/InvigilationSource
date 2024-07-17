package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.model.domain.Batch;
import com.example.mybatisplus.model.dto.PageDTO;
import com.example.mybatisplus.model.dto.UnitCountDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.PersonnelService;
import com.example.mybatisplus.model.domain.Personnel;


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
@RequestMapping("/personnel")
@Api(tags = "用户信息")
public class PersonnelController {

    private final Logger logger = LoggerFactory.getLogger( PersonnelController.class );

    @Autowired
    private PersonnelService personnelService;


    //用户端获取个人信息
    @GetMapping(value = "/information/{username}")
    @ResponseBody
    @ApiOperation(value = "获取用户信息", notes = "根据用户名获取用户信息")
    public JsonResponse<Personnel> getUserInformation(@PathVariable("username") String username) throws Exception{
        QueryWrapper<Personnel> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        Personnel one=personnelService.getOne(queryWrapper);
        if(one!=null){
            return JsonResponse.success(one);
        }else{
            throw new Exception("用户不存在");
        }
    }
    //用户端修改个人信息
    @PostMapping("/information/update")
    @ResponseBody
    @ApiOperation(value = "修改用户信息", notes = "根据用户名修改用户信息")
    public JsonResponse<String> updateUserInformation(@RequestBody Personnel personnel) {
        personnelService.updateById(personnel);
        return JsonResponse.success("修改成功");
    }
    @GetMapping("unitList")
    @ResponseBody
    public JsonResponse unitList(Personnel personnel, PageDTO dto){
        Page<UnitCountDTO> page=personnelService.unitList(personnel,dto);
        return JsonResponse.success(page);
    }
}

