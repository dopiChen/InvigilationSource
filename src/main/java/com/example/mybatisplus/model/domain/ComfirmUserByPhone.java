package com.example.mybatisplus.model.domain;

public class ComfirmUserByPhone {
    private String phone;
    private String username;
    private String code;

    public String getCode() {
        return code;
    }
    public String getUsername() {
        return username;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
           this.phone = phone;
    }

    public void setUsername(String username) {
          this.username = username;
    }

    public void setCode(String code) {
          this.code = code;
    }
}
