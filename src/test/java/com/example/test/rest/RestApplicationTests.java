package com.example.test.rest;

import com.example.test.rest.dto.ProductDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestApplicationTests {

	@Test
	public void contextLoads() {
	}

	TestRestTemplate restTemplate = new TestRestTemplate();

	@LocalServerPort
	private int port;

	@Test
	public void testApi() {
		String url = "http://localhost:" + port;
		ResponseEntity<ProductDto[]> response =
				restTemplate.postForEntity(url + "/campaign/3", new ProductDto(), ProductDto[].class);
	}


	private List<ProductDto> buildProducts() {
		ProductDto product = new ProductDto();
		// TODO
		return Collections.emptyList();
	}

}
