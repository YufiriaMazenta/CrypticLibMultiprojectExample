package com.example.crypticlibexample.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.PluginContainer;
import com.velocitypowered.api.proxy.ProxyServer;
import crypticlib.VelocityPlugin;
import org.slf4j.Logger;

import java.nio.file.Path;

@Plugin(
        id = "{{id}}",
        name = "{{name}}",
        version = "{{version}}",
        authors = {"Yufiria"}
)
public class Example extends VelocityPlugin {

    public static Example INSTANCE;

    @Inject
    public Example(Logger logger, ProxyServer proxyServer, PluginContainer pluginContainer, Path dataDirectory) {
        super(logger, proxyServer, pluginContainer, dataDirectory);
        INSTANCE = this;
    }



}
