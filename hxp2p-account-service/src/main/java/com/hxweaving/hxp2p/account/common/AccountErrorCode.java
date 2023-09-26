package com.hxweaving.hxp2p.account.common;

import com.hxweaving.hxp2p.common.domain.ErrorCode;

public enum AccountErrorCode implements ErrorCode {
    E_130101(130101, "用户名已存在"),
    E_130104(130104, "用户未注册"),
    E_130105(130105, "用户名或密码错误"),
    E_140141(140141,"注册失败"),

    E_140151(140151,"获取短信验证码失败"),
    E_140152(140152,"验证码错误"),
    ;

    private int code;
    private String desc;


    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    AccountErrorCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AccountErrorCode setErrorCode(int code) {
        for (AccountErrorCode errorCode : AccountErrorCode.values()) {
            if(errorCode.getCode() == code){
                return errorCode;
            }
        }
        return null;
    }
}
