package com.tpay;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("accounts")
public interface AccountsServiceClient {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	String hi();
}
