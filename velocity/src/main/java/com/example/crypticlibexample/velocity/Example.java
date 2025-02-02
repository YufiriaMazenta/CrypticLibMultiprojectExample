package com.example.crypticlibexample.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.PluginContainer;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import crypticlib.VelocityPlugin;
import crypticlib.config.ConfigHandler;
import crypticlib.config.node.impl.velocity.ConfigSectionConfig;
import org.slf4j.Logger;

import java.nio.file.Path;
import java.util.Map;

@Plugin(
        id = "{{id}}",
        name = "{{name}}",
        version = "{{version}}",
        authors = {"Yufiria"}
)
@ConfigHandler(path = "config.yml")
public class Example extends VelocityPlugin {

    public static final ConfigSectionConfig test = new ConfigSectionConfig("test", Map.of(
        "test", Map.of("test1", "test2"),
        "test2", Map.of("test3", Map.of("test4", "test5"))
    ));

    public static Example INSTANCE;

    @Inject
    public Example(Logger logger, ProxyServer proxyServer, PluginContainer pluginContainer, @DataDirectory Path dataDirectory) {
        super(logger, proxyServer, pluginContainer, dataDirectory);
        INSTANCE = this;
    }



}
