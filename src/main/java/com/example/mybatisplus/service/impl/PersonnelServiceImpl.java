package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.model.domain.FlowItem;
import com.example.mybatisplus.model.domain.FlowResponse;
import com.example.mybatisplus.model.domain.Personnel;
import com.example.mybatisplus.mapper.PersonnelMapper;
import com.example.mybatisplus.model.dto.PageDTO;
import com.example.mybatisplus.model.dto.PerUserDTO;
import com.example.mybatisplus.model.dto.UnitCountDTO;
import com.example.mybatisplus.service.PersonnelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 人员表 服务实现类
 * </p>
 *
 * @author clt
 * @since 2024-07-12
 */
@Service
public class PersonnelServiceImpl extends ServiceImpl<PersonnelMapper, Personnel> implements PersonnelService {
    @Autowired
    private PersonnelMapper personnelMapper;

    public List<FlowResponse> getFlowList(String unit){
        return personnelMapper.getFlowList(unit);
    }

    @Override
    public Page<UnitCountDTO> unitList(Personnel personnel, PageDTO dto) {
        QueryWrapper<Personnel> wrapper = new QueryWrapper<>();
        wrapper.select("unit", "COUNT(DISTINCT username) AS count")
                .groupBy("unit");
        Page<UnitCountDTO> page = new Page<>(dto.getPageNo(), dto.getPageSize());
        List<UnitCountDTO> records = baseMapper.selectMaps(wrapper)
                .stream()
                .map(map -> {
                    UnitCountDTO dto1 = new UnitCountDTO();
                    dto1.setUnit((String) map.get("unit"));
                    dto1.setCount(((Long) map.get("count")).intValue());
                    return dto1;
                }).collect(Collectors.toList());
        page.setRecords(records);
        return page;
    }

    @Override
    public Page<Personnel> pageList(Personnel personnel, PageDTO dto) {
        QueryWrapper<Personnel> wrapper=new QueryWrapper<>();
        if (personnel != null && personnel.getUnit() != null && !personnel.getUnit().isEmpty()) {
            wrapper.like("unit", personnel.getUnit());
        }
        Page<Personnel> page=new Page<>();
        page.setCurrent(dto.getPageNo()).setSize(dto.getPageSize());
        baseMapper.selectPage(page,wrapper);
        return page;
    }

    @Override
    public List<FlowItem> getperuserList(String unit) {
        return personnelMapper.getperuserList(unit);
    }

    @Override
    public List<UnitCountDTO> selectUnitCounts() {
        return personnelMapper.selectUnitCounts();
    }

    @Override
    public List<PerUserDTO> getperuserListdto(String unit) {
        return personnelMapper.getperuserListdto(unit);
    }


}
