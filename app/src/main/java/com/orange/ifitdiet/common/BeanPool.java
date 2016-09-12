package com.orange.ifitdiet.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 廖俊瑶 on 2016/9/10.
 */
public class BeanPool {
    private Map<String, Object> beanPool=new HashMap<>();

    public BeanPool() {
    }

    public void addBean(String key, Object obj) {
        beanPool.put(key, obj);
    }

    public Object getBean(String key) {
        return beanPool.get(key);
    }

}
