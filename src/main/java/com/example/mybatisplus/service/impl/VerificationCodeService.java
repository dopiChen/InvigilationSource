package com.example.mybatisplus.service.impl;
import com.example.mybatisplus.model.domain.CodeDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
//存储要发送的手机和验证码
@Service
public class VerificationCodeService {

    private Map<String, CodeDetails> codeStorage = new HashMap<>();
    private static final long EXPIRATION_TIME = 60 * 1000; // 1 minute in milliseconds

    public void storeCode(String phone, String code) {
        CodeDetails codeDetails = new CodeDetails(code, System.currentTimeMillis());
        codeStorage.put(phone, codeDetails);
    }

    public boolean verifyCode(String phone, String code) {
        CodeDetails codeDetails = codeStorage.get(phone);
        if (codeDetails == null) {
            return false;
        }
        long currentTime = System.currentTimeMillis();
        // 检查验证码是否失效
        if (currentTime - codeDetails.getTimestamp() > EXPIRATION_TIME) {
            codeStorage.remove(phone); // Remove expired code
            return false;
        }
        return code.equals(codeDetails.getCode());
    }
}
