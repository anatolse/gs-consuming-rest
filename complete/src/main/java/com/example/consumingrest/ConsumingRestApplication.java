package com.example.consumingrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

@SpringBootApplication
public class ConsumingRestApplication {

	private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ConsumingRestApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			//String request = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"wallet_status\"}";
			String request = "{\"jsonrpc\":\"2.0\",\"id\":123,\"method\":\"tx_send\",\"params\":{\"value\":12342342,\"address\":\"472e17b0419055ffee3b3813b98ae671579b0ac0dcd6f1a23b11a75ab148cc67\"}}";
			HttpEntity<String> entity = new HttpEntity<String>(request, headers);

			
			ResponseEntity<String> response = restTemplate.exchange("http://127.0.0.1:9100/api/wallet", HttpMethod.POST, entity, String.class);
			
			log.info(response.getBody());
		};
	}
}
