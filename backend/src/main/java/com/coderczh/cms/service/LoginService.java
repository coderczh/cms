package com.coderczh.cms.service;

import com.coderczh.cms.dto.login.AccountInputDto;
import com.coderczh.cms.dto.login.LoginOutputDto;
import com.coderczh.cms.resp.ResultData;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface LoginService {
    ResultData<LoginOutputDto> getLoginOutput(@Valid AccountInputDto accountInputDto);
}
