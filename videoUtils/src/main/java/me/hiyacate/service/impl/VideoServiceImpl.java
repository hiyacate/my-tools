package me.hiyacate.service.impl;

import me.hiyacate.entity.R;
import me.hiyacate.service.VideoService;
import me.hiyacate.utils.HandleVideo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author hiya
 * @description
 * @date 2022-11-06 13:04
 */
@Service
public class VideoServiceImpl implements VideoService {
    @Override
    public R moveFile(String desPath, String targetFile, String targetPath) {
        ArrayList<String> files = HandleVideo.getFileList(desPath);
        HandleVideo.mvFile(files, targetPath);

        return R.success("移动文件成功");
    }
}
