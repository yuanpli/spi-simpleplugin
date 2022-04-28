package com.perry.sample.plugin.main;

import com.perry.sample.plugin.spi.CarPlugin;
import com.perry.sample.plugin.spi.car.CarFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Component
public class PluginLoader {

    private final Map<String, CarFactory> fooFactoryMap = new HashMap<>();
    private final AtomicBoolean loading = new AtomicBoolean();
    private File pluginsDir;

    @PostConstruct
    public void initPluginDir() {
        String pluginsPath = "plugins";
        this.pluginsDir = new File(pluginsPath);
        loadPlugins();
    }

    public void loadPlugins() {
        if (!pluginsDir.exists() || !pluginsDir.isDirectory()) {
            System.err.println("Skipping Plugin Loading. Plugin dir not found: " + pluginsDir);
            return;
        }

        if (loading.compareAndSet(false, true)) {
            final File[] files = requireNonNull(pluginsDir.listFiles());
            for (File pluginDir : files) {
                if (pluginDir.isDirectory()) {
                    loadPlugin(pluginDir);
                }
            }
        }
    }

    private void loadPlugin(final File pluginDir) {
        System.out.println("Loading plugin: " + pluginDir);
        final URLClassLoader pluginClassLoader = createPluginClassLoader(pluginDir);
        final ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            Thread.currentThread().setContextClassLoader(pluginClassLoader);
            for (CarPlugin plugin : ServiceLoader.load(CarPlugin.class, pluginClassLoader)) {
                installPlugin(plugin);
            }
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }
    }


    private void installPlugin(final CarPlugin plugin) {
        System.out.println("Installing plugin: " + plugin.getClass().getName());
        for (CarFactory f : plugin.getCarFactories()) {
            fooFactoryMap.put(f.name(), f);
        }
    }

    private URLClassLoader createPluginClassLoader(File dir) {
        final URL[] urls = Arrays.stream(Optional.of(dir.listFiles()).orElse(new File[]{}))
                .sorted()
                .map(File::toURI)
                .map(this::toUrl)
                .toArray(URL[]::new);

        return new PluginClassLoader(urls, getClass().getClassLoader());
    }

    private URL toUrl(final URI uri) {
        try {
            return uri.toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public CarFactory getFooFactory(String name) {
        return fooFactoryMap.get(name);
    }

    public List<CarFactory> getPlugins() {
        return fooFactoryMap.values().stream().collect(Collectors.toList());
    }
}
