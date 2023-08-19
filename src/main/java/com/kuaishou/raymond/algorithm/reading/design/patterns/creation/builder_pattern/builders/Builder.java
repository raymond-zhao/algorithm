package com.kuaishou.raymond.algorithm.reading.design.patterns.creation.builder_pattern.builders;

import com.kuaishou.raymond.algorithm.reading.design.patterns.creation.builder_pattern.components.Engine;
import com.kuaishou.raymond.algorithm.reading.design.patterns.creation.builder_pattern.components.GPSNavigator;
import com.kuaishou.raymond.algorithm.reading.design.patterns.creation.builder_pattern.components.Transmission;
import com.kuaishou.raymond.algorithm.reading.design.patterns.creation.builder_pattern.components.TripComputer;
import com.kuaishou.raymond.algorithm.reading.design.patterns.creation.builder_pattern.cars.CarType;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-28 20:29
 */
public interface Builder {

    /**
     * 设置汽车类型
     * @param type
     */
    void setCarType(CarType type);

    /**
     * 汽车座位数
     * @param seats
     */
    void setSeats(int seats);

    /**
     * 汽车引擎
     * @param engine
     */
    void setEngine(Engine engine);

    /**
     * 汽车发射器
     * @param transmission
     */
    void setTransmission(Transmission transmission);

    /**
     * 里程记录仪
     * @param tripComputer
     */
    void setTripComputer(TripComputer tripComputer);

    /**
     * GPS 导航
     * @param gpsNavigator
     */
    void setGPSNavigator(GPSNavigator gpsNavigator);

}
