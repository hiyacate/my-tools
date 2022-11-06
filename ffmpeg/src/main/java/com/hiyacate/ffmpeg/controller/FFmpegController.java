package com.hiyacate.ffmpeg.controller;

import com.hiyacate.ffmpeg.vo.ReqBody;
import com.hiyacate.ffmpeg.service.FFmpegService;
import com.hiyacate.ffmpeg.entity.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author hiya
 * @date 2022/9/1 23:50
 * @description
 */
@RestController
@RequestMapping("/ffmpeg")
@CrossOrigin(origins = "*")
public class FFmpegController {

    @Resource
    private FFmpegService ffmpegService;

    @PostMapping("/concat")
    @CrossOrigin(origins = "*")
    public R concat(@RequestBody ReqBody reqBody) throws IOException {
        return ffmpegService.concat(reqBody);
    }
}
