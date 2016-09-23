package com.mlongbo.sunflower.server;

import java.lang.management.ManagementFactory;

/**
 * @author malongbo
 */
public class Util {
    public static int getPID() {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        return Integer.parseInt(name.split("@")[0]);
    }
}
