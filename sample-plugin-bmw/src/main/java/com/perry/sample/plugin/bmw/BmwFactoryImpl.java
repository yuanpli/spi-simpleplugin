package com.perry.sample.plugin.bmw;

import com.perry.sample.plugin.spi.car.CarFactory;
import com.perry.sample.plugin.spi.car.ICar;

public class BmwFactoryImpl implements CarFactory {

    @Override
    public String name() {
        return "BMW";
    }

    @Override
    public ICar build() {
        return new BmwCarImpl();
    }
}
