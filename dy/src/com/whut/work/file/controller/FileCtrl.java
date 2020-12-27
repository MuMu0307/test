package com.whut.work.file.controller;

import com.whut.work.file.model.File;
import com.whut.work.file.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FileCtrl {

    @Autowired
    private IFileService fileService;

    @RequestMapping(value="/getFileList",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getFileList(HttpServletRequest request){
        Map<String,Object> returnMap = new HashMap<String,Object>();

        try {
            List<File> fileList = fileService.getFileList();

            returnMap.put("list", fileList);
            returnMap.put("success", true);
        } catch (Exception e) {
            returnMap.put("message", "异常：获取失败!");
            returnMap.put("success", false);
            e.printStackTrace();
        }
        return returnMap;
    }

}
