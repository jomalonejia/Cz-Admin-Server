import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cz.api.service.*;
import com.cz.common.util.qiniu.PictureUtil;
import com.cz.dto.item.ItemContent;
import com.cz.mapper.ItemMapper;
import com.cz.model.category.Category;
import com.cz.model.item.Item;
import com.cz.model.order.Order;
import com.cz.model.param.Param;
import com.cz.model.personal.Role;
import com.cz.model.personal.User;
import com.cz.dto.user.DtoUser;
import com.cz.service.search.EsClient;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jomalone_jia on 2017/6/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring.xml"})
public class TestConfig {
    @Autowired
    private IUserService userService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IItemService itemService;
    @Autowired
    private IParamService paramService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private ItemMapper itemMapper;

    @Test
    public void test1() {
        User admin = userService.loadUserByUsername("admin");
        System.out.println(admin);
    }

    @Test
    public void test2() {
        EntityWrapper<User> ew = new EntityWrapper<User>();
        ew.where("name={0}", "'zhangsan'").and("id=1")
                .orNew("status={0}", "0").or("status=1")
                .notLike("nlike", "notvalue")
                .andNew("new=xx").like("hhh", "ddd")
                .andNew("pwd=11").isNotNull("n1,n2").isNull("n3")
                .groupBy("x1").groupBy("x2,x3")
                .having("x1=11").having("x3=433")
                .orderBy("dd").orderBy("d1,d2");
        System.out.println(ew.getSqlSegment());
    }

    @Test
    public void test3() {
        EntityWrapper<User> ew = new EntityWrapper<User>();
        ew.where("username={0}", "admin").and("id=1");
        System.out.println(ew.getSqlSegment());
        System.out.println(userService.selectOne(ew));
    }

    @Test
    public void test4() {
        EntityWrapper<User> ew = new EntityWrapper<User>();
        ew.where("username={0}", "admin");
        System.out.println(ew.getSqlSegment());
        System.out.println(userService.selectOne(ew));
    }

    @Test
    public void test5() {
        EntityWrapper<User> ew = new EntityWrapper<User>();
        ew.where("username={0}", "disable");
        System.out.println(ew.getSqlSegment());
        User user = new User();
        user.setUsername("disabled");
        System.out.println(userService.update(user, ew));
    }

    @Test
    public void test6() {
        Page<User> page = new Page<User>(1, 10);
        Page page1 = userService.listUserWithRole(page);
        System.out.println(page1.toString());
        System.out.println(page1.getRecords());
    }

    @Test
    public void test7() {
        User user = new User();
        user.setId(4L);
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("test1");
        Role role1 = new Role();
        role1.setId(2L);
        role1.setRoleName("test2");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        roles.add(role1);
        System.out.println(roles);
        user.setRoles(roles);
        user.setFullname("fullname");
        user.setId(4L);
        userService.updateUserWithRole(user);
    }


    @Test
    public void test8() {
        DtoUser dtoUser = new DtoUser();
        dtoUser.setUsername("aluba");
        dtoUser.setPassword("123456");
        User user = userService.registerUser(dtoUser);
        System.out.println(user);
    }

    @Test
    public void test9() {
        System.out.println(userService.isUserExsit("aluba"));
    }


    @Test
    public void test10() {

        System.out.println(userService.listRelatedUsers(892550087922724864L));
    }

    @Test
    public void test11() {
        List<Category> categories = categoryService.listCategories();
        List cats = new ArrayList<ArrayList<Category>>();
        for (Category category : categories) {
            System.out.println(category);
            if (category.getParentId() == 0) {

            } else {
            }
        }
        System.out.println(cats);
    }

    @Test
    public void test12() {
        EntityWrapper<Category> ew = new EntityWrapper<Category>();
        ew.isNotNull("category_name");
        Map<String, Object> stringObjectMap = categoryService.selectMap(ew);
        System.out.println(stringObjectMap);
    }


    @Test
    public void test13() {
        Category category = new Category();
        category.setParentId(1);
        category.setName("aluba");
        categoryService.insertCategory(category);
        System.out.println(category.getId());
        System.out.println(category);
    }

    @Test
    public void test14() {
        List<Category> categories = categoryService.listChildCategories(1L);
        System.out.println(categories.toString());
    }

    @Test
    public void test15() {
        Category category = new Category();
        category.setId(49L);
        category.setName("aluba122");
        category.setParentId(2);
        EntityWrapper<Category> ew = new EntityWrapper<Category>();
        ew.where("category_id={0}", category.getId());

        categoryService.update(category, ew);
    }

    @Test
    public void test16() {
        Category category = new Category();
        category.setName("aluba121232");
        category.setParentId(2);
        categoryService.insert(category);
    }

    @Test
    public void test17() {
        Object o = itemService.selectImages("6f4a2ec79cee495991ac3b4f491fa725");
        System.out.println(o.toString());
    }

    @Test
    public void test18() {
        Integer aluba = itemService.updateImageById("6f4a2ec79cee495991ac3b4f491fa725", "aluba");
        System.out.println(aluba);
    }

