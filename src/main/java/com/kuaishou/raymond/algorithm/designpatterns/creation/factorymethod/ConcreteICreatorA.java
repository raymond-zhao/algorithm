package com.kuaishou.raymond.algorithm.designpatterns.creation.factorymethod;

import lombok.extern.slf4j.Slf4j;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-26 21:17
 */
@Slf4j
public class ConcreteICreatorA implements ICreator {
    @Override
    public IProduct createProduct() {
        return new ProductA();
    }
}
