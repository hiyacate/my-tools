package me.hiyacate.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;

/**
 * @author hiya
 * @description
 * @date 2022-11-06 13:17
 */
@Slf4j
public class HandleVideo {
    ArrayList<String> fileList = new ArrayList<>();

    public static ArrayList<String> getFileList(String desPath) {
        ArrayList<String> fileList = new ArrayList<>();
        getAllFile(desPath, fileList);
        log.info("文件夹下所有文件为");
        fileList.forEach((i)->{
            String[] split = i.split("/");
            int length = split.length;
            log.info(split[length-1]);

        });
        return fileList;


    }

    public static void mvFile(ArrayList<String> files, String targetPath) {
        for (int i = 0; i < files.size(); i++) {
            try {
                String file = files.get(i);
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
                        new File(file)
                ));

                String[] split = file.split("/");
                String fileName = split[split.length - 1];

                log.info("\nfileName为{}", fileName);
                log.info("\n输出路径为:{}", targetPath + fileName);

                File fileNew = new File(targetPath + fileName);
                if (fileNew.exists()) {
                    log.info("\n该文件已存在");
                    continue;
                }

                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(
                        targetPath + fileName
                ));


                byte[] bys = new byte[1024];
                int len = 0;
                while ((len = bis.read(bys)) != -1) {
                    bos.write(bys, 0, len);
                }
                bos.close();
                bis.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private static void getAllFile(String sourcePath, ArrayList<String> fileList) {
        File dir = new File(sourcePath);
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file = new File(files[i].toString());
            if (file.isDirectory()) {
                getAllFile(file.toString(), fileList);
            } else {
                String[] split = file.toString().split("/");
                int length = split.length;
                String fileName = split[length - 1];
                if(!fileName.startsWith(".")) {
                    fileList.add(file.toString());
                }
            }

        }

    }
}