package com.hiyacate.ffmpeg.vo;

import lombok.Data;

/**
 * @author hiya
 * @date 2022/9/3 19:14
 * @description
 */
@Data
public class ReqBody {
    private String desPath;
    private String outPath;
    private String targetFile;
}
