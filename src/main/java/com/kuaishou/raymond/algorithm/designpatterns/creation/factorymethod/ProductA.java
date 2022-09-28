package com.kuaishou.raymond.algorithm.designpatterns.creation.factorymethod;

import lombok.extern.slf4j.Slf4j;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-26 21:10
 */
@Slf4j
public class ProductA implements IProduct {
    @Override
    public void doStuff() {
        log.info("A is doing some stuff.");
    }
}
