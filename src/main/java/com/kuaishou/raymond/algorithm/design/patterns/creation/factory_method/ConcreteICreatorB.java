package com.kuaishou.raymond.algorithm.design.patterns.creation.factory_method;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-26 21:17
 */
public class ConcreteICreatorB implements ICreator {
    @Override
    public IProduct createProduct() {
        return new ProductB();
    }
}
