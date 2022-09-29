package com.kuaishou.raymond.algorithm.design_patterns.creation.builder_pattern.demo;

import com.kuaishou.raymond.algorithm.design_patterns.creation.builder_pattern.builders.CarBuilder;
import com.kuaishou.raymond.algorithm.design_patterns.creation.builder_pattern.builders.CarManualBuilder;
import com.kuaishou.raymond.algorithm.design_patterns.creation.builder_pattern.cars.Car;
import com.kuaishou.raymond.algorithm.design_patterns.creation.builder_pattern.cars.Manual;
import com.kuaishou.raymond.algorithm.design_patterns.creation.builder_pattern.director.Director;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-29 10:34
 */
public class Demo {
    public static void main(String[] args) {
        Director director = new Director();

        // Director gets the concrete builder object from the client
        // (application code). That's because application knows better which
        // builder to use to get a specific product.
        CarBuilder builder = new CarBuilder();
        director.constructSportsCar(builder);

        // The final product is often retrieved from a builder object, since
        // Director is not aware and not dependent on concrete builders and
        // products.
        Car car = builder.getResult();
        System.out.println("Car built:\n" + car.getCarType());

        CarManualBuilder manualBuilder = new CarManualBuilder();

        // Director may know several building recipes.
        director.constructSportsCar(manualBuilder);
        Manual carManual = manualBuilder.getResult();
        System.out.println("\nCar manual built:\n" + carManual.print());
    }
}
