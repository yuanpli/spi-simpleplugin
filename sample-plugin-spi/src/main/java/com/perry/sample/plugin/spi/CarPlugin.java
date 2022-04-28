package com.perry.sample.plugin.spi;

import com.perry.sample.plugin.spi.car.CarFactory;

import java.util.Collections;
import java.util.List;

public interface CarPlugin {

    default List<CarFactory> getCarFactories() {
        return Collections.emptyList();
    }
}
