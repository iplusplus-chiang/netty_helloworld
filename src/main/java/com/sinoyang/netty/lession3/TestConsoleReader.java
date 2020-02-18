package com.sinoyang.netty.lession3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 从控制台获取输入。
 */
public class TestConsoleReader {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(;;) {
            System.out.println(br.readLine() + "\r\n");
        }
    }
}