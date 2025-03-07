package com.coderczh.cms.service;

import com.coderczh.cms.dto.login.AccountInputDto;
import com.coderczh.cms.dto.login.LoginOutputDto;
import com.coderczh.cms.dto.login.PhoneInputDto;
import com.coderczh.cms.resp.ResultData;

public interface LoginService {
    ResultData<LoginOutputDto> getLoginOutput(AccountInputDto accountInputDto);

    ResultData<LoginOutputDto> getLoginOutput(PhoneInputDto phoneInputDto);

    ResultData<String> getCaptcha(String phoneNo);
}
