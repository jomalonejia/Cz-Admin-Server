import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jomalone_jia on 2017/6/19.
 */
public class TestJackson {

    @Test
    public void test1() throws IOException {

        ArrayList<String> lists = new ArrayList();
        lists.add("testlist01");
        lists.add("testlist02");
        HashMap<String,String> maps = new HashMap();
        maps.put("mapkey01","mapvalue01");
        maps.put("mapkey02","mapvalue02");

        ObjectMapper mapper = new ObjectMapper();
        // 仅输出一行json字符串

    }


}
