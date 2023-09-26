package com.hxweaving.hxp2p.common.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "this is name", description = "this is description")
public class RestResponse<T> {

    private int code;
    private String msg;
    private T result;

    public RestResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> RestResponse<T> success() { return new RestResponse<T>();}

    public static <T> RestResponse<T> success(T result) {
        RestResponse<T> response = new RestResponse<T>();
        response.setResult(result);
        return response;
    }

    public static <T> RestResponse<T> validfail(String msg) {
        RestResponse<T> response = new RestResponse<>();
        response.setCode(-2);
        response.setMsg(msg);
        return response;
    }

    public RestResponse() { this(0, "");}

    public String getMsg() { return msg;}
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {return code;}
    public void setCode(int code) {
        this.code = code;
    }

    public T getResult() {return result;}
    public void setResult(T result) {
        this.result = result;
    }

    public Boolean isSuccessful(){return this.code == 0;}

    public String toString() {
        return "RestResponse [code=" + code + " ,msg=" + msg + ", result" + result + "]";
    }
}
