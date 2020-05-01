package com.mmkj.baselibrary.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Data: 2018/9/4 14:51
 * Author: Xuyexiang
 * Title:
 */
public class ConvertUtils {
    public static List map2List(Map<String, Object> convertMap) {
        List<Object> listValue = new ArrayList<>();
        for (Object o : convertMap.keySet()) {
            String key = o.toString();
            listValue.add(convertMap.get(key));
        }
        return listValue;
    }

    public static String getKey(Map<String, Object> map, String value) {
        String key = "";
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                key = entry.getKey();
                break;
            }
        }
        return key;
    }
}
