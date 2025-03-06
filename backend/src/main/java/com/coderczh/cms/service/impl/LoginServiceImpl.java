package com.coderczh.cms.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderczh.cms.dao.RoleInfoDao;
import com.coderczh.cms.dao.UserInfoDao;
import com.coderczh.cms.dao.UserRoleDao;
import com.coderczh.cms.dto.login.AccountInputDto;
import com.coderczh.cms.dto.login.LoginOutputDto;
import com.coderczh.cms.entity.RoleInfo;
import com.coderczh.cms.entity.UserInfo;
import com.coderczh.cms.entity.UserRole;
import com.coderczh.cms.resp.ResultData;
import com.coderczh.cms.resp.ReturnCodeEnum;
import com.coderczh.cms.service.LoginService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserInfoDao userInfoDao;

    @Resource
    private UserRoleDao userRoleDao;

    @Resource
    private RoleInfoDao roleInfoDao;

    @Override
    public ResultData<LoginOutputDto> getLoginOutput(@Valid AccountInputDto accountInputDto) {
        // 获取用户信息
        QueryWrapper<UserInfo> userWrapper = new QueryWrapper<>();
        userWrapper.eq("account_no", accountInputDto.getAccountNo())
                .eq("password", accountInputDto.getPassword());
        UserInfo userInfo = userInfoDao.selectOne(userWrapper);
        if(userInfo == null) {
            return ResultData.fail(ReturnCodeEnum.USER_INFO_ERROR.getCode(),
                    ReturnCodeEnum.USER_INFO_ERROR.getDescription());
        }

        // 获取用户角色关联关系
        QueryWrapper<UserRole> userRoleWrapper = new QueryWrapper<>();
        userRoleWrapper.select("role_id").eq("user_id", userInfo.getId());
        UserRole userRole = userRoleDao.selectOne(userRoleWrapper);
        if(userRole == null) {
            return ResultData.fail(ReturnCodeEnum.USER_INFO_ERROR.getCode(),
                    ReturnCodeEnum.USER_INFO_ERROR.getDescription());
        }

        // 获取角色信息
        QueryWrapper<RoleInfo> roleWrapper = new QueryWrapper<>();
        roleWrapper.eq("id", userRole.getRoleId());
        RoleInfo roleInfo = roleInfoDao.selectOne(roleWrapper);
        if(roleInfo == null) {
            return ResultData.fail(ReturnCodeEnum.USER_INFO_ERROR.getCode(),
                    ReturnCodeEnum.USER_INFO_ERROR.getDescription());
        }
        return getResultData(userInfo, roleInfo);
    }

    private ResultData<LoginOutputDto> getResultData(UserInfo userInfo, RoleInfo roleInfo) {
        LoginOutputDto loginOutputDto = new LoginOutputDto();
        loginOutputDto.setUserInfo(userInfo).setRoleInfo(roleInfo).setToken(IdUtil.fastSimpleUUID());
        return ResultData.success(loginOutputDto);
    }
}
