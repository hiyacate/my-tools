package com.hiyacate.hiyaOcr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hiya
 * @description
 * @date 2023-01-09 21:51
 */
@SpringBootApplication
public class OcrApplication {
    public static void main(String[] args) {
        SpringApplication.run(OcrApplication.class,args);
        System.out.println("Ocr 启动成功");
    }
}
