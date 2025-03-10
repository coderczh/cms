package com.coderczh.cms.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderczh.cms.dao.UserInfoDao;
import com.coderczh.cms.dao.UserRoleDao;
import com.coderczh.cms.dto.registry.RegistryInputDto;
import com.coderczh.cms.entity.UserInfo;
import com.coderczh.cms.entity.UserRole;
import com.coderczh.cms.resp.ResultData;
import com.coderczh.cms.resp.ReturnCodeEnum;
import com.coderczh.cms.service.RegistryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class RegistryServiceImpl implements RegistryService {

    @Resource
    private UserInfoDao userInfoDao;

    @Resource
    private UserRoleDao userRoleDao;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData<String> registry(RegistryInputDto registryInputDto) {
        UserInfo userInfo = Convert.convert(UserInfo.class, registryInputDto);
        QueryWrapper<UserInfo> accountWrapper = new QueryWrapper<>();
        accountWrapper.eq("account_no", userInfo.getAccountNo())
                .eq("password", userInfo.getPassword());
        QueryWrapper<UserInfo> phoneWrapper = new QueryWrapper<>();
        phoneWrapper.eq("phone_no", userInfo.getPhoneNo());
        if (userInfoDao.selectCount(accountWrapper) == 0L && userInfoDao.selectCount(phoneWrapper) == 0) {
            userInfo.setCreateDate(LocalDate.now());
            userInfoDao.insert(userInfo);
            UserRole userRole = new UserRole();
            userRole.setUserId(userInfo.getId()).setRoleId(1);
            userRoleDao.insert(userRole);
        } else {
            return ResultData.fail(ReturnCodeEnum.USER_EXIST_ERROR.getCode(),
                    ReturnCodeEnum.USER_EXIST_ERROR.getDescription());
        }
        return ResultData.success("注册成功");
    }
}
