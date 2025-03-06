package com.coderczh.cms.dto.login;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AccountInputDto {

    @NotBlank(message = "帐号不能为空")
    @Size(min = 6, message = "帐号长度错误")
    private String accountNo;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, message = "密码长度错误")
    private String password;
}
