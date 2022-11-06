package com.hiyacate.ffmpeg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hiya
 * @date 2022/9/1 23:43
 * @description
 */
@SpringBootApplication
public class FFmpegApplication {
    public static void main(String[] args) {
        SpringApplication.run(FFmpegApplication.class,args);
        System.out.println("ffmpeg 启动成功");
    }
}
