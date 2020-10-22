package com.topyougo.productimport.service;

import com.topyougo.productimport.dto.DataResponse;

public interface JntIntegrationService {
	public DataResponse fetchTrackingInfo(String orderNo);
}
