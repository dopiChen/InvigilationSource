package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.Personnel;
import com.example.mybatisplus.mapper.PersonnelMapper;
import com.example.mybatisplus.service.PersonnelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
