package me.hiyacate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hiya
 * @description
 * @date 2022-11-06 12:59
 */
@SpringBootApplication
public class VideoApplication {
    public static void main(String[] args) {
        SpringApplication.run(VideoApplication.class, args);
        System.out.println("video启动成功");
    }
}
