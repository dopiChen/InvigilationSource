package com.example.mybatisplus.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.model.domain.FlowItem;
import com.example.mybatisplus.model.domain.Personnel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.model.dto.PageDTO;
import com.example.mybatisplus.model.dto.UnitCountDTO;

import java.util.List;

/**
 * <p>
 * 人员表 服务类
 * </p>
 *
 * @author clt
 * @since 2024-07-12
 */
public interface PersonnelService extends IService<Personnel> {

    Page<UnitCountDTO> unitList(Personnel personnel, PageDTO dto);

    Page<Personnel> pageList(Personnel personnel, PageDTO dto);

    List<FlowItem> getperuserList(String unit);

    List<UnitCountDTO> selectUnitCounts();
}
