package com.mlongbo.sunflower.server;

/**
 * @author malongbo
 */
public interface Server {
    /**
     *
     * @param logger
     */
    void setLogger(Logger logger);

    /**
     *
     * @param uriPrefix
     * @param controller
     */
    void register(String uriPrefix, Class< ? extends Controller> controller);

    /**
     *
     */
    void run();
}
