package com.example.authentication.controller;

import com.example.authentication.model.Foo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/authentication")
@Slf4j
public class AuthenticationController {

    @RequestMapping("/come")
    public String come(@RequestBody Foo param) {
        log.info("receive.name===>{}", param);
        String name = "continue";
        if (null != param && null != param.getName()) {
            name = "你好啊，" + param.getName();
        }
        return "{\"name\":\"" + name + "\"}";
    }
}
