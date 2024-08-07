package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.FinalLiist;
import com.example.mybatisplus.model.domain.Signup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.model.dto.PersonnelExaminationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author clt
 * @since 2024-07-12
 */
@Mapper
public interface SignupMapper extends BaseMapper<Signup> {

    List<Signup> getExamineSignUp(@Param("username") String username, @Param("usertype") int usertype);

    List<Signup> getExamineSignUpByKeyword(@Param("username") String username, @Param("usertype") int usertype, @Param("keyword") String keyword);

    List<Signup> getDisapprovedList(@Param("username") String username, @Param("usertype") int usertype);

    List<Signup> getDisapprovedListByKeyword(@Param("username") String username, @Param("usertype") int usertype, @Param("keyword") String keyword);

    List<FinalLiist> getFinalNameList();

    List<FinalLiist> getFinalNameListByKeyword(@Param("keyword") String keyword);

    List<Signup> getComfirmList(@Param("username") String username);

    List<Signup> getAllComfirms(@Param("username") String username);

    List<PersonnelExaminationDTO> getFinalNameListdto();
}