    @Test
    public void test19() {

    }

    @Test
    public void test20() {
        /*Item item = new Item();
        item.setImage("");
        item.setDescribe("");
        item.setName("alubabaaba");
        boolean insert = itemService.insert(item);
        System.out.println(insert);
        System.out.println("___________________");
        System.out.println(item.getId());*/
    }

    @Test
    public void test21() {
        List<Category> categories = categoryService.listCategories();
        List list = categoryService.listTreeCategories();
        System.out.println("_--------------------------");
        System.out.println(list.toString());
    }

    @Test
    public void test22() {
        List<Param> params = paramService.listParams();
        System.out.println(params.toString());
    }

    @Test
    public void test23() {
        /*List params = new ArrayList<Param>();
        Param param1 = new Param();
        Param param2 = new Param();

        param1.setId(2);
        param1.setParamDescribe("尺码");
        List param1Values = new ArrayList<ParamValue>();
        param1Values.add(new ParamValue(4,"XS"));
        param1Values.add(new ParamValue(5,"S"));
        param1.setParamDetailss(param1Values);

        param2.setId(1);
        param2.setParamDescribe("颜色");
        List param2Values = new ArrayList<ParamValue>();
        param2Values.add(new ParamValue(-1,"#183652"));
        param2Values.add(new ParamValue(-1,"#516f8b"));
        param2.setParamValues(param2Values);

        params.add(param1);
        params.add(param2);

        System.out.println(params.toString());

        paramService.insertParams("1111",params);*/
    }

    @Test
    public void test24() {
        List<Param> params = paramService.listParamsById("086c65c0a17843e8a5375ded70f52b68");
        System.out.println(params.toString());
    }

    @Test
    public void test25() {
        PageInfo<Item> itemPageInfo = itemService.listItems(1, 5);
        System.out.println(itemPageInfo.toString());
    }

    @Test
    public void test26() {
        PageInfo<Order> orderPageInfo = orderService.listOrders(1, 2);
        System.out.println(orderPageInfo);
    }

    @Test
    public void test27() {
        Order order = orderService.selectById("5e1c8f8a0e1540e09b85153bdaf36fb0");
        System.out.println(order.getStatus().next());
    }

    @Test
    public void test28() {
        orderService.updateStatus("cba4b9c63db54668a777617eed1ad37c", "aluba");
    }

    @Test
    public void test29() {
        itemService.saveOrUpdateItemContent(new ItemContent("60e054fdd0c74824bbbac46bf7d08603", null));
    }

    @Test
    public void test30() {
        String content = "<p><img src=\"http://otlht2gvo.bkt.clouddn.com/47497093002C81D6B508CD5B2FC08A8B3E799A4A689D66764F79E2B3FCE0750A\">" +
                "<img src=\"http://otlht2gvo.bkt.clouddn.com/F664120E342672BF09DE7B1FDE48AB3376B69E4F26786CEB872394044E918CCF\">" +
                "<img src=\"http://otlht2gvo.bkt.clouddn.com/354350D09E28152D1CB75EB5E582782064A075AAC478C304361172A473D6E63C\"></p>";
        Pattern deletePattern = Pattern.compile("<img src=\"http://otlht2gvo.bkt.clouddn.com/(.*?)\">");
        Matcher matcher = deletePattern.matcher(content);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }

    @Test
    public void test31() {
        String content = "<p><img src=\"http://otlht2gvo.bkt.clouddn.com/DD3E989EA92BD5A13B1AAD26145191DEEDE59898D63270EFE779BBE9D4646D64\"><img\n" +
                "        src=\"http://otlht2gvo.bkt.clouddn.com/EBC4793E49EEB182D8CA2D6BA51BB988DDA04412F10FD7F5FF99AA6B68B65644\"><img\n" +
                "        src=\"http://otlht2gvo.bkt.clouddn.com/FDEC22393BA11146610B4DF8D664F46695D09FD685E82C0713BC751851B3ECB8\"><img\n" +
                "        src=\"http://otlht2gvo.bkt.clouddn.com/434B84AD3787F14B80858478636CCB2A8D72B4BBC51A2EC50D62471DFFFAE33D\"></p>";
        Pattern deletePattern = Pattern.compile("<img src=\"http://otlht2gvo.bkt.clouddn.com/(.*?)\">");
        Matcher matcher = deletePattern.matcher(content);
        ArrayList<String> deleteArray = new ArrayList<>();
        while (matcher.find()) {
            deleteArray.add(matcher.group(1));
        }
        PictureUtil.getInstance().bucketDelete(deleteArray.toArray(new String[0]));
    }

    @Test
    public void test32() {
        itemMapper.deleteItemContentById("60e054fdd0c74824bbbac46bf7d08603");
    }

    @Test
    public void test33(){
        Item item = new Item();
        item.setId("bb");
        item.setPrice(299);
        item.setContent("free to play");
        EsClient.add(item);
        itemService.insertItem(item);
    }
}

