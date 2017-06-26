package testAop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by admin on 2017/6/23.
 */
public class Test1 {

    @Test
    public void t1(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:1.xml");
        //我们直接在这里获取Person对象就可以了，因为在最开始xml文件newPerson对象后，Spring就已经帮我们代理了！
        Person p =ctx.getBean(Person.class);
        p.sleep();
    }

    @Test
    public void t2(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:2.xml");
        //我们直接在这里获取Person对象就可以了，因为在最开始xml文件newPerson对象后，Spring就已经帮我们代理了！
        Person p =ctx.getBean(Person.class);
        p.sleep();
    }

    @Test
    public void t3(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:3.xml");
        Around3 p =ctx.getBean(Around3.class);
        p.sleep();
    }
}
