package cn.edu.ncut.test;

import org.apache.activemq.broker.BrokerService;

/**
 * @author lixiwei-mac
 * @create 00:16
 */
public class EmbbedBroker
{
    public static void main(String[] args) throws Exception
    {
        BrokerService broker = new BrokerService();
        broker.addConnector("tcp://localhost:61616");
        broker.start();
        System.out.println("ActiveMQ 已启动s");
    }
}
