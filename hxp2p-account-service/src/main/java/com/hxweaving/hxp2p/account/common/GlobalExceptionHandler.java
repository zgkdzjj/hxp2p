package com.hxweaving.hxp2p.account.common;

import com.hxweaving.hxp2p.common.domain.BusinessException;
import com.hxweaving.hxp2p.common.domain.CommonErrorCode;
import com.hxweaving.hxp2p.common.domain.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RestResponse exceptionGet(HttpServletRequest req, HttpServletResponse response, Exception e) {
        if(e instanceof BusinessException) {
            BusinessException be = (BusinessException) e;
            if (CommonErrorCode.CUSTOM.equals(be.getErrorCode())) {
                return new RestResponse(be.getErrorCode().getCode(), be.getMessage());
            } else {
                return new RestResponse(be.getErrorCode().getCode(), be.getErrorCode().getDesc());
            }
        } else if(e instanceof NoHandlerFoundException) {
            return new RestResponse(404, "No Resource Found");
        } else if(e instanceof HttpRequestMethodNotSupportedException) {
            return new RestResponse(405, "Method Not Support");
        } else if(e instanceof HttpMediaTypeNotSupportedException){
            return new RestResponse(415, "Not Supported Media Type");
        }

        log.error("[System Error]" + e.getMessage());

        return new RestResponse(CommonErrorCode.UNKOWN.getCode(),CommonErrorCode.UNKOWN.getDesc());


    }
}
