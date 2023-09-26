package com.hxweaving.hxp2p.consumer.agent;


import com.hxweaving.hxp2p.api.account.model.AccountDTO;
import com.hxweaving.hxp2p.api.account.model.AccountRegisterDTO;
import com.hxweaving.hxp2p.common.domain.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("account-service")
public interface AccountAPIAgent {

    @PostMapping("/account/l/accounts")
    RestResponse<AccountDTO> register(@RequestBody AccountRegisterDTO accountRegisterDTO);
}
