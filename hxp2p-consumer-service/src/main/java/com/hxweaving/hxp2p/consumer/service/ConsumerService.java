package com.hxweaving.hxp2p.consumer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hxweaving.hxp2p.api.consumer.model.ConsumerRegisterDTO;
import com.hxweaving.hxp2p.consumer.entity.Consumer;

public interface ConsumerService extends IService<Consumer> {

    /**
     * Check a consumer exists or not by mobile
     * @param mobile
     * @return
     */
    Integer checkMobile(String mobile);


    void register(ConsumerRegisterDTO consumerRegisterDTO);
}
