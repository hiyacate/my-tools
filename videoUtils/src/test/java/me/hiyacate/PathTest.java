package me.hiyacate;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hiya
 * @description
 * @date 2022-11-06 13:23
 */
@SpringBootTest
@Slf4j
public class PathTest {

    @Test
    public void showFile() {
        ArrayList<String> files = new ArrayList<String>();
        String sourcePath = "/Users/hiya/Movies";
        File file = new File(sourcePath);
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                files.add(tempList[i].toString());
            }
            if (tempList[i].isDirectory()) {
                System.out.println("文件夹:" + tempList[i]);

            }
        }
        System.out.println(files);

    }

    @Test
    public void StringTest() {
        String file = "/User/hiya/Movie/test.mp4";
        boolean b = file.endsWith("/");
        String pattern = "/\\w+\\.\\w+";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(file);
        if (m.find()) {
            String group = m.group();
            String[] split = group.split("/");
            System.out.println(split[1]);
        }

    }

    @Test
    public void showFiles() {
        ArrayList<String> fileList= new ArrayList<>();
        String path = "/Users/hiya/temp";
        show(path, fileList);
        for (int i = 0; i < fileList.size(); i++) {
            System.out.println(fileList.get(i));
        }
    }

    public void show(String path, ArrayList<String> fileList) {
        File dir = new File(path);
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; i++) {
                File file = new File(files[i].toString());
                if (file.isDirectory()) {
                    show(file.toString(), fileList);
                }
                if(file.isFile()){
                    fileList.add(file.toString());
                }
            }
        }

    }

    @Test
    public void startTest(){
        String name = ".DS_Store";
        boolean b = name.startsWith(".");
    }
}
