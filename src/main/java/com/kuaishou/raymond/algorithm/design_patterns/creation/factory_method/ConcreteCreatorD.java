package com.kuaishou.raymond.algorithm.design_patterns.creation.factory_method;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-27 16:05
 */
public class ConcreteCreatorD extends ConcreteCreatorC {
    @Override
    public IProduct createProduct() {
        return new ProductD();
    }
}
