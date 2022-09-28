package com.kuaishou.raymond.algorithm.design_patterns.creation.factory_method;

import lombok.extern.slf4j.Slf4j;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-26 21:13
 */
@Slf4j
public class ProductB implements IProduct {
    @Override
    public void doStuff() {
        log.info("B is doing some stuff.");
    }
}
