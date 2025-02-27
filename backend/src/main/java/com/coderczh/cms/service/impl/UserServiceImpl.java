package com.coderczh.cms.service.impl;

import com.coderczh.cms.dao.UserInfoDao;
import com.coderczh.cms.entity.UserInfo;
import com.coderczh.cms.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo getUserInfo(UserInfo userInfo) {
        return new UserInfo();
    }
}
