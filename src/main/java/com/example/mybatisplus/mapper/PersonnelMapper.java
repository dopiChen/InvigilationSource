package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.FlowItem;
import com.example.mybatisplus.model.domain.FlowResponse;
import com.example.mybatisplus.model.domain.Personnel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.model.domain.Signup;
import com.example.mybatisplus.model.dto.UnitCountDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 人员表 Mapper 接口
 * </p>
 *
 * @author clt
 * @since 2024-07-12
 */
public interface PersonnelMapper extends BaseMapper<Personnel> {
    List<FlowResponse> getFlowList(@Param("unit") String unit);

    List<FlowItem> getperuserList(@Param("unit") String unit);

    List<UnitCountDTO> selectUnitCounts();
}
