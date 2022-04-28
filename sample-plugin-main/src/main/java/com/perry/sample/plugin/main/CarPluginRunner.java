package com.perry.sample.plugin.main;

import com.perry.sample.plugin.spi.car.CarFactory;
import com.perry.sample.plugin.spi.car.ICar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class CarPluginRunner {

    @Autowired
    private PluginLoader pluginLoader;

    @PostConstruct
    public void run() {
        System.out.println("Hello World!");
        List<CarFactory> plugins = pluginLoader.getPlugins();
        plugins.forEach(plugin -> {
            if (plugin == null) {
                System.err.println("No factories loaded!");
                return;
            }

            System.out.println("This is running from the plugin");
            final ICar foo = plugin.build();
            foo.drive();
        });
    }
}
