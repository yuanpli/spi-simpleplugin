package com.perry.sample.plugin.psa;

import com.perry.sample.plugin.spi.car.CarFactory;
import com.perry.sample.plugin.spi.car.ICar;

public class PsaFactoryImpl implements CarFactory {

    @Override
    public String name() {
        return "PSA";
    }

    @Override
    public ICar build() {
        return new PsaCarImpl();
    }
}
