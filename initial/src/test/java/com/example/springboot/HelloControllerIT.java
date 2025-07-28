package com.example.springboot;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerIT {

	@Autowired
	private TestRestTemplate template;

    @Test
    public void getHello() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/", String.class);
        assertThat(response.getBody()).isEqualTo("Greetings from Spring Boot!");
    }

    @Test
    public void getTest() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/test/", String.class);
        assertThat(response.getBody()).isEqualTo("Greetings from Spring Boot! testing changes");
    }

    @Test
    public void getBooks() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/books/", String.class);
        assertThat(response.getBody()).isEqualTo("Welcome to the main page! Working on frontend & input :)");
    }
}