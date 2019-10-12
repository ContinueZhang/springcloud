package com.example.finance.controller;


import com.example.finance.model.Foo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/finance")
@Slf4j
public class FinanceController {
    private final String fooResourceUrl = "http://localhost:9091/authentication/come";
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/come")
    public String come() {
        return "come on finance baby";
    }

    @RequestMapping("/call/authentication")
    public Object callAuthentication() throws IOException {
        Object body = "";
//        body = method1();
//        body = method2();
//        body = method3();
//        body = method4();
//        body = method5();
        body = method6();
        return body;
    }

    private Object method1() throws IOException {
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
        ObjectMapper mapper = new ObjectMapper();
        String body = response.getBody();
        JsonNode jsonNode = mapper.readTree(body);
        log.info("respone name===>" + jsonNode.path("name"));
        return body;
    }

    private Object method2() {
        Foo foo = restTemplate.getForObject(fooResourceUrl, Foo.class);
        log.info("foo.name===>" + foo.getName());
        return foo;
    }

    private Object method3() {
        HttpHeaders httpHeaders = restTemplate.headForHeaders(fooResourceUrl);
        log.info("contentType===>" + httpHeaders.getContentType());
        return httpHeaders;
    }

    private Object method4() {
        Foo foo = new Foo();
        foo.setName("method4");
        HttpEntity<Foo> request = new HttpEntity<>(foo);
        foo = restTemplate.postForObject(fooResourceUrl, request, Foo.class);
        Assert.notNull(foo, "返回结果为null");
        log.info("foo.name===>{}", foo.getName());
        return foo;
    }

    private Object method5() {
        Foo foo = new Foo();
        foo.setName("method5");
        HttpEntity<Foo> request = new HttpEntity<>(foo);
        return restTemplate.postForLocation(fooResourceUrl, request);
    }

    private Object method6() {
        Foo foo = new Foo();
        foo.setName("method6");
        HttpEntity<Foo> request = new HttpEntity<>(foo);
        ResponseEntity<Foo> response = restTemplate.exchange(fooResourceUrl, HttpMethod.POST, request, Foo.class);
        log.info("statusCod{}===>", response.getStatusCode());
        return response.getBody();
    }

    private Object method7() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id", "1");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(fooResourceUrl, request, String.class);
        log.info("statusCod{}===>", response.getStatusCode());
        return response.getBody();
    }

}
