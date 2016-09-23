package com.mlongbo.sunflower.server.example;

import com.mlongbo.sunflower.server.*;
import com.mlongbo.sunflower.server.example.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        final Logger logger = LoggerFactory.getLogger("TestApp");
        Server server = new DefaultServer(
                new Config()
                .set(ConfigKey.Name, "TestApp")
                .set(ConfigKey.Address, "127.0.0.1:9001"));

        server.setLogger(logger);
        server.handleSignal("USR2", new SignalHandler() {
            public void handle(Signal signal) {
                logger.debug("receive an usr2.");
            }
        });
        server.register("/sunflower/v1/user", UserController.class);
        server.run();
    }
}
