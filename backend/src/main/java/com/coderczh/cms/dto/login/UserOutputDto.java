package com.coderczh.cms.dto.login;

import com.coderczh.cms.entity.RoleInfo;
import com.coderczh.cms.entity.UserInfo;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserOutputDto {
    public UserInfo userInfo;
    public RoleInfo roleInfo;
    public String token;
}
