package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mybatisplus.model.domain.FinalLiist;
import com.example.mybatisplus.model.domain.Signup;
import com.example.mybatisplus.mapper.SignupMapper;
import com.example.mybatisplus.service.SignupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
    public List<Signup> getExamineSignUp(String username, int usertype,int pageNum) {
        PageHelper.startPage(pageNum,2);
        return signupMapper.getExamineSignUp(username, usertype);
    }

    @Override
    public List<Signup> getDisapprovedList(String username, int usertype,int pageNum) {
        PageHelper.startPage(pageNum,2);
        List<Signup> list = signupMapper.getDisapprovedList(username,usertype);
        PageInfo<Signup> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getPages();

        return signupMapper.getDisapprovedList(username,usertype);
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
    public List<Signup> getComfirmList(String username) {
        return signupMapper.getComfirmList(username);
    }

    @Override
    public List<Signup> getAllComfirms(String username) {
        return signupMapper.getAllComfirms(username);
    }


}
