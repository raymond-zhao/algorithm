package com.kuaishou.raymond.algorithm.reading.design.patterns.demo.validator.checkfilter;

import org.springframework.stereotype.Component;

import com.kuaishou.raymond.algorithm.reading.design.patterns.demo.service.DemoService.DemoParam;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: raymond
 * @Datetime: 2023/9/22 15:49
 * Description:
 */
@Slf4j
@Component
public class UselessCheckFilter implements CheckFilter<DemoParam> {
    @Override
    public void check(DemoParam demoParam) {
        log.info("checking demo param.");
    }
}
