package com.hiyacate.onmyoji;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hiya
 * @description
 * @date 2022-10-25 14:25
 */
@SpringBootApplication
public class OnmyojiApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnmyojiApplication.class,args);
        System.out.println("ffmpeg 启动成功");
    }
}
