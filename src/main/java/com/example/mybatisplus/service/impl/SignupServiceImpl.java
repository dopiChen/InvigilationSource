package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mybatisplus.model.domain.Signup;
import com.example.mybatisplus.mapper.SignupMapper;
import com.example.mybatisplus.service.SignupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
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
    public List<Signup> getExamineSignUp(int usertype) {
        return signupMapper.getExamineSignUp(usertype);
    }

    @Override
    public Signup allowSignUp(Signup signup) {
        UpdateWrapper<Signup> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("exam_id",signup.getExamId()).eq("username",signup.getUsername())
                .set("approval_status",signup.getApprovalStatus()+1);
        signupMapper.update(signup,updateWrapper);
    }
}
