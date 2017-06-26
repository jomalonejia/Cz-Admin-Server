package com.cz.core.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by admin on 2017/6/19.
 */
public class PropertiesUtil extends PropertyPlaceholderConfigurer{

    private List<String> decryptProperties = new ArrayList<String>();


    @Override
    protected void loadProperties(Properties props) throws IOException {
        super.loadProperties(props);
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            if(decryptProperties!=null && decryptProperties.contains(keyStr)){

                //转码 。。。。
                props.setProperty(keyStr, value);
            }
        }
    }

    public void setDecryptProperties(List<String> decryptProperties) {
        this.decryptProperties = decryptProperties;
    }
}
