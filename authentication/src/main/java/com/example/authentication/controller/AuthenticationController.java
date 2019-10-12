package com.example.authentication.controller;

import com.example.authentication.model.Foo;
import com.example.authentication.repository.FooRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 张天赐
 */
@RestController
@RequestMapping("/authentication")
@Slf4j
public class AuthenticationController {

    @Autowired
    private FooRepository fooRepository;

    @RequestMapping("/come")
    public String come(@RequestBody Foo param) {
        log.info("receive.name===>{}", param);
        String name = "continue";
        if (null != param && null != param.getName()) {
            name = "你好啊，" + param.getName();
        }
        return "{\"name\":\"" + name + "\"}";
    }

    @RequestMapping("/get")
    public Object get() {
        return fooRepository.findAll();
    }

}
