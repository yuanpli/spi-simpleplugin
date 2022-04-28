package com.perry.sample.plugin.spi.car;

public interface CarFactory {

    String name();

    ICar build();
}
