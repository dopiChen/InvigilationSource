package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.Signup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author clt
 * @since 2024-07-12
 */
@Mapper
public interface SignupMapper extends BaseMapper<Signup> {

    List<Signup> getExamineSignUp(@Param("usertype") int usertype);
}
