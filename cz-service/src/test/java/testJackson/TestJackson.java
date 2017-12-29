package testJackson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by jomalone_jia on 2017/6/19.
 */
public class TestJackson {

    @Test
    public void test1(){
        System.out.println("javabean转化示例开始----------");
        Person person = new Person("1","fastjson",1,new User("admin","123456"));

        //这里将javabean转化成json字符串
        String jsonString = JSON.toJSONString(person);
        System.out.println(jsonString);
        //这里将json字符串转化成javabean对象,
        person =JSON.parseObject(jsonString,Person.class);
        System.out.println(person.toString());

        System.out.println("javabean转化示例结束----------");
    }

    @Test
    public void test2(){
        System.out.println("List<javabean>转化示例开始----------");

        Person person1 = new Person("1","fastjson1",1,new User("admin","123456"));
        Person person2 = new Person("2","fastjson2",2,new User("user","654321"));
        List<Person> persons = new ArrayList<Person>();
        persons.add(person1);
        persons.add(person2);
        String jsonString = JSON.toJSONString(persons);
        System.out.println("json字符串:"+jsonString);

        //解析json字符串
        List<Person> persons2 = JSON.parseArray(jsonString,Person.class);
        //输出解析后的person对象，也可以通过调试模式查看persons2的结构
        System.out.println("person1对象："+persons2.get(0).toString());
        System.out.println("person2对象："+persons2.get(1).toString());

        System.out.println("List<javabean>转化示例结束----------");
    }
}
