package com.kuaishou.raymond.algorithm.design.patterns.creation.factory_method;

import lombok.extern.slf4j.Slf4j;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-27 15:41
 */
@Slf4j
public class ProductC implements IProduct {
    @Override
    public void doStuff() {
        log.info("C is doing some stuff.");
    }
}
