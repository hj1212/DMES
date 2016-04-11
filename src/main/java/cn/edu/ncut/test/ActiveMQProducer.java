package cn.edu.ncut.test;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.*;
import java.io.IOException;

/**
 * ActiveMQ消息发送示例（利用Producer）
 * Author:菩提树下的杨过 http://yjmyzz.cnblogs.com
 */
public class ActiveMQProducer {

    public static void main(String[] args) throws JMSException, IOException, InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext*");
        PooledConnectionFactory connectionFactory = context.getBean(PooledConnectionFactory.class);
        ActiveMQQueue destination = context.getBean(ActiveMQQueue.class);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer = session.createProducer(destination);

        System.out.println("准备发送消息...");
        int max = 1000;
        Long start = System.currentTimeMillis();

        for (int i = 0; i < max; i++) {
            TextMessage msg = session.createTextMessage("message test:" + i);
            //msg.setIntProperty("id", i);
            producer.send(msg);
        }
        Long end = System.currentTimeMillis();
        Long elapse = end - start;
        int perform = Double.valueOf(max / (elapse / 1000d)).intValue();

        System.out.print("发送 " + max + " 条消息，耗时：" + elapse + "毫秒，平均" + perform + "条/秒");

        //producer.send(session.createTextMessage("SHUTDOWN"));
        //Thread.sleep(1000 * 3);
        //connection.close();
        System.exit(0);
    }
}