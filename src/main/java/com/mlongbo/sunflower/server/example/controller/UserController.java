package com.mlongbo.sunflower.server.example.controller;

import com.mlongbo.sunflower.server.BaseController;
import com.mlongbo.sunflower.server.Mapping;
import com.mlongbo.sunflower.server.Method;
import com.mlongbo.sunflower.server.MethodType;

/**
 * @author malongbo
 */
public class UserController  extends BaseController{

    /**
     * 查询用户信息接口
     */
    @Method(MethodType.GET)
    public void profile() {

    }

    /**
     * 更新用户信息接口
     */
    @Mapping("profile")
    @Method(MethodType.PUT)
    public void updateProfile() {

    }
}
