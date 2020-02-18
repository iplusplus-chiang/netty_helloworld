package com.sinoyang.netty.lession6;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyServerHanlder extends SimpleChannelInboundHandler<MyDataInfoUnion.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfoUnion.MyMessage msg) throws Exception {

        //System.out.println("获取客户端发送的消息：" + msg.getName() + ", " + msg.getAge() + ", " + msg.getAddress());
        MyDataInfoUnion.MyMessage.DataType dataType = msg.getDataType();
        if(dataType == MyDataInfoUnion.MyMessage.DataType.PersonType) {
            MyDataInfoUnion.Person person = msg.getPerson();
            System.out.println("获取客户端发送的消息(Person)：" + person.getName() + ", " + person.getAge() + ", " + person.getAddress());
        } else if(dataType == MyDataInfoUnion.MyMessage.DataType.CatType) {
            MyDataInfoUnion.Cat cat = msg.getCat();
            System.out.println("获取客户端发送的消息(Cat)：" + cat.getName() + ", " + cat.getSex());
        } else if(dataType == MyDataInfoUnion.MyMessage.DataType.DogType) {
            MyDataInfoUnion.Dog dog = msg.getDog();
            System.out.println("获取客户端发送的消息(Dog)：" + dog.getName() + ", " + dog.getAge());
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    }
}