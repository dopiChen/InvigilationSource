package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.Examination;
import com.example.mybatisplus.mapper.ExaminationMapper;
import com.example.mybatisplus.service.ExaminationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 考试表 服务实现类
 * </p>
 *
 * @author clt
 * @since 2024-07-12
 */
@Service
public class ExaminationServiceImpl extends ServiceImpl<ExaminationMapper, Examination> implements ExaminationService {

}
