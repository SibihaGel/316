package org.example.service;

import org.example.entity.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserService {

    private final RestTemplate restTemplate;
    private final HttpHeaders headers;
    private final String URL = "http://94.198.50.185:7081/api/users/";

    public UserService(RestTemplate restTemplate, HttpHeaders headers) {
        this.restTemplate = restTemplate;
        this.headers = headers;
    }

    //   GET
    public String getAllUsers() {

        ResponseEntity<String> response = restTemplate.getForEntity(URL,String.class);
        headers.add("cookie", response.getHeaders().getFirst(HttpHeaders.SET_COOKIE));
        return response.getBody();
    }

    //   POST
    public ResponseEntity<String> newUser(User user) {
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        return restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
    }

    //  PUT
    public ResponseEntity<String> editUser(User user) {
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        return restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
    }

    //   DELETE
    public ResponseEntity<String> deleteUser() {
        HttpEntity<User> entity = new HttpEntity<>(null, headers);
        return restTemplate.exchange("http://94.198.50.185:7081/api/users/3", HttpMethod.DELETE, entity, String.class);
    }
}