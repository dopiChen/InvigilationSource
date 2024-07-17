package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.FlowResponse;
import com.example.mybatisplus.model.domain.Personnel;
import com.example.mybatisplus.mapper.PersonnelMapper;
import com.example.mybatisplus.service.PersonnelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
