package com.hiyacate.ffmpeg;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SpringBootTest
public class concat {
    @Test
    public void concat() throws IOException {
        int inputSize = 2;
        StringBuilder concatContext = new StringBuilder();
        for (int i = 0; i < inputSize; i++) {
            concatContext.append("file '").append("/User/hiya/").append("input").append(i).append(".ts'").append("\n");
        }
//        String substring = concatContext.substring(0, concatContext.length() - 1) + "\"";
        File file = new File("/Users/hiya/concat.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file.getName(), true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        System.out.println(concatContext);
        bufferedWriter.write(String.valueOf(concatContext));
        bufferedWriter.close();
    }

    @Test
    public void w () throws IOException {
        String content = "a dog will be write in file\na cat will be sleep\n";
        File file = new File("/Users/hiya/concat.txt");
        if(!file.exists()){
            file.createNewFile();
        }
//        FileWriter fw = new FileWriter(file.getName(), true); 用这行代码, 内容写不到文件
        FileWriter fw = new FileWriter(file.getAbsoluteFile(),false);
        BufferedWriter bw = new BufferedWriter(fw);
        System.out.println(content);
        bw.write(content);
        bw.close();
    }
}
