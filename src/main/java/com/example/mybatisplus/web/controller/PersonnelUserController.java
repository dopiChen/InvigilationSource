package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.FinalLiist;
import com.example.mybatisplus.model.domain.FlowItem;
import com.example.mybatisplus.model.domain.FlowResponse;
import com.example.mybatisplus.model.domain.Personnel;
import com.example.mybatisplus.model.dto.PerUserDTO;
import com.example.mybatisplus.model.dto.ResponseWIthPageInfo;
import com.example.mybatisplus.model.dto.UnitCountDTO;
import com.example.mybatisplus.service.impl.PersonnelServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "部门成员具体名单")
@RestController
@RequestMapping("/peruser")
public class PersonnelUserController {
    @Autowired
    PersonnelServiceImpl personnelService;

    @GetMapping("/dto/{unit}/{pageNum}/{pageSize}")
    @ResponseBody
    public JsonResponse peruserdto(@PathVariable String unit,@PathVariable("pageNum")int pageNum, @PathVariable("pageSize") int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<PerUserDTO> perUserDTOS=personnelService.getperuserListdto(unit);
        PageInfo<PerUserDTO> pageInfo = new PageInfo<>(perUserDTOS);
        long total = pageInfo.getTotal();
        return JsonResponse.success(ResponseWIthPageInfo.builder().data(perUserDTOS).total(total).build());
    }

    @GetMapping("/{pageNum}/{pageSize}")
    @ResponseBody
    public JsonResponse peruser1(@PathVariable("pageNum")int pageNum, @PathVariable("pageSize") int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<UnitCountDTO> unitCountDTOS=personnelService.selectUnitCounts();
        PageInfo<UnitCountDTO> pageInfo = new PageInfo<>(unitCountDTOS);
        long total = pageInfo.getTotal();
        return JsonResponse.success(ResponseWIthPageInfo.builder().data(unitCountDTOS).total(total).build());
    }
}
