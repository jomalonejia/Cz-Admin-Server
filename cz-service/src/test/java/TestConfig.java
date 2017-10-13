import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cz.api.service.ICategoryService;
import com.cz.api.service.IItemService;
import com.cz.item.DtoCategory;
import com.cz.mapper.UserMapper;
import com.cz.model.Category;
import com.cz.model.Role;
import com.cz.model.User;
import com.cz.api.service.IUserService;
import com.cz.model.UserRole;
import com.cz.user.DtoUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private IItemService iItemService;

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
            if (category.getParentCategoryId() == 0) {

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
        category.setParentCategoryId(1);
        category.setCategoryName("aluba");
        int i = categoryService.insertCategory(category);
        System.out.println(i);
        System.out.println(category.getCategoryId());
        System.out.println(category);
    }

    @Test
    public void test14(){
        List<Category> categories = categoryService.listChildCategories(1L);
        System.out.println(categories.toString());
    }

    @Test
    public void test15(){
        Category category = new Category();
        category.setCategoryId(49L);
        category.setCategoryName("aluba122");
        category.setParentCategoryId(2);
        EntityWrapper<Category> ew = new EntityWrapper<Category>();
        ew.where("category_id={0}", category.getCategoryId());

        categoryService.update(category,ew);
    }

    @Test
    public void test16(){
        Category category = new Category();
        category.setCategoryName("aluba121232");
        category.setParentCategoryId(2);
        categoryService.insert(category);
    }
}

