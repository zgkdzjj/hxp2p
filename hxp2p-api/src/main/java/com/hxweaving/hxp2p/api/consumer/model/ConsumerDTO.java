package com.hxweaving.hxp2p.api.consumer.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ConsumerDTO {

    private Long id;

    private String username;

    private String fullname;

    private String idNumber;

    private String userNo;

    private String mobile;

    private String userType;

    private String role;

    private String authList;

    private Integer isBindCard;

    private Integer status;

    private BigDecimal loanAmount;

}
