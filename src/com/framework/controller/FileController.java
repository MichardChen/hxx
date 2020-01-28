package com.framework.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author Chenwx
 * @date
 */
@RestController
@RequestMapping("/file")
public class FileController {

    /**
     * 下载文件
     * @param filePath
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/download/img")
    public void downloadFile(String filePath, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //文件所在目录路径
        System.out.println("文件路径：" + filePath);
        //得到该文件
        File file = new File(filePath);
        if(!file.exists()){
            System.out.println("Have no such file!");
//            return 0;//文件不存在就退出方法
        }
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            //设置Http响应头告诉浏览器下载这个附件,下载的文件名也是在这里设置的
            response.setHeader("Content-Disposition", "attachment;Filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
            OutputStream outputStream = response.getOutputStream();
            byte[] bytes = new byte[2048];
            int len = 0;
            while ((len = fileInputStream.read(bytes))>0){
                outputStream.write(bytes,0,len);
            }
            fileInputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
