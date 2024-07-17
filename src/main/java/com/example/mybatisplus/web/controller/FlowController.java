package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.FlowResponse;
import com.example.mybatisplus.model.domain.Personnel;
import com.example.mybatisplus.service.impl.PersonnelServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flow")
@Api(tags = "审批流程")
public class FlowController {
    @Autowired
    PersonnelServiceImpl personnelService;

    @GetMapping("/{username}")
    @ApiOperation("获取指定老师的领导列表")
    public JsonResponse<List<FlowResponse>> getFlowList(@PathVariable String username) throws Exception {
        Personnel theOne =  personnelService.getById(username);
        if (theOne == null) throw new Exception("未找到用户");
        String unit = theOne.getUnit();

        List<FlowResponse> list = personnelService.getFlowList(unit);
        if (list==null || list.isEmpty()) throw new Exception("未找到领导列表");

        return JsonResponse.success(list);
    }
}
