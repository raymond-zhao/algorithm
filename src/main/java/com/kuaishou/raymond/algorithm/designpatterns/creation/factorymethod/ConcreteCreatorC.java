package com.kuaishou.raymond.algorithm.designpatterns.creation.factorymethod;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-27 15:42
 */
public class ConcreteCreatorC implements ICreator {
    @Override
    public IProduct createProduct() {
        return new ProductC();
    }
}
