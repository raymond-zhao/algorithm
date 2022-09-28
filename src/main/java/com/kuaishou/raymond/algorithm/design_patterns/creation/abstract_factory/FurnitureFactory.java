package com.kuaishou.raymond.algorithm.design_patterns.creation.abstract_factory;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-27 19:50
 */
public interface FurnitureFactory {

    Chair createChair();

    CoffeeTable createCoffeeTable();

    Sofa createSofa();

}
