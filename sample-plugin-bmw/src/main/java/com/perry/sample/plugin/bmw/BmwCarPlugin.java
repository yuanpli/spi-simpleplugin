package com.perry.sample.plugin.bmw;

import com.perry.sample.plugin.spi.CarPlugin;
import com.perry.sample.plugin.spi.car.CarFactory;

import java.util.Arrays;
import java.util.List;

public class BmwCarPlugin implements CarPlugin {

    @Override
    public List<CarFactory> getCarFactories() {
        return Arrays.asList(
                new BmwFactoryImpl()
        );
    }
}
