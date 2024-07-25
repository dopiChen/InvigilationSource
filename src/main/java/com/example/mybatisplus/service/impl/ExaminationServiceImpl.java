package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.ExamSign;
import com.example.mybatisplus.model.domain.Examination;
import com.example.mybatisplus.mapper.ExaminationMapper;
import com.example.mybatisplus.model.domain.FlowItem;
import com.example.mybatisplus.model.dto.ExamSignDTO;
import com.example.mybatisplus.service.ExaminationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private ExaminationMapper examinationMapper;

    @Override
    public List<ExamSignDTO> selectemsDTO(String examRoom) {
        return examinationMapper.selectemsDTO(examRoom);
    }

    @Override
    public List<ExamSignDTO> selectemsDTO1() {
        return examinationMapper.selectemsDTO1();
    }

    @Override
    public List<Integer> getByBatchId(long id) {
        return examinationMapper.getByBacthId(id);
    }
}
