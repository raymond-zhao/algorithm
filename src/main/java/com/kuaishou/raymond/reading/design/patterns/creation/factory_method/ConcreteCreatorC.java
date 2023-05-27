package com.kuaishou.raymond.reading.design.patterns.creation.factory_method;

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
