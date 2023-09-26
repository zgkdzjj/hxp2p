package com.hxweaving.hxp2p.account.service;


import com.hxweaving.hxp2p.common.domain.CommonErrorCode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SMSServiceTest {

    @Test
    public void test1(){

        String desc = CommonErrorCode.E_100102.getDesc();
        System.out.println(desc);

        CommonErrorCode errorCode = CommonErrorCode.setErrorCode(0);
        System.out.println(errorCode);
    }
}
