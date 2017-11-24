package testRedis;

import com.cz.common.util.cache.redis.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jomalone_jia on 2017/6/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testRedis.xml"})
public class TestRedis2 {

    @Autowired

    @Test
    public void test1(){
        RedisUtil util = new RedisUtil();
        util.get("laowang");
    }
}
