package com.kuaishou.raymond.algorithm.design.patterns.creation.factory_method;

import lombok.extern.slf4j.Slf4j;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-27 15:50
 */
@Slf4j
public class ProductD extends ProductC {
    @Override
    public void doStuff() {
        log.info("D  extends C then doing some stuff.");
    }
}
