package com.hxweaving.hxp2p.api.account.model;

import lombok.Data;

@Data
public class AccountLoginDTO {

    private String username;
    private String mobile;
    private String password;
    private String domain;
}
