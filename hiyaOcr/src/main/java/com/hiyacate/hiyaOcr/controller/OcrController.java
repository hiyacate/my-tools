package com.hiyacate.hiyaOcr.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hiyacate.hiyaOcr.utils.HttpUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author hiya
 * @description
 * @date 2023-01-09 22:24
 */
@Controller
@RequestMapping("/ocr")
public class OcrController {

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public String test() throws Exception {
        String host = "https://gjbsb.market.alicloudapi.com";
        String path = "/ocrservice/advanced";
        String method = "POST";
        String appcode = "fc2f84ecfaa84357810972b5ae7196c2";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/json; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        String bodys = "{\"img\":\"\",\"url\":\"https://reggie-hiya.oss-cn-beijing.aliyuncs.com/3.jpeg\",\"prob\":false,\"charInfo\":false,\"rotate\":false,\"table\":false}";


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "成功";
    }


    /**
     * 输出获取的Json返回值，并把它输出到控制台和文件
     *
     * @param content 获取的Json返回值
     */
    public static void output(String content) {
        System.out.println(content);
        File file = new File("/Users/hiya/Desktop/1.txt");  //这里选择输出文件的地址
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析Json
     *
     * @param string 获取response中的Json
     */
    public static void prase(String string) {
        JSONObject jsonObject = JSONObject.parseObject(string);
        JSONArray jsonArray = jsonObject.getJSONArray("prism_wordsInfo");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject newjsonObject = (JSONObject) jsonArray.get(i);
            output(newjsonObject.getString("word"));
        }
    }

    /**
     * 将文件读取并转化为Base64字符串
     *
     * @param fileName 文件名
     * @return Base64字符串
     * @throws Exception
     */
    public static String changeToBase64(String fileName) throws Exception {
        File file = new File(fileName);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        String base64Code = Objects.requireNonNull(Base64.encodeBase64(buffer)).toString();
        return base64Code;
    }

}
