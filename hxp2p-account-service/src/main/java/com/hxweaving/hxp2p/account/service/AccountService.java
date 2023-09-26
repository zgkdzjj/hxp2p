package com.hxweaving.hxp2p.account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hxweaving.hxp2p.account.entity.Account;
import com.hxweaving.hxp2p.api.account.model.AccountDTO;
import com.hxweaving.hxp2p.api.account.model.AccountLoginDTO;
import com.hxweaving.hxp2p.api.account.model.AccountRegisterDTO;
import com.hxweaving.hxp2p.common.domain.RestResponse;

public interface AccountService extends IService<Account> {

    RestResponse getSMSCode(String mobile);

    Integer validateSMSCode(String mobile, String key, String code);

    AccountDTO register(AccountRegisterDTO accountRegisterDTO);

    AccountDTO login(AccountLoginDTO accountLoginDTO);
}
