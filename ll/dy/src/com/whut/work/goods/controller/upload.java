package com.whut.work.goods.controller;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/fileTest")
@Controller
public class upload {

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
    public Map upload(HttpServletRequest request, @RequestParam("file") MultipartFile file, ModelMap model)  {
        Map<String, Object> returnMap = new HashMap<String, Object>();
	    try {

            System.out.println("开始");


            String savePath = request.getSession().getServletContext().getRealPath("/WEB-INF/goods-imgs/");//存储路径
            System.out.println(ServletFileUpload.isMultipartContent(request));
            System.out.println(savePath);
            System.out.println(request);


            String fileName = file.getOriginalFilename();

            System.out.println(fileName);

            BufferedInputStream bufrs = new BufferedInputStream(file.getInputStream());
            BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(new File(savePath,fileName)));// 获得文件输出流
            byte[] by = new byte[1024];
            int ch = 0;
            while ((ch = bufrs.read(by)) != -1) {
                out.write(by);


            }
            out.flush();
            out.close();
            bufrs.close();

            returnMap.put("success", "sucess");
            ;
        }catch (Exception e){
		    e.printStackTrace();
            return  returnMap;
        }
        return  returnMap;
    }
	
	
	
}
