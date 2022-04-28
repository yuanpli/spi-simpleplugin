package com.perry.sample.plugin.bmw;

import cn.hutool.core.util.IdUtil;
import com.perry.sample.plugin.spi.car.ICar;

public class BmwCarImpl implements ICar {

    @Override
    public void drive() {
        String simpleUUID = IdUtil.randomUUID();
        System.out.println("This is a super car made by BMW!" + simpleUUID);
    }
}
