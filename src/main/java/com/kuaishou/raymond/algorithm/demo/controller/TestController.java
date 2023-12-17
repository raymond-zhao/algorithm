package com.kuaishou.raymond.algorithm.demo.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kuaishou.raymond.algorithm.demo.aspect.PerfLog;

/**
 * @Author: raymond
 * @Datetime: 2023/8/17 15:54
 * Description:
 */
@RequestMapping("/api")
@RestController
public class TestController {

    @PerfLog
    @GetMapping("/divide")
    public ResponseEntity<Integer> divideNumber(@RequestParam("number") Integer number) {
        return ResponseEntity.of(Optional.of(10 / number));
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello, World!");
    }
}
