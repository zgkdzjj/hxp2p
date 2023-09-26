package com.hxweaving.hxp2p.api.consumer.model;

import lombok.Data;

@Data
public class ConsumerRegisterDTO {

    private String username;

    private String mobile;

    private String password;

    // B: Borrower, I: Investor
    private String role;

    // 验证码
    private String key;
}
