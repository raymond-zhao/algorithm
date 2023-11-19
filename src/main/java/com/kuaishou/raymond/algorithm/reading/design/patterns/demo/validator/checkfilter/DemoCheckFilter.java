package com.kuaishou.raymond.algorithm.reading.design.patterns.demo.validator.checkfilter;

import org.springframework.stereotype.Component;

import com.kuaishou.raymond.algorithm.reading.design.patterns.demo.service.DemoService.DemoDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: raymond
 * @Datetime: 2023/9/22 16:46
 * Description:
 */
@Slf4j
@Component
public class DemoCheckFilter implements CheckFilter<DemoDTO> {
    @Override
    public void check(DemoDTO demoDTO) {
        log.info("DemoCheckFilter is working...");
    }
}
