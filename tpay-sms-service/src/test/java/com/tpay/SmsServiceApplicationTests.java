package com.tpay;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = { "eureka.client.enabled=false", "authserver.secret=test" })
public class SmsServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

}
