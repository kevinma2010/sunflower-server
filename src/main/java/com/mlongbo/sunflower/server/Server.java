package com.mlongbo.sunflower.server;

import org.slf4j.Logger;
import sun.misc.SignalHandler;

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
     * @param signal
     * @param handler
     */
    void handleSignal(String signal, SignalHandler handler);

    /**
     *
     */
    void run();
}
