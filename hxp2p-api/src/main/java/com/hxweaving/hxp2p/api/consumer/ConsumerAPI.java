package com.hxweaving.hxp2p.api.consumer;

import com.hxweaving.hxp2p.api.consumer.model.ConsumerDTO;
import com.hxweaving.hxp2p.api.consumer.model.ConsumerRegisterDTO;
import com.hxweaving.hxp2p.common.domain.RestResponse;

public interface ConsumerAPI {

    RestResponse<ConsumerDTO> register(ConsumerRegisterDTO consumerRegisterDTO);
}
