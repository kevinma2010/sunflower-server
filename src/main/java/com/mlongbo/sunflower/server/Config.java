package com.mlongbo.sunflower.server;

import java.util.HashMap;
import java.util.Map;

/**
 * @author malongbo
 */
public class Config {
    private Map<ConfigKey, String> meta;
    private final String defaultHost = "127.0.0.1";
    private final String defaultPort = "9000";

    /**
     *
     */
    public Config() {
        this.meta = new HashMap<ConfigKey, String>();
    }

    /**
     *
     * @param key
     * @param value
     * @return
     */
    public Config set(ConfigKey key, String value) {
        this.meta.put(key, value);
        return this;
    }

    /**
     *
     * @param key
     * @return
     */
    public String get(ConfigKey key) {
        return meta.get(key);
    }

    /**
     *
     * @return
     */
    public String getHost() {
        String address = this.get(ConfigKey.Address);
        if (address != null) {
            return address.split(":")[0];
        }
        return this.defaultHost;
    }

    /**
     *
     * @return
     */
    public String getPort() {
        String address = this.get(ConfigKey.Address);
        if (address != null) {
            return address.split(":")[1];
        }
        return this.defaultPort;
    }
}

