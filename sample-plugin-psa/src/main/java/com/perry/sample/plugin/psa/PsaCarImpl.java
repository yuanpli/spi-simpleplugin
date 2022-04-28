package com.perry.sample.plugin.psa;

import cn.hutool.core.util.IdUtil;
import com.perry.sample.plugin.spi.car.ICar;

public class PsaCarImpl implements ICar {

    @Override
    public void drive() {
        String simpleUUID = IdUtil.nanoId();
        System.out.println("This is a super car made by PSA!" + simpleUUID);
    }
}
