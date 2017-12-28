import com.cz.api.service.IItemService;
import com.cz.model.item.Item;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jomalone_jia on 2017/12/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring.xml"})
public class TestAop {

    @Autowired
    private IItemService itemService;

    @Test
    public void test1(){
        PageInfo<Item> itemPageInfo = itemService.listItems(1, 5);
        System.out.println(itemPageInfo.getClass());
        System.out.println(itemPageInfo.toString());
    }

    @Test
    public void test2(){
        itemService.test();
    }

}
