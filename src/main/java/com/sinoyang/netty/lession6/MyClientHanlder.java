package com.sinoyang.netty.lession6;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 *
 */
public class MyClientHanlder extends SimpleChannelInboundHandler<MyDataInfoUnion.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfoUnion.MyMessage msg) throws Exception {
    }

    /**
     * 客户端发送一条数据
     *
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        // 构建对象
        // MyDataInfo.Person person = MyDataInfo.Person.newBuilder().setName("客户端对象").setAge(17).setAddress("客户端地址").build();
        // 构建对象
        MyDataInfoUnion.MyMessage myMessage = null;
        int random = new Random().nextInt(3);
        if (random == 0) {
            MyDataInfoUnion.Person person = MyDataInfoUnion.Person.newBuilder().setName("对象1").setAge(17).setAddress("对象1地址").build();
            myMessage = MyDataInfoUnion.MyMessage.newBuilder().setDataType(MyDataInfoUnion.MyMessage.DataType.PersonType).setPerson(person).build();
        } else if(random == 1) {
            MyDataInfoUnion.Cat cat = MyDataInfoUnion.Cat.newBuilder().setName("麻奇").setSex("公猫").build();
            myMessage = MyDataInfoUnion.MyMessage.newBuilder().setDataType(MyDataInfoUnion.MyMessage.DataType.CatType).setCat(cat).build();
        } else if(random == 2) {
            MyDataInfoUnion.Dog dog = MyDataInfoUnion.Dog.newBuilder().setName("小旺").setAge(2).build();
            myMessage = MyDataInfoUnion.MyMessage.newBuilder().setDataType(MyDataInfoUnion.MyMessage.DataType.DogType).setDog(dog).build();
        }
        // 发送消息
        ctx.channel().writeAndFlush(myMessage);
    }

    /**
     * 出现异常的时候
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.fillInStackTrace();
        ctx.close();
    }
}