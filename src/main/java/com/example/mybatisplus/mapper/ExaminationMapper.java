package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.Examination;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.model.dto.ExamSignDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 考试表 Mapper 接口
 * </p>
 *
 * @author clt
 * @since 2024-07-12
 */
public interface ExaminationMapper extends BaseMapper<Examination> {


    List<ExamSignDTO> selectemsDTO(@Param("examRoom")String examRoom);

    List<ExamSignDTO> selectemsDTO1();
}
