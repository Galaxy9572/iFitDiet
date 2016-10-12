package com.orange.ifitdiet.common;

import java.util.HashMap;

/**
 * Created by 廖俊瑶 on 2016/10/12.
 */
public class BeanPool {
    private HashMap beanMap;

    public BeanPool() {
        beanMap=new HashMap<String,Object>();
    }

    public HashMap getBeanMap() {
        return beanMap;
    }

    public void setBeanMap(HashMap beanMap) {
        this.beanMap = beanMap;
    }
}
