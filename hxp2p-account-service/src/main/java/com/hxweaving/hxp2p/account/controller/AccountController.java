package com.hxweaving.hxp2p.account.controller;

import com.hxweaving.hxp2p.account.service.AccountService;
import com.hxweaving.hxp2p.api.account.AccountAPI;
import com.hxweaving.hxp2p.api.account.model.AccountDTO;
import com.hxweaving.hxp2p.api.account.model.AccountLoginDTO;
import com.hxweaving.hxp2p.api.account.model.AccountRegisterDTO;
import com.hxweaving.hxp2p.common.domain.RestResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class AccountController implements AccountAPI {

    @GetMapping
    public String test(){
        return "Hello World, Account";
    }

    @Autowired
    AccountService accountService;

    @Override
    @GetMapping("/sms/{mobile}")
    public RestResponse getSMSCode( @PathVariable String mobile) {

        RestResponse smsCode = accountService.getSMSCode(mobile);
        return smsCode;
    }

    @GetMapping(value = "/mobile/{mobile}/key/{key}/code/{code}")
    @Override
    public RestResponse validateSMSCode(@PathVariable String mobile, @PathVariable String key, @PathVariable String code) {
        Integer result = accountService.validateSMSCode(mobile, key, code);

        return RestResponse.success(result);
    }

    @PostMapping("/l/accounts")
    @Override
    public RestResponse<AccountDTO> register(@RequestBody  AccountRegisterDTO accountRegisterDTO) {

        AccountDTO accountDTO = accountService.register(accountRegisterDTO);

        return RestResponse.success(accountDTO);
    }

    @PostMapping("/l/accounts/session")
    @Override
    public RestResponse<AccountDTO> login(@RequestBody AccountLoginDTO accountLoginDTO) {
        AccountDTO accountDTO = accountService.login(accountLoginDTO);
        return RestResponse.success(accountDTO);
    }
}
