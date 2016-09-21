package com.mlongbo.sunflower.server.example;

import com.mlongbo.sunflower.server.*;
import com.mlongbo.sunflower.server.example.controller.UserController;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        Server server = new DefaultServer(
                new Config()
                .set(ConfigKey.Name, "TestApp")
                .set(ConfigKey.Address, "127.0.0.1:9001"));

        server.register("/sunflower/v1/user", UserController.class);
        server.run();
    }
}
