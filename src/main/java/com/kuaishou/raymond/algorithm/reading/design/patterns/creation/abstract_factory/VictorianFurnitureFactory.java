package com.kuaishou.raymond.algorithm.reading.design.patterns.creation.abstract_factory;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-27 19:58
 */
public class VictorianFurnitureFactory implements FurnitureFactory {

    @Override
    public Chair createChair() {
        return new VictorianChair();
    }

    @Override
    public CoffeeTable createCoffeeTable() {
        return null;
    }

    @Override
    public Sofa createSofa() {
        return null;
    }

}
