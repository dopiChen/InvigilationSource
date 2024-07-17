package com.example.mybatisplus.model.domain;
//分装验证码及其时间
public class CodeDetails {
    private String code;
    private long timestamp;

    public CodeDetails(String code, long timestamp) {
        this.code = code;
        this.timestamp = timestamp;
    }

    public String getCode() {
        return code;
    }

    public long getTimestamp() {
        return timestamp;
    }
}

