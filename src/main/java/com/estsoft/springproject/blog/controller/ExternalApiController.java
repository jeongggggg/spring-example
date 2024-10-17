package com.estsoft.springproject.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class ExternalApiController {
    @GetMapping("/api/external")
    public String callApi(){
        // 외부 API 호출
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> json = restTemplate.getForEntity(
                "https://jsonplaceholder.typicode.com/posts", String.class);

        log.info("StatusCode : {}", json.getStatusCode());
        log.info(json.getBody());

        return "OK";
    }
}
