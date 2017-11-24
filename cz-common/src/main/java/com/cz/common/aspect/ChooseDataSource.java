package com.cz.common.aspect;

/**
 * Created by jomalone_jia on 2017/6/19.
 */
public class ChooseDataSource {

    private static final ThreadLocal<String> thread = new ThreadLocal<String>();

    public static void setDatSource(String dataSource){
        thread.set(dataSource);
    }

    public static String getDataSource(){
        return (String)thread.get();
    }

    public static void clearDataSource(){
        thread.remove();
    }

}
