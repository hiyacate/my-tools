package me.hiyacate.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.hiyacate.entity.R;
import me.hiyacate.service.VideoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author hiya
 * @description
 * @date 2022-11-06 13:02
 */
@RestController
@RequestMapping("/video")
@Slf4j
public class VideoController {
    @Resource
    private VideoService videoService;

    @PostMapping("/moveFile")
    public R moveFile(@RequestBody JSONObject jsonObject) {
        String desPath = (String) jsonObject.get("desPath");
        String targetFile = (String) jsonObject.get("targetFile");
        String targetPath = (String) jsonObject.get("targetPath");
//        if (StringUtils.isBlank(desPath) && StringUtils.isBlank(targetFile)) {
        if (StringUtils.isBlank(desPath) && StringUtils.isBlank(targetPath)) {
            log.error("sourcePath和targetFile不能为空");
        }
        R result = videoService.moveFile(desPath, targetFile, targetPath);
        return result;

    }
}
