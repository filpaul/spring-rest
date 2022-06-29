package com.example.springrest;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

public class SpringRestApplication {

    private static final String URL = "http://94.198.50.185:7081/api/users";

    private static final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<List> responseEntity = getAllUsers(requestEntity); ;
        headers.set("Cookie", String.join(";", Objects.requireNonNull(responseEntity.getHeaders().get("Set-Cookie"))));
        System.out.println(headers);

        User user1 = new User();
        user1.setId(3L);
        user1.setName("James");
        user1.setLastName("Brown");
        user1.setAge((byte) 55);

        HttpEntity<User> entity = new HttpEntity<>(user1, headers);
        createUser(entity);

        user1.setName("Thomas");
        user1.setLastName("Shelby");
        entity = new HttpEntity<>(user1, headers);
        updateUser(entity);

        deleteUser(entity);
    }

    private static ResponseEntity<List> getAllUsers(HttpEntity<Object> requestEntity) {
        ResponseEntity<List> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, requestEntity, List.class);
        System.out.println(responseEntity);
        return responseEntity;
    }

    private static void createUser(HttpEntity<User> entity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
        System.out.println("create--->");
        System.out.println(responseEntity.getBody());
        System.out.println("<---create");
        // 5ebfeb
    }

    private static void updateUser(HttpEntity<User> entity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
        System.out.println("update--->");
        System.out.println(responseEntity.getBody());
        System.out.println("<---update");
        // e7cb97
    }

    private static void deleteUser(HttpEntity<User> entity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "/3", HttpMethod.DELETE, entity, String.class);
        System.out.println("delete--->");
        System.out.println(responseEntity.getBody());
        System.out.println("<---delete");
        // 5dfcf9
    }
}
