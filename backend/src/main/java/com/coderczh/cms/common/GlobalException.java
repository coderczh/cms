package com.coderczh.cms.common;

import com.alibaba.fastjson2.JSONObject;
import com.coderczh.cms.resp.ResultData;
import com.coderczh.cms.resp.ReturnCodeEnum;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@SuppressWarnings("all")
public class GlobalException {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultData<Object> paramException(ConstraintViolationException exception) {
        return ResultData.fail(ReturnCodeEnum.PARAM_ERROR.getCode(), ReturnCodeEnum.PARAM_ERROR.getDescription());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultData<JSONObject> systemException(Exception exception) {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), ReturnCodeEnum.RC500.getDescription());
    }
}
