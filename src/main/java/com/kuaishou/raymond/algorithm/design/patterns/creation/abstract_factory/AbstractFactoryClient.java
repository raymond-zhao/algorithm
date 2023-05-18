package com.kuaishou.raymond.algorithm.design.patterns.creation.abstract_factory;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-27 20:11
 */
public class AbstractFactoryClient {

    private FurnitureFactory furnitureFactory;

    public AbstractFactoryClient() {}

    public AbstractFactoryClient(FurnitureFactory furnitureFactory) {
        this.furnitureFactory = furnitureFactory;
    }

    public void setFurnitureFactory(FurnitureFactory furnitureFactory) {
        this.furnitureFactory = furnitureFactory;
    }

    public void doStuff() {
        furnitureFactory.createChair().hasLegs();
        furnitureFactory.createChair().sitOn();
    }

    public static void main(String[] args) {
        AbstractFactoryClient abstractFactoryClient = new AbstractFactoryClient();
        VictorianFurnitureFactory victorianFurnitureFactory = new VictorianFurnitureFactory();
        abstractFactoryClient.setFurnitureFactory(victorianFurnitureFactory);
        abstractFactoryClient.doStuff();

        ModernFurnitureFactory modernFurnitureFactory = new ModernFurnitureFactory();
        abstractFactoryClient.setFurnitureFactory(modernFurnitureFactory);
        abstractFactoryClient.doStuff();

        AbstractFactoryClient abstractFactoryClient1 = new AbstractFactoryClient(new VictorianFurnitureFactory());
        abstractFactoryClient1.doStuff();
    }
}
