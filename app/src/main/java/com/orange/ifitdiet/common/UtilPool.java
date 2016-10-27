package com.orange.ifitdiet.common;

import java.util.HashMap;

/**
 * 存放所有工具类的池
 * Created by 廖俊瑶 on 2016/10/14.
 */
public class UtilPool {
    private HashMap<String, Object> utilMap;

    public UtilPool() {
        utilMap=new HashMap<String,Object>();
    }

    /**
     * 获取到UtilMap
     *
     * @return UtilMap
     */
    public HashMap<String, Object> getUtilMap() {
        return utilMap;
    }
}
