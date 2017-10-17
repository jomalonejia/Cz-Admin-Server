package testmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by jomalone_jia on 2017/6/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring.xml"})
public class TestConfig {

    @Autowired
    @Qualifier("jmsQueueTemplate")
    private JmsTemplate jmsQueueTemplate;

    @Autowired
    @Qualifier("jmsTopicTemplate")
    private JmsTemplate jmsTopicTemplate;

    @Test
    public  void test(){
        jmsQueueTemplate.send("testQueue", new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage("xiaoyang1");
            }
        });
       /* jmsTopicTemplate.send("testTopic", new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage("xiaoyang2");
            }
        });*/
    }
}
