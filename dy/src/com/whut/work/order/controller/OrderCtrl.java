package com.whut.work.order.controller;

import com.whut.work.base.model.Page;
import com.whut.work.order.model.Order;
import com.whut.work.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderCtrl {

    @Autowired
    private IOrderService orderService;

    @RequestMapping(value="/addOneOrder",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addOneOrder(HttpServletRequest request,Integer id,String orderGoodsArray){
        Map<String,Object> returnMap = new HashMap<String,Object>();

        try {
            Map<String,Object> cartResult = orderService.addOneOrder(id,orderGoodsArray);

            returnMap.put("value", cartResult);
            returnMap.put("success", true);
        } catch (Exception e) {
            returnMap.put("message", "异常：操作失败!");
            returnMap.put("success", false);
            e.printStackTrace();
        }
        return returnMap;
    }

    @RequestMapping(value="/getOrders",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getOrders(HttpServletRequest request,int currentPage,int pageSize,
                                       Integer userId,String orderType){
        Map<String,Object> returnMap = new HashMap<String,Object>();

        try {
            Page<Order> orderResult = orderService.getOrders(currentPage,pageSize,userId,orderType);

            returnMap.put("page", orderResult);
            returnMap.put("success", true);
        } catch (Exception e) {
            returnMap.put("message", "异常：操作失败!");
            returnMap.put("success", false);
            e.printStackTrace();
        }
        return returnMap;
    }

    @RequestMapping(value="/deleteOneOrder",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteOneOrder(HttpServletRequest request,Integer id,String orderId){
        Map<String,Object> returnMap = new HashMap<String,Object>();

        try {
            Map<String,Object> orderResult = orderService.deleteOneOrder(id,orderId);

            returnMap.put("value", orderResult);
            returnMap.put("success", true);
        } catch (Exception e) {
            returnMap.put("message", "异常：操作失败!");
            returnMap.put("success", false);
            e.printStackTrace();
        }
        return returnMap;
    }

    @RequestMapping(value="/completeOrder",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> completeOrder(HttpServletRequest request,Integer id,String orderId){
        Map<String,Object> returnMap = new HashMap<String,Object>();

        try {
            Map<String,Object> orderResult = orderService.completeOrder(id,orderId);

            returnMap.put("value", orderResult);
            returnMap.put("success", true);
        } catch (Exception e) {
            returnMap.put("message", "异常：操作失败!");
            returnMap.put("success", false);
            e.printStackTrace();
        }
        return returnMap;
    }

}
