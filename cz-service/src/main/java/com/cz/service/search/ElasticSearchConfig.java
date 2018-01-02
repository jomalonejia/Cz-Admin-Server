package com.cz.service.search;

import fr.pilato.spring.elasticsearch.ElasticsearchRestClientFactoryBean;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by jomalone_jia on 2017/12/29.
 */
//@Component
public class ElasticSearchConfig {
    @Configuration
    public class AppConfig {
        @Bean
        public RestClient esClient() throws Exception {
            ElasticsearchRestClientFactoryBean factory = new ElasticsearchRestClientFactoryBean();
            factory.setEsNodes(new String[]{"127.0.0.1:9200"});
            factory.afterPropertiesSet();
            return factory.getObject();
        }
    }

    /*@Autowired
    private RestClient client;

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("fr.pilato.tests");
        context.refresh();

        ElasticSearchConfig p = context.getBean(ElasticSearchConfig.class);
        p.run();

        context.close();
    }

    private void run() throws IOException {
        client.performRequest("GET", "/");
    }*/
}
