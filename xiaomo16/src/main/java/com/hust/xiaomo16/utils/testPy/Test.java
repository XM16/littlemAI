package com.hust.xiaomo16.utils.testPy;

import java.io.IOException;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Scanner;
 
public class Test {



    public static void main(String[] args) throws IOException {
//        String result = "";
        String userInput = "";

        System.out.println("小莫智能机器人正在为您服务~");
        System.out.println("我是智能（智障）机器人小莫，有什么能帮到你？可以尝试输入__我要预约__ 菜单浏览__优惠信息__爆品推荐__我要点餐");

        Scanner sc = new Scanner(System.in);
        while (true) {

            System.out.print("用户： ");
            userInput = sc.nextLine();
            chat(userInput);

        }

    }

    public static void chat(String userInput) throws IOException {
        String result = "";
        try {
            Process process = Runtime.getRuntime().exec("python /Users/boon/Downloads/QQ下载/xiaoMo04.py  " + userInput);

            InputStreamReader ir = new InputStreamReader(process.getInputStream(), "UTF-8");
            LineNumberReader input = new LineNumberReader(ir);
            while (result != null) {
                result = input.readLine();
                if (result != null) {
                    System.out.println(result);
                }
            }
            input.close();
            ir.close();
            process.waitFor();
        } catch (Exception e) {
        System.out.println("调用python脚本并读取结果时出错：" + e.getMessage());
    }
    }

}