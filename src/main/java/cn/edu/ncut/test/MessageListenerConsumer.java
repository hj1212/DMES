package cn.edu.ncut.test;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.*;
import java.io.IOException;

/**
 * @author lixiwei-mac
 * @create 00:08
 */
public class MessageListenerConsumer
{
    public static void main(String[] args) throws JMSException, IOException
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext*");
        PooledConnectionFactory connectionFactory = context.getBean(PooledConnectionFactory.class);
        ActiveMQQueue destination = context.getBean(ActiveMQQueue.class);
        Connection connection = connectionFactory.createConnection();

        connection.start();
        Session session  = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);//接收到消息立刻从队列中移除;完成业务后手动删除设置为CLIENT_ACKONOWLEDGE
        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(new ActiveMQListener());
        System.in.read();
    }
    static class ActiveMQListener implements MessageListener
    {
        @Override
        public void onMessage(Message message)
        {
            if (message instanceof TextMessage)
            {
                try
                {
                    System.out.println(((TextMessage)message).getText());
//                    message.acknowledge();
                } catch (JMSException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
