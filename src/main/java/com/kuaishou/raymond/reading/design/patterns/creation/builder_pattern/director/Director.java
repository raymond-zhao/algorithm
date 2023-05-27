package com.kuaishou.raymond.reading.design.patterns.creation.builder_pattern.director;

import com.kuaishou.raymond.reading.design.patterns.creation.builder_pattern.components.Engine;
import com.kuaishou.raymond.reading.design.patterns.creation.builder_pattern.components.GPSNavigator;
import com.kuaishou.raymond.reading.design.patterns.creation.builder_pattern.components.Transmission;
import com.kuaishou.raymond.reading.design.patterns.creation.builder_pattern.components.TripComputer;
import com.kuaishou.raymond.reading.design.patterns.creation.builder_pattern.builders.Builder;
import com.kuaishou.raymond.reading.design.patterns.creation.builder_pattern.cars.CarType;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-29 10:29
 * Director 接收 Builder 接口，进行数据填充
 */
public class Director {

    public void constructSportsCar(Builder builder) {
        builder.setCarType(CarType.SPORTS_CAR);
        builder.setSeats(2);
        builder.setEngine(new Engine(3.0, 0));
        builder.setTransmission(Transmission.SEMI_AUTOMATIC);
        builder.setTripComputer(new TripComputer());
        builder.setGPSNavigator(new GPSNavigator());
    }

    public void constructCityCar(Builder builder) {
        builder.setCarType(CarType.CITY_CAR);
        builder.setSeats(2);
        builder.setEngine(new Engine(1.2, 0));
        builder.setTransmission(Transmission.AUTOMATIC);
        builder.setTripComputer(new TripComputer());
        builder.setGPSNavigator(new GPSNavigator());
    }

    public void constructSUV(Builder builder) {
        builder.setCarType(CarType.SUV);
        builder.setSeats(4);
        builder.setEngine(new Engine(2.5, 0));
        builder.setTransmission(Transmission.MANUAL);
        builder.setGPSNavigator(new GPSNavigator());
    }

}
