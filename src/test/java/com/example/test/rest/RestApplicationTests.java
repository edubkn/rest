package com.example.test.rest;

import com.example.test.rest.dto.ProductDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestApplicationTests {

	@Test
	public void contextLoads() {
	}

	private TestRestTemplate restTemplate = new TestRestTemplate();
	private ObjectMapper om = new ObjectMapper();

	@LocalServerPort
	private int port;

	@Test
	public void testApi() throws IOException {
		String url = "http://localhost:" + port;
		ProductDto[] product = om.readValue(new ClassPathResource("request.json").getInputStream(), ProductDto[].class);
		ResponseEntity<ProductDto[]> response =
				restTemplate.postForEntity(url + "/product/insert", product, ProductDto[].class);

	}


	private List<ProductDto> buildProducts() {
		ProductDto product = new ProductDto();
		// TODO
		return Collections.emptyList();
	}

}
