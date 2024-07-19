package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.model.domain.FinalLiist;
import com.example.mybatisplus.model.domain.Signup;
import com.example.mybatisplus.mapper.SignupMapper;
import com.example.mybatisplus.model.dto.PageDTO;
import com.example.mybatisplus.model.dto.PersonnelExaminationDTO;
import com.example.mybatisplus.service.SignupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author clt
 * @since 2024-07-12
 */
@Service
public class SignupServiceImpl extends ServiceImpl<SignupMapper, Signup> implements SignupService {
    @Autowired
    private SignupMapper signupMapper;


    @Override
    public List<Signup> getExamineSignUp(String username, int usertype) {
        return signupMapper.getExamineSignUp(username, usertype);
    }

    @Override
    public List<Signup> getExamineSignUpByKeyword(String username, int usertype, String keyword) {
        return signupMapper.getExamineSignUpByKeyword(username, usertype, keyword);
    }

    @Override
    public List<Signup> getDisapprovedList(String username, int usertype) {
        return signupMapper.getDisapprovedList(username, usertype);
    }

    public List<Signup> getDisapprovedListByKeyword(String username, int usertype,String keyword) {
        return signupMapper.getDisapprovedListByKeyword(username, usertype,keyword);
    }


    @Override
    public void allowSignUp(Signup signup) {
        UpdateWrapper<Signup> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("exam_id", signup.getExamId()).eq("username", signup.getUsername())
                .set("approval_status", signup.getApprovalStatus() + 1);
        signupMapper.update(signup, updateWrapper);
    }

    @Override
    public void disallowSignUp(Signup one, String reason) {
        UpdateWrapper<Signup> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("exam_id", one.getExamId()).eq("username", one.getUsername()).set("is_out", 1).set("reson", reason);
        ;
        signupMapper.update(one, updateWrapper);
    }

    @Override
    public List<FinalLiist> getFinalNameList() {
        return signupMapper.getFinalNameList();
    }

    @Override
    public List<FinalLiist> getFinalNameListByKeyword(String keyword) {
        return signupMapper.getFinalNameListByKeyword(keyword);
    }

    @Override
    public List<Signup> getComfirmList(String username) {
        return signupMapper.getComfirmList(username);
    }

    @Override
    public List<Signup> getAllComfirms(String username) {
        return signupMapper.getAllComfirms(username);
    }

    @Override
    public Page<Signup> allComfirmPageList(String username, Signup signup, PageDTO pageDTO) {
        QueryWrapper<Signup> wrapper = new QueryWrapper<>();
        wrapper.eq("approval_status", 5).eq("username", username);
        Page<Signup> page = new Page<>();
        page.setCurrent(pageDTO.getPageNo()).setSize(pageDTO.getPageSize());
        baseMapper.selectPage(page, wrapper);
        return page;
    }

    @Override
    public Page<Signup> searchAllComfirmPageList(String username, int examId, PageDTO pageDTO) {
        QueryWrapper<Signup> wrapper = new QueryWrapper<>();
        wrapper.eq("approval_status", 5).eq("username", username).eq("exam_id", examId);
        Page<Signup> page = new Page<>();
        page.setCurrent(pageDTO.getPageNo()).setSize(pageDTO.getPageSize());
        baseMapper.selectPage(page, wrapper);
        return page;
    }

    @Override
    public List<PersonnelExaminationDTO> getFinalNameListdto() {
        return signupMapper.getFinalNameListdto();
    }


}
