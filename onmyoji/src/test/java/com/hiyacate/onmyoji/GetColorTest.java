package com.hiyacate.onmyoji;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;

/**
 * @author hiya
 * @description
 * @date 2022-10-25 14:27
 */

@SpringBootTest
public class GetColorTest {
    @Test
    public void test() throws AWTException {
        Robot robot = new Robot();
        Color color = robot.getPixelColor(939,691);
        System.out.println(color);
    }
}
