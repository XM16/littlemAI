package com.hust.xiaomo16.utils.testJS;

/**
 * @program: XM16
 * @description:
 * @author: Boon Guan
 * @create: 2019-02-22 10:18
 **/
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import javax.script.Invocable;

import javax.script.ScriptEngine;

import javax.script.ScriptEngineManager;

public class testJS {


    public static String chatBot(String userInput) throws IOException {
        String result = "";
        try {
            Process process = Runtime.getRuntime().exec("python /Users/boon/Downloads/QQ下载/xiaoMo04.py  " + userInput);

            InputStreamReader ir = new InputStreamReader(process.getInputStream(), "UTF-8");
            LineNumberReader input = new LineNumberReader(ir);
            while (result != null) {
                result = input.readLine();
                if (result != null) {
                    return result;
                }
            }
            input.close();
            ir.close();
            process.waitFor();
        } catch (Exception e) {
            System.out.println("调用python脚本并读取结果时出错：" + e.getMessage());
        }
        return result;
    }

}
