package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.ExamSign;
import com.example.mybatisplus.model.domain.Examination;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.model.dto.ExamSignDTO;

import java.util.List;

/**
 * <p>
 * 考试表 服务类
 * </p>
 *
 * @author clt
 * @since 2024-07-12
 */
public interface ExaminationService extends IService<Examination> {


    List<ExamSignDTO> selectemsDTO(String examRoom);

    List<ExamSignDTO> selectemsDTO1();
}
