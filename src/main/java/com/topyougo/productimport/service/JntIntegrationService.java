package com.topyougo.productimport.service;

import com.topyougo.productimport.dto.DataResponse;

@FunctionalInterface
public interface JntIntegrationService {

    abstract DataResponse fetchTrackingInfo(String orderNo);
}
