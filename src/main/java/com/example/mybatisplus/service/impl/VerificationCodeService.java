package com.example.mybatisplus.service.impl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
//存储要发送的手机和验证码
@Service
public class VerificationCodeService {

    private Map<String, String> codeStorage = new HashMap<>();

    public void storeCode(String phone, String code) {
        codeStorage.put(phone, code);
    }

    public boolean verifyCode(String phone, String code) {
        return code.equals(codeStorage.get(phone));
    }
}
