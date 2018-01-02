package com.cz.common.util;

import com.cz.common.util.security.AESUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by jomalone_jia on 2017/6/19.
 */
public class PropertiesUtil extends PropertyPlaceholderConfigurer{

    private List<String> decryptProperties = new ArrayList<String>();

    private static Properties props;       // load the properties key & value

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
            throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        this.props = props;
    }


    @Override
    protected void loadProperties(Properties props) throws IOException {
        super.loadProperties(props);
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            if(decryptProperties!=null && decryptProperties.contains(keyStr)){
                String decodeValue = AESUtil.AESDecode(value);
                props.setProperty(keyStr, decodeValue);
            }
        }
    }

    public void setDecryptProperties(List<String> decryptProperties) {
        this.decryptProperties = decryptProperties;
    }

    public static String getProperty(String key){
        return props.getProperty(key);
    }
}
