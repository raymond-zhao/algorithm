package com.kuaishou.raymond.algorithm.design.patterns.creation.builder_pattern.components;

import com.kuaishou.raymond.algorithm.design.patterns.creation.builder_pattern.cars.Car;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-29 09:55
 */
public class TripComputer {
    private Car car;

    public void setCar(Car car) {
        this.car = car;
    }

    public void showFuelLevel() {
        System.out.println("Fuel level: " + car.getFuel());
    }

    public void showStatus() {
        if (this.car.getEngine().isStarted()) {
            System.out.println("Car is started");
        } else {
            System.out.println("Car isn't started");
        }
    }
}
