package com.kuaishou.raymond.algorithm.design_patterns.creation.abstract_factory;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-27 19:59
 */
public class ModernFurnitureFactory implements FurnitureFactory {

    @Override
    public Chair createChair() {
        return new ModernChair();
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
