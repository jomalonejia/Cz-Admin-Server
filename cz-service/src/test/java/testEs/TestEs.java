package testEs;

import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Created by jomalone_jia on 2017/12/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring.xml"})
public class TestEs {

    @Autowired
    private RestClient client;

    @Test
    public void test1() throws IOException {
        client.performRequest("GET", "/");
    }

    @Test
    public void test2() throws IOException {
        Response get = client.performRequest("GET", "/_search");
        System.out.println(get.toString());
    }
}
