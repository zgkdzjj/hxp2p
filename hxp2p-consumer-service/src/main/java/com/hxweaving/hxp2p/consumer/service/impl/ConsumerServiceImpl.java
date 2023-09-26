package com.hxweaving.hxp2p.consumer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxweaving.hxp2p.api.account.model.AccountDTO;
import com.hxweaving.hxp2p.api.account.model.AccountRegisterDTO;
import com.hxweaving.hxp2p.api.consumer.model.ConsumerDTO;
import com.hxweaving.hxp2p.api.consumer.model.ConsumerRegisterDTO;
import com.hxweaving.hxp2p.common.domain.BusinessException;
import com.hxweaving.hxp2p.common.domain.CodePrefixCode;
import com.hxweaving.hxp2p.common.domain.CommonErrorCode;
import com.hxweaving.hxp2p.common.domain.RestResponse;
import com.hxweaving.hxp2p.common.util.CodeNoUtil;
import com.hxweaving.hxp2p.consumer.agent.AccountAPIAgent;
import com.hxweaving.hxp2p.consumer.common.ConsumerErrorCode;
import com.hxweaving.hxp2p.consumer.entity.Consumer;
import com.hxweaving.hxp2p.consumer.mapper.ConsumerMapper;
import com.hxweaving.hxp2p.consumer.service.ConsumerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl extends ServiceImpl<ConsumerMapper, Consumer> implements ConsumerService {

    @Autowired
    private AccountAPIAgent accountAPIAgent;

    @Override
    public Integer checkMobile(String mobile) {
        return getByMobile(mobile) == null ? 0 : 1;
    }

    private ConsumerDTO getByMobile(String mobile) {
        QueryWrapper<Consumer> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Consumer::getMobile, mobile);
        Consumer consumer = getOne(wrapper);
        // Consumer => ConsumerDTO
        return convertConsumerEntityToDTO(consumer);
    }

    private ConsumerDTO convertConsumerEntityToDTO(Consumer consumer) {
        if(consumer == null) {
            return null;
        }
        ConsumerDTO consumerDTO = new ConsumerDTO();
        BeanUtils.copyProperties(consumer, consumerDTO);
        return consumerDTO;
    }


    @Override
    public void register(ConsumerRegisterDTO consumerRegisterDTO) {

        if(checkMobile(consumerRegisterDTO.getMobile()) == 1) {
            throw new BusinessException(ConsumerErrorCode.E_140107);
        }

        Consumer consumer = new Consumer();
        BeanUtils.copyProperties(consumerRegisterDTO,consumer);

        consumer.setUsername(CodeNoUtil.getNo(CodePrefixCode.CODE_NO_PREFIX));
        consumerRegisterDTO.setUsername(consumer.getUsername());
        consumer.setUserNo(CodeNoUtil.getNo(CodePrefixCode.CODE_REQUEST_PREFIX));
        consumer.setIsBindCard(0);
        save(consumer);

        // Call to account service
        AccountRegisterDTO accountRegisterDTO = new AccountRegisterDTO();
        BeanUtils.copyProperties(consumerRegisterDTO, accountRegisterDTO );
        RestResponse<AccountDTO> restResponse = accountAPIAgent.register(accountRegisterDTO);
        if(restResponse.getCode() != CommonErrorCode.SUCCESS.getCode()) {
            throw new BusinessException(ConsumerErrorCode.E_140106);
        }



    }
}
