package com.hxweaving.hxp2p.account.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.hxweaving.hxp2p.account.common.AccountErrorCode;
import com.hxweaving.hxp2p.common.domain.BusinessException;
import com.hxweaving.hxp2p.common.domain.CommonErrorCode;
import com.hxweaving.hxp2p.common.domain.RestResponse;
import com.hxweaving.hxp2p.common.util.OkHttpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SMSService {

    @Value("${sms.url}")
    private String smsURL;

    @Value("${sms.enable}")
    private boolean smsEnable;

    public RestResponse getSMSCode(String mobile) {

        String urlWithParams = smsURL + "generate?effectiveTime=300&name=sms";
        JSONObject mobileObj = new JSONObject();
        mobileObj.put("mobile", mobile);
        if(smsEnable){
            return OkHttpUtil.post(urlWithParams, JSON.toJSONString(mobileObj));
        }
        return RestResponse.success();
    }

    public void validateSMSCode(String key, String code) {
        if(smsEnable) {
            StringBuilder params = new StringBuilder("verify?name=sms");
            params.append("&verificationKey=").append(key);
            params.append("&verificationCode=").append(code);
            String urlWithParams = smsURL + params;
            RestResponse smsResponse = OkHttpUtil.post(urlWithParams, "");
            if (smsResponse.getCode() != CommonErrorCode.SUCCESS.getCode() ||
                    smsResponse.getResult().toString().equalsIgnoreCase("false")) {
                throw new BusinessException(AccountErrorCode.E_140152);

            }
        }
    }
}
