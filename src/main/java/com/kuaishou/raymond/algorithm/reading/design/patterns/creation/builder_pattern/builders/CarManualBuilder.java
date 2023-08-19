package com.kuaishou.raymond.algorithm.reading.design.patterns.creation.builder_pattern.builders;

import com.kuaishou.raymond.algorithm.reading.design.patterns.creation.builder_pattern.components.Engine;
import com.kuaishou.raymond.algorithm.reading.design.patterns.creation.builder_pattern.components.GPSNavigator;
import com.kuaishou.raymond.algorithm.reading.design.patterns.creation.builder_pattern.components.Transmission;
import com.kuaishou.raymond.algorithm.reading.design.patterns.creation.builder_pattern.components.TripComputer;
import com.kuaishou.raymond.algorithm.reading.design.patterns.creation.builder_pattern.cars.CarType;
import com.kuaishou.raymond.algorithm.reading.design.patterns.creation.builder_pattern.cars.Manual;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-29 10:08
 */
public class CarManualBuilder implements Builder {
    private CarType type;
    private int seats;
    private Engine engine;
    private Transmission transmission;
    private TripComputer tripComputer;
    private GPSNavigator gpsNavigator;

    @Override
    public void setCarType(CarType type) {
        this.type = type;
    }

    @Override
    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public void setTripComputer(TripComputer tripComputer) {
        this.tripComputer = tripComputer;
    }

    @Override
    public void setGPSNavigator(GPSNavigator gpsNavigator) {
        this.gpsNavigator = gpsNavigator;
    }

    public Manual getResult() {
        return new Manual(type, seats, engine, transmission, tripComputer, gpsNavigator);
    }
}
