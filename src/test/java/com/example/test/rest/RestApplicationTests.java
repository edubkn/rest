package com.example.test.rest;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.example.test.rest.dto.ProductDto;
import com.example.test.rest.dto.ProductResponseDto;
import com.example.test.rest.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestApplicationTests {


	private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

	private final TestRestTemplate restTemplate = new TestRestTemplate();
	private final ObjectMapper om = new ObjectMapper();

	@LocalServerPort
	private int port;

	@Test
	public void testApi() throws IOException {
		String url = "http://localhost:" + port;
		ProductDto[] product = om.readValue(new ClassPathResource("request.json").getInputStream(), ProductDto[].class);
		ResponseEntity<ProductResponseDto[]> response =
				restTemplate.postForEntity(url + "/product/insert", product, ProductResponseDto[].class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		LOG.info(om.writeValueAsString(response.getBody()));
	}

}
