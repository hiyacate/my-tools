package com.hiyacate.ffmpeg.service;

import com.hiyacate.ffmpeg.vo.ReqBody;
import com.hiyacate.ffmpeg.entity.R;

import java.io.IOException;

/**
 * @author hiya
 * @date 2022/9/1 23:49
 * @description
 */
public interface FFmpegService {

    R concat(ReqBody reqBody) throws IOException;
}
