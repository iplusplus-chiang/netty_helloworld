package com.sinoyang.netty.protobuf;

import org.junit.Test;

/**
 * 测试protobuf
 */
public class TestDataInfo {

    @Test
    public void test1() throws Exception {
        // 构建对象
        DataInfo.Student student1 = DataInfo.Student.newBuilder().setName("张三").setAge(22).setAddress("湖南人士").build();
        // 转换成字节数组
        byte[] studentBytes = student1.toByteArray();
        // 反序列化回来。
        DataInfo.Student student2 = DataInfo.Student.parseFrom(studentBytes);
        // 打印各个属性
        System.out.println(student2.getName() + ", " +student2.getAge() + ", " + student2.getAddress());
    }
}