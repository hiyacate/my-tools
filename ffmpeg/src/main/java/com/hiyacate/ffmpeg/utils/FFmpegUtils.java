package com.hiyacate.ffmpeg.utils;

import com.hiyacate.ffmpeg.vo.ReqBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hiya
 * @date 2022/9/4 22:30
 * @description
 */
@Slf4j
public class FFmpegUtils {

    public static List<String> getFileList(String desPath, String targetFile) {
        File dir = new File(desPath);
        ArrayList<String> videoList = new ArrayList<>();
        File[] fileList = dir.listFiles();
        if (fileList == null) {
            return null;
        }
        for (File file : fileList) {
            if (file.isDirectory()) {
                continue;
            }
            String fileName = file.toString();
            Pattern pattern = Pattern.compile(targetFile);
            Matcher matcher = pattern.matcher(fileName);
            if (matcher.find()) {
                videoList.add(fileName);
            }
        }

        Collections.sort(videoList);
        return videoList;
    }

    public static void getTs(String outPath, String fileName, String fileNumber) throws IOException {

        log.info("-------{}-------", "转换开始");
        String cmd = "ffmpeg -i " + fileName + " -c copy -bsf:v h264_mp4toannexb -f mpegts "
                + outPath + "input" + fileNumber + ".ts";
        runCommand(cmd);

        log.info("-------{}-------", "转换完成");
    }

    public static void getConcat(String outPath, int inputSize, String outPutName) {
        log.info("-------{}-------", "拼接开始");
        try {
            StringBuilder concatContext = new StringBuilder();
            for (int i = 0; i < inputSize; i++) {
                concatContext.append("file '").append(outPath).append("input").append(i).append(".ts'\n");
            }
//        String substring = concatContext.substring(0, concatContext.length() - 1) + "\"";
            File file = new File(outPath + "concat.txt");
            boolean createNewFile = true;
            if (!file.exists()) {
                createNewFile = file.createNewFile();
            }
            if (createNewFile) {
                log.info("concat.txt 文件创建成功");
            } else {
                log.info("concat.txt 文件已经存在");
            }

            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            System.out.println(concatContext);
            bufferedWriter.write(String.valueOf(concatContext));
            bufferedWriter.close();
            String cmd = "ffmpeg -f concat -safe 0 -i " + file + " -c copy " + outPutName;
            runCommand(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("-------{}-------", "拼接完成");
    }

    public static void reName(String desFileName) {
        File oldName = new File(desFileName);
        File newName = new File(desFileName.replace(" ", "_"));
        if (newName.exists()) {
            System.out.println("无需重命名");
            return;
        }
        if (oldName.renameTo(newName)) {
            System.out.println("已重命名");
        } else {
            System.out.println("reName error");
        }


    }

    public static void runCommand(String cmd) throws IOException {
        List<String> command = new ArrayList<>();
        String[] strings = cmd.split(" ");
        for (String string : strings) {
            //command.add(string);
            Collections.addAll(command, string);
        }
        ProcessBuilder builder = new ProcessBuilder(command);
        builder.directory(new File("./ffmpeg/src/main/resources/ffmpeg"));
//        builder.directory(new File(ffmpeg));
        builder.redirectErrorStream(true);
        log.info("\n完整命令: {}", String.join(StringUtils.SPACE, builder.command()));
        Process process = null;
        try {
            process = builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (process != null) {
            InputStreamReader is = new InputStreamReader(process.getInputStream(), "gbk");
            BufferedReader br = new BufferedReader(is);
            while (br.readLine() != null) {
                System.out.println(br.readLine());
            }
        } else {
            return;
        }

        if (process.isAlive()) {
            process.destroy();
        }
    }

    public static void deleteInputFiles(ReqBody reqBody) {
        String outPath = reqBody.getOutPath();
        List<String> inputList = getFileList(outPath, "input");
        if (inputList == null) {
            return;
        }
        for (String inputFile : inputList) {
            File file = new File(inputFile);
            if (file.exists()) {
                file.delete();
                log.info("过渡文件---{}---删除成功", file);
            }
        }
    }

    public static String getOutFileName(String s, String desPath, String outPath) {

        Pattern pattern = Pattern.compile("\\d*.mkv");
        Matcher matcher = pattern.matcher(s);
        boolean matcherFlag;
        matcherFlag = matcher.find();
        if (matcherFlag) log.info("找到了匹配对象");
        else log.info("没有匹配对象");

        String resultName = s.replace(matcher.group(), ".mp4");

        return resultName.replace(desPath, outPath);
    }
}
