package com.kuaishou.raymond.algorithm.reading.design.patterns.demo.validator.checkfilter;

import org.springframework.stereotype.Component;

import com.kuaishou.raymond.algorithm.reading.design.patterns.demo.service.DemoService.DemoDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: raymond
 * @Datetime: 2023/9/22 14:20
 * Description:
 */
@Slf4j
@Component
public class IdCheckFilter implements CheckFilter<DemoDTO> {
    @Override
    public void check(DemoDTO demoDTO) {
        log.info("IdCheckFilter is working...");
    }
}
