package com.coderczh.cms.dto.login;

import com.coderczh.cms.entity.RoleInfo;
import com.coderczh.cms.entity.UserInfo;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginOutputDto {
    private UserInfo userInfo;
    private RoleInfo roleInfo;
    private String token;
}
