package com.azir.controller;

import com.azir.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@RestController
@RequestMapping("/common")
public class CommonController {
    @Value("${regselftry.path}")
    private String basepath;

    @PostMapping("/upload")
    public R<String> upload(MultipartFile file)  {
        String uuid= String.valueOf(UUID.randomUUID());
        String filename= file.getContentType();
        String[] split = filename.split("/");
        String type=split[split.length-1];


        try {

            File file1=new File(basepath+uuid+"."+type);
            file.transferTo(file1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return R.success(uuid+"."+type);
    }

    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){

        try(
                InputStream fileInputStream=new FileInputStream(new File(basepath+ name));
                BufferedInputStream bufferedInputStream=new BufferedInputStream(fileInputStream);
                ServletOutputStream outputStream = response.getOutputStream();
        ){
            byte[]bytes=new byte[1024];
            int len=0;
            while( (len=bufferedInputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return;
    }
}
