package com.sinoyang.netty.thrift;

import com.sinoyang.netty.thrift.generated.Person;
import com.sinoyang.netty.thrift.generated.PersonService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class ThriftClient {

    public static void main(String[] args) {

        TTransport transport = new TFastFramedTransport(new TSocket("localhost", 8899, 600));
        TProtocol tProtocol = new TCompactProtocol(transport);
        PersonService.Client client = new PersonService.Client(tProtocol);
        /////////////////////////////////////////////////////////////////////////////
        try {
            // 打开socket
            transport.open();
            // 调用方法
            Person person = client.getPersonByUsername("张三");
            System.out.println("获取服务器数据：" + person.getUsername() + ", " + person.getAge() + ", " + person.isMarried());
            // 调用方法
            Person person1 = new Person();
            person1.setUsername("李五");
            person1.setAge(19);
            person1.setMarried(true);
            client.savePerson(person1);
            /////////////////////////////////////
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            transport.close();
        }
    }
}
