package com.hxweaving.hxp2p.api.account.model;

import lombok.Data;

@Data
public class AccountDTO {

    private Long id;
    private String username;
    private String mobile;
    private Integer status;
    private String domain;

}
