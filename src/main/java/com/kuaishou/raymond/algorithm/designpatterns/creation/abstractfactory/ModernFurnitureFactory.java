package com.kuaishou.raymond.algorithm.designpatterns.creation.abstractfactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-27 19:59
 */
@Slf4j
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
