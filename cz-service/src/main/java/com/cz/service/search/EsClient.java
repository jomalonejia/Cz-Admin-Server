package com.cz.service.search;

import com.alibaba.fastjson.JSON;
import com.cz.common.util.PropertiesUtil;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * Created by jomalone_jia on 2018/1/2.
 */
public class EsClient {

    private static final Logger _log = LoggerFactory.getLogger(EsClient.class);

    private static final TransportClient client;
    private static final String index = "cz";
    private static final String type = "item";


     static {
         Settings settings = Settings.builder().put("cluster.name", "es1"/*PropertiesUtil.getProperty("elasticSearch.cluster.name")*/)
                 .build();
         client = new PreBuiltTransportClient(settings);
         try {
             client.addTransportAddress(new TransportAddress(InetAddress.getByName(/*PropertiesUtil.getProperty("elasticSearch.url")*/"localhost"), 9300));
         } catch (UnknownHostException e) {
             e.printStackTrace();
         }
     }

     public static  IndexResponse add(Object object){
         return client.prepareIndex(index, type)
                 .setSource(JSON.toJSONString(object), XContentType.JSON)
                 .get();
     }
}
