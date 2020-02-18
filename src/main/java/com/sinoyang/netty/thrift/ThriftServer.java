package com.sinoyang.netty.thrift;

import com.sinoyang.netty.thrift.generated.PersonService;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

/**
 * 服务端
 */
public class ThriftServer {

    public static void main(String[] args) throws Exception{

        // 非阻塞的Socket
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);
        THsHaServer.Args args1 = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<PersonServiceImpl>(new PersonServiceImpl());
        // 设定工厂
        args1.protocolFactory(new TCompactProtocol.Factory());
        args1.transportFactory(new TFastFramedTransport.Factory());
        args1.processorFactory(new TProcessorFactory(processor));
        // 启动server
        TServer tServer = new THsHaServer(args1);
        System.out.println("Thrift is started.......");
        tServer.serve();
    }
}