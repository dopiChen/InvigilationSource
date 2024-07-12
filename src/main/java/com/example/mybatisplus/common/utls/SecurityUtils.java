package com.example.mybatisplus.common.utls;

import com.example.mybatisplus.model.domain.User;
import com.example.mybatisplus.model.dto.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityUtils {
    /**
     * 获取当前用户
     *
     * @return
     */
    public static User getCurrentUserInfo() {
        User userInfo = SessionUtils.getCurrentUserInfo();
        //模拟登录
        if (userInfo == null) {
            userInfo = new User();
            userInfo.setLoginName("模拟");
        }
        return userInfo;
    }
    public static UserInfoDTO getUserInfo() {
        User userInfo = SessionUtils.getCurrentUserInfo();
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        //模拟登录
        if (userInfo == null) {
            userInfo = new User();
            userInfo.setLoginName("模拟用户");
            userInfoDTO.setId("10001");
            userInfoDTO.setName("模拟用户");
            userInfoDTO.setUserType(1);
        }else{

            userInfoDTO.setId(userInfo.getUsername());
            userInfoDTO.setName(userInfo.getName());
            userInfoDTO.setUserType(userInfo.getUsertype());
        }
        return userInfoDTO;
    }
}
