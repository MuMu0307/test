package com.whut.work.goods.controller;

import com.whut.work.base.model.Page;
import com.whut.work.goods.model.Goods;
import com.whut.work.goods.service.IGoodsService;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping("/goods")
public class GoodsCtrl {

    @Autowired
    private IGoodsService goodsService;

    @RequestMapping(value="/addOneGoods",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addOneGoods(HttpServletRequest request,String name, String picture, Float unitPrice,
                                          String description, Integer ownerId, String ownerName){
        Map<String,Object> returnMap = new HashMap<String,Object>();

        try {
            Map<String,Object> goods = goodsService.addOneGoods(name,picture,unitPrice,description,
                    ownerId,ownerName);

            returnMap.put("list", goods);
            returnMap.put("success", true);
        } catch (Exception e) {
            returnMap.put("message", "异常：新增失败!");
            returnMap.put("success", false);
            e.printStackTrace();
        }
        return returnMap;
    }

    @RequestMapping(value="/addOneGoods_pic",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addOneGoods_pic(HttpServletRequest request, @RequestParam("file") MultipartFile file, ModelMap model){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        System.out.println(file.getName()+"开始00");
        /*System.out.println("start-------------");
        String savePath = request.getSession().getServletContext().getRealPath("/WEB-INF/goods-imgs/");//存储路径
        System.out.println(ServletFileUpload.isMultipartContent(request));
        System.out.println(savePath);
        System.out.println(request);
        try {
            if (ServletFileUpload.isMultipartContent(request)) {
                List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                System.out.println(items);
                for (FileItem item : items) {
                        System.out.println("helloosjfos");
                        String fileType = item.getName().substring(item.getName().lastIndexOf(".") + 1).toLowerCase();//文件类型
                        String fileName = new Date().getTime() + "." + fileType; //保存的文件名
                        String filePath = savePath + "\\" + fileName; //保存的文件路径
                        System.out.println(fileType+fileName+filePath+"---------");
                        System.out.println(savePath);

                        BufferedInputStream in = new BufferedInputStream(item.getInputStream());// 获得文件输入流
                        BufferedOutputStream out = new BufferedOutputStream(
                                new FileOutputStream(new File(filePath)));// 获得文件输出流
                        Streams.copy(in, out, true);// 开始把文件写到指定的上传文件夹
                        in.close();
                        out.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        /****************************************************************/
        try {

            //System.out.println("开始");


            String savePath = request.getSession().getServletContext().getRealPath("/goods-imgs/");//存储路径
            File myPath = new File(savePath);
            if ( !myPath.exists()){//若此目录不存在，则创建之// 这个东西只能一级建立文件夹，两级是无法建立的。。。。。
                myPath.mkdir();}
            //System.out.println(ServletFileUpload.isMultipartContent(request));
            System.out.println(savePath+"开始01");

            String fileName = file.getOriginalFilename();

            System.out.println(fileName+"开始02");

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

        }catch (Exception e){
            e.printStackTrace();
        }
        return returnMap;
    }

    @RequestMapping(value="/getGoodsPageList",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getGoodsPageList(HttpServletRequest request,int currentPage,int pageSize,
                                               String blurGoodsName, String sortRuleArray){
        Map<String,Object> returnMap = new HashMap<String,Object>();

        try {
            Page<Goods> goodsPage = goodsService.getGoodsPageList(currentPage, pageSize,blurGoodsName,sortRuleArray);

            returnMap.put("page", goodsPage);
            returnMap.put("success", true);
        } catch (Exception e) {
            returnMap.put("message", "异常：获取失败!");
            returnMap.put("success", false);
            e.printStackTrace();
        }
        return returnMap;
    }

    @RequestMapping(value="/getMyPubGoods",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getMyPubGoods(HttpServletRequest request,int currentPage,int pageSize,
                                               String blurGoodsName, Integer userId){
        Map<String,Object> returnMap = new HashMap<String,Object>();

        try {
            Page<Goods> goodsPage = goodsService.getMyPubGoods(currentPage, pageSize,blurGoodsName,userId);

            returnMap.put("page", goodsPage);
            returnMap.put("success", true);
        } catch (Exception e) {
            returnMap.put("message", "异常：获取失败!");
            returnMap.put("success", false);
            e.printStackTrace();
        }
        return returnMap;
    }

    @RequestMapping(value="/deleteOneGoods",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteOneGoods(HttpServletRequest request,Integer goodsId,Integer userId){
        Map<String,Object> returnMap = new HashMap<String,Object>();

        try {
            Map<String,Object> goodsResult = goodsService.deleteOneGoods(goodsId,userId);

            returnMap.put("value", goodsResult);
            returnMap.put("success", true);
        } catch (Exception e) {
            returnMap.put("message", "异常：操作失败!");
            returnMap.put("success", false);
            e.printStackTrace();
        }
        return returnMap;
    }

}
