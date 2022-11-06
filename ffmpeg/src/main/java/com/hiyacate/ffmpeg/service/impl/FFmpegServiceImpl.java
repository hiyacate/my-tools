package com.hiyacate.ffmpeg.service.impl;

import com.hiyacate.ffmpeg.service.FFmpegService;
import com.hiyacate.ffmpeg.utils.FFmpegUtils;
import com.hiyacate.ffmpeg.vo.ReqBody;
import com.hiyacate.ffmpeg.entity.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;


/**
 * @author hiya
 * @date 2022/9/1 23:49
 * @description
 */
@Service
@Slf4j
public class FFmpegServiceImpl implements FFmpegService {

    @Override
    public R concat(ReqBody reqBody) throws IOException {
        String desPath = reqBody.getDesPath();
        String outPath = reqBody.getOutPath();
        String targetFile = reqBody.getTargetFile();
        List<String> fileList = FFmpegUtils.getFileList(desPath, targetFile);

        log.info("待处理文件列表如下:");
        for (String s : fileList) {
            log.info(s.toString());
        }

        for (String fileName : fileList) {
            FFmpegUtils.reName(fileName);
        }

        List<String> fileListNew = FFmpegUtils.getFileList(desPath, targetFile);
        int i = 0;
        for (String fileName : fileListNew) {
            FFmpegUtils.getTs(outPath, fileName, String.valueOf(i));
            i += 1;
        }

        List<String> inputList = FFmpegUtils.getFileList(outPath, "input");

        String s = fileListNew.get(0);
        String outFileName = FFmpegUtils.getOutFileName(s, desPath, outPath);

        int inputSize = inputList.size();

        FFmpegUtils.getConcat(outPath, inputSize, outFileName);

        FFmpegUtils.deleteInputFiles(reqBody);

        log.info("输出路径为:\n {}", outFileName);
        return R.success(outFileName);
    }


}
