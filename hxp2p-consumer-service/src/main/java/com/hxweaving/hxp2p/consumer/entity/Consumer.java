package com.hxweaving.hxp2p.consumer.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("consumer")
public class Consumer {

    private Long id;

    @TableField("USERNAME")
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

    private String requestNo;

}
