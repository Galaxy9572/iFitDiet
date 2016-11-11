package com.orange.ifitdiet.common;

import com.orange.ifitdiet.domain.GroupBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 廖俊瑶 on 2016/11/8.
 */

public class ListPool {
    private HashMap<String,List<Map<String,GroupBean>>> listMap;

    public ListPool() {
        listMap=new HashMap<>();
    }

    public HashMap<String, List<Map<String, GroupBean>>> getListMap() {
        return listMap;
    }
}
