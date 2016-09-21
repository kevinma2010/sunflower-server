package com.mlongbo.sunflower.server;

/**
 * @author malongbo
 */
public class DefaultServer  implements Server{
    private Config cfg;

    public DefaultServer(Config cfg) {
        this.cfg = cfg;
    }

    public void setLogger(Logger logger) {

    }

    public void register(String uriPrefix, Class<? extends Controller> controller) {

    }

    public void run() {
        System.out.printf("%s start at %s", cfg.get(ConfigKey.Name), cfg.get(ConfigKey.Address));
    }
}
