package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.Batch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 批次表 Mapper 接口
 * </p>
 *
 * @author clt
 * @since 2024-07-12
 */
@Mapper
public interface BatchMapper extends BaseMapper<Batch> {

    List<Batch> searchBatch(@Param("keyword") String keyword);

}
