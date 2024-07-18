package com.example.mybatisplus.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.model.domain.FinalLiist;
import com.example.mybatisplus.model.domain.Signup;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.model.dto.PageDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author clt
 * @since 2024-07-12
 */
public interface SignupService extends IService<Signup> {

    List<Signup> getExamineSignUp(String username,int usertype,int pageNum);

    List<Signup> getDisapprovedList(String username,int usertype,int pageNum);

    void allowSignUp(Signup signup);

    void disallowSignUp(Signup one,String reason);

    List<FinalLiist> getFinalNameList();

    List<Signup> getComfirmList(String username);

    List<Signup> getAllComfirms(String username);

    Page<Signup> allComfirmPageList(String username, Signup signup, PageDTO pageDTO);

    Page<Signup> searchAllComfirmPageList(String username, int examId, PageDTO pageDTO);
}
