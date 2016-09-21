package com.mlongbo.sunflower.server;

/**
 * @author malongbo
 */
public class DefaultRouter implements Router{
    public Router add(String actionKey, Class<? extends Controller> controllerClass) {
        return this;
    }
}
