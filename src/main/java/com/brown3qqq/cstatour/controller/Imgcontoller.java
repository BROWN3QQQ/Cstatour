package com.brown3qqq.cstatour.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class Imgcontoller {

    @RequestMapping(value = "/beimg", method = RequestMethod.POST)
    @ResponseBody
    public String fileupload(@RequestParam("file") MultipartFile file,@RequestParam("data") String data){
        if (!file.isEmpty()) {
            try {
                /*
                 * 这段代码执行完毕之后，图片上传到了工程的跟路径； 大家自己扩散下思维，如果我们想把图片上传到
                 * d:/files大家是否能实现呢？ 等等;
                 * 这里只是简单一个例子,请自行参考，融入到实际中可能需要大家自己做一些思考，比如： 1、文件路径； 2、文件名；
                 * 3、文件格式; 4、文件大小的限制;
                 */
                // E://CstatourIdeaProject/src/main/resources/static/imgsource/
                // /home/static/
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("/home/static/"+ data));
                System.out.println(file.getName());
                System.out.println(file.getSize());
                System.out.println(file.getBytes());
                System.out.println(file.getContentType());


                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }

            return "上传成功";

        } else {
            return "上传失败，因为文件是空的.";
        }

    }


}
