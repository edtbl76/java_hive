package com.example.multimodule.application;

import com.example.multimodule.library.MyService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private MyService myService;

	@Test
	public void myServiceShouldNotBeNull() {
		Assertions.assertThat(myService.message()).isNotNull();
	}

}
