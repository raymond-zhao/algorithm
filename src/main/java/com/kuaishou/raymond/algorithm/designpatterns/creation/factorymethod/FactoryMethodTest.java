package com.kuaishou.raymond.algorithm.designpatterns.creation.factorymethod;

import lombok.extern.slf4j.Slf4j;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-26 21:20
 */
@Slf4j
public class FactoryMethodTest {

    public static void main(String[] args) {
        ICreator iCreator;
        System.setProperty("product", "C");
        String product = System.getProperty("product");

        if ("A".equals(product)) {
            iCreator = new ConcreteICreatorA();
        } else if ("B".equals(product)) {
            iCreator = new ConcreteICreatorB();
        } else if ("C".equals(product)) {
            iCreator = new ConcreteCreatorC();
        } else {
            throw new IllegalArgumentException("未知的产品类型");
        }

        iCreator.someOperation();

        iCreator = new ConcreteCreatorD();
        iCreator.someOperation();
    }

}
