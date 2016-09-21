package com.mlongbo.sunflower.server;

/**
 * @author malongbo
 */
public interface Router {
    Router add(String actionKey, Class<? extends Controller> controllerClass) ;
}
