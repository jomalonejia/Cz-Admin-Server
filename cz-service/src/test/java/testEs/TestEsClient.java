package testEs;

import com.cz.model.item.Item;
import com.cz.service.search.EsClient;
import org.junit.Test;

/**
 * Created by jomalone_jia on 2018/1/2.
 */
public class TestEsClient {


    @Test
    public void test1(){
        Item item = new Item();
        item.setId("a");
        item.setPrice(299);
        item.setContent("free to play");
        EsClient.add(item);
    }
}
