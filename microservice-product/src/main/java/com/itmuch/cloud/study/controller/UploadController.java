package com.itmuch.cloud.study.controller;



import com.itmuch.cloud.study.utils.FtpClientEntity;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Controller
@RequestMapping("/test")
@Configuration
@PropertySource("classpath:ftp.properties")
public class UploadController {

    @Value("${FTP_URL}")
    private  String FTP_URL;

    @Value("${FTP_USERNAME}")
    private  String FTP_USERNAME;

    @Value("${FTP_PASSWORD}")
    private  String FTP_PASSWORD;

    @Value("${FTP_PATH}")
    private  String FTP_PATH;

    @Value("${FTP_PORT}")
    private  Integer FTP_PORT;

    @Value("${NGINX_URL}")
    private  String NGINX_URL;

    @RequestMapping("/upload")
    @ResponseBody
    public String saveImage(@RequestParam(value="file") MultipartFile file, Model model) throws Exception{

        if(file==null||file.isEmpty()){
            System.out.println("------------上传文件为空-----------");
            return "test";
        }

        //存在ftp图片服务器的路径
//        String path = "/home/ftpuser/www/images";
        String path = FTP_PATH;
        String filename=null;
        //String filename = file.getOriginalFilename(); //获得原始的文件名


        //生成uuid作为文件名称
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        //获得文件类型（可以判断如果不是图片，禁止上传）
        String contentType=file.getContentType();
        //获得文件后缀名
        String suffixName=contentType.substring(contentType.indexOf("/")+1);
        //得到 文件名
        filename=uuid+"."+suffixName;
        InputStream input=file.getInputStream();
        System.out.println("------------上传文件名-----------"+filename);
        FtpClientEntity a = new FtpClientEntity();
        FTPClient ftp = a.getConnectionFTP(FTP_URL, FTP_PORT, FTP_USERNAME, FTP_PASSWORD);
        a.uploadFile(ftp, path, filename, input);
        a.closeFTP(ftp);
        String fileSrc =NGINX_URL+filename;
        model.addAttribute("fileSrc",fileSrc);
        return fileSrc;

    }
}
