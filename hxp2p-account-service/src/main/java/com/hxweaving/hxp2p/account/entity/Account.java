package com.hxweaving.hxp2p.account.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Account {

    @TableId(value = "ID",type = IdType.AUTO)
    private Long id;

    private String username;

    @TableField("MOBILE")
    private String mobile;

    private String password;

    private String salt;

    private Integer status;

    private String domain;

}
