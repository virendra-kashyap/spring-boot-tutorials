package com.vulnerabilities.spring;

import java.net.URI;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.vulnerabilities.spring.model.User;

@SpringBootTest(classes = SpringVulnerabilitiesZapServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
class UserApiTest {

	@LocalServerPort
	private int port;

	private TestRestTemplate restTemp;
	private final HttpHeaders headers = new HttpHeaders();
	private final User user = new User();

	public URI loadUri(String uri) {
		return URI.create(String.format("http://localhost:%s%s", port, uri));
	}

	@BeforeEach
	public void setup() {
		restTemp = new TestRestTemplate();
		headers.setContentType(MediaType.APPLICATION_JSON);
		user.setName("Virendra");
		user.setBirthDate(LocalDate.of(2012, 12, 23));
		user.setEmail("virendra@gmail.com");
		user.setAddress("Indore");
	}

	@Test
	@Order(1)
	void whenValidUserAdd() {
		final ResponseEntity<Object> reply = restTemp.exchange(loadUri("/users"), HttpMethod.POST,
				new HttpEntity<>(user, headers), Object.class);
		Assertions.assertEquals(HttpStatus.CREATED.value(), reply.getStatusCode().value());
		Assertions.assertNotNull(reply.getHeaders().getLocation());
	}

	@Test
	@Order(2)
	void whenValidGetId() {
		final ResponseEntity<Object> reply = restTemp.exchange(loadUri("/users/1"), HttpMethod.GET,
				new HttpEntity<>(headers), Object.class);
		Assertions.assertEquals(HttpStatus.OK.value(), reply.getStatusCode().value());
	}

	@Test
	@Order(3)
	void whenValidSearch() {
		final ResponseEntity<Object> reply = restTemp.exchange(loadUri("/users"), HttpMethod.GET,
				new HttpEntity<>(headers), Object.class);
		Assertions.assertEquals(HttpStatus.OK.value(), reply.getStatusCode().value());
	}

	@Test
	@Order(4)
	void whenValidUpdate() {
		user.setName("Somesh");
		final ResponseEntity<Object> reply = restTemp.exchange(loadUri("/users/1"), HttpMethod.PUT,
				new HttpEntity<>(user, headers), Object.class);
		Assertions.assertEquals(HttpStatus.ACCEPTED.value(), reply.getStatusCode().value());
	}

	@Test
	@Order(5)
	void whenValidDelete() {
		final ResponseEntity<Object> reply = restTemp.exchange(loadUri("/users/1"), HttpMethod.DELETE, HttpEntity.EMPTY,
				Object.class);
		Assertions.assertEquals(HttpStatus.CREATED.value(), reply.getStatusCode().value());
	}
}
