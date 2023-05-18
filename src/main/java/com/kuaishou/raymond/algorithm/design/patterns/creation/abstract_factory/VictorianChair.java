package com.kuaishou.raymond.algorithm.design.patterns.creation.abstract_factory;

import lombok.extern.slf4j.Slf4j;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-27 19:45
 */
@Slf4j
public class VictorianChair implements Chair {
    @Override
    public void sitOn() {
        log.info("Sitting on VictorianChair.");
    }

    @Override
    public void hasLegs() {
        log.info("VictorianChair has 4 legs.");
    }
}
