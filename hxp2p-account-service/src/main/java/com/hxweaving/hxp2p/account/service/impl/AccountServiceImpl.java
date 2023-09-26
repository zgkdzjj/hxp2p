package com.hxweaving.hxp2p.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxweaving.hxp2p.account.common.AccountErrorCode;
import com.hxweaving.hxp2p.account.entity.Account;
import com.hxweaving.hxp2p.account.mapper.AccountMapper;
import com.hxweaving.hxp2p.account.service.AccountService;
import com.hxweaving.hxp2p.account.service.SMSService;
import com.hxweaving.hxp2p.api.account.model.AccountDTO;
import com.hxweaving.hxp2p.api.account.model.AccountLoginDTO;
import com.hxweaving.hxp2p.api.account.model.AccountRegisterDTO;
import com.hxweaving.hxp2p.common.domain.BusinessException;
import com.hxweaving.hxp2p.common.domain.RestResponse;
import com.hxweaving.hxp2p.common.domain.StatusCode;
import com.hxweaving.hxp2p.common.util.PasswordUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Autowired
    SMSService smsService;

    @Value("${sms.enable}")
    private boolean smsEnable;


    @Override
    public RestResponse getSMSCode(String mobile) {
        RestResponse smsCode = smsService.getSMSCode(mobile);
        return smsCode;
    }

    @Override
    public Integer validateSMSCode(String mobile, String key, String code) {
        // TODO: mobile could be different(still in the table) with the one which receives code
        // The logic could be improved
        smsService.validateSMSCode(key, code);
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Account::getMobile, mobile);
        int count = (int) count(wrapper);
        return count > 0 ? 1: 0;

    }

    @Override
    public AccountDTO register(AccountRegisterDTO accountRegisterDTO) {
        Account account = new Account();
        account.setUsername(accountRegisterDTO.getUsername());
        account.setMobile(accountRegisterDTO.getMobile());
        account.setPassword(PasswordUtil.generate(accountRegisterDTO.getPassword()));
        if(smsEnable) {
            account.setPassword(PasswordUtil.generate(accountRegisterDTO.getMobile()));
        }
        account.setDomain("c");
        account.setStatus(StatusCode.STATUS_IN.getCode());
        save(account);
        return covertAccountEntityToDTO(account);
    }

    @Override
    public AccountDTO login(AccountLoginDTO accountLoginDTO) {
        Account account = null;
        if(accountLoginDTO.getDomain().equalsIgnoreCase("c")) {
            account = getAccountByMobile(accountLoginDTO.getMobile());
        } else {
            account = getAccountByUsername(accountLoginDTO.getUsername());
        }
        if(account == null) {
            throw new BusinessException(AccountErrorCode.E_130104);
        }

        //  account => accountDTO
        AccountDTO accountDTO = covertAccountEntityToDTO(account);
        if(smsEnable) {
            return accountDTO;
        }

        if (PasswordUtil.verify(accountLoginDTO.getPassword(), account.getPassword())) {
            return accountDTO;
        }
        throw new BusinessException(AccountErrorCode.E_130105);
    }

    private Account getAccountByUsername(String username) {
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Account::getUsername, username);
        Account account = getOne(queryWrapper);
        return account;
    }

    private Account getAccountByMobile(String mobile) {
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Account::getMobile,mobile);
        Account account = getOne(queryWrapper);
        return account;
    }

    private AccountDTO covertAccountEntityToDTO(Account entity) {

        if (entity == null) {
            return null;
        }

        AccountDTO accountDTO = new AccountDTO();
        BeanUtils.copyProperties(entity,accountDTO);
        return accountDTO;
    }
}
