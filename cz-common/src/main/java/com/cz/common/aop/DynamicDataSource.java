package com.cz.common.aop;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jomalone_jia on 2017/6/19.
 */
public class DynamicDataSource extends AbstractRoutingDataSource{

    public static Map<String,List<String>> methodType = new HashMap<String, List<String>>();


    public void setMethodType(Map<String, String> map) {
        for (String key : map.keySet()) {
            List<String> v = new ArrayList<String>();
            String[] types = map.get(key).split(",");
            for (String type : types) {
                if (StringUtils.isNotBlank(type)) {
                    v.add(type);
                }
            }
            methodType.put(key, v);
        }
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return ChooseDataSource.getDataSource();
    }


}
