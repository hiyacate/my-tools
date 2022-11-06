package com.hiyacate.onmyoji.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author hiya
 * @description
 * @date 2022-10-25 15:41
 */
public class ImageUtil {

    public static int[][] getImageRGB(BufferedImage bfImage) {
        int width = bfImage.getWidth();
        int height = bfImage.getHeight();
        int[][] result = new int[height][width];
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                result[h][w] = bfImage.getRGB(w, h);

            }
        }
        return result;
    }
}