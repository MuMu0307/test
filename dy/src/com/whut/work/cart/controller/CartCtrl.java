package com.whut.work.cart.controller;

import com.whut.work.base.model.Page;
import com.whut.work.cart.model.Cart;
import com.whut.work.cart.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartCtrl {

    @Autowired
    private ICartService cartService;

    @RequestMapping(value="/addToCart",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addToCart(HttpServletRequest request,Integer id,Integer ticketId,Integer ticketNums){
        Map<String,Object> returnMap = new HashMap<String,Object>();

        try {
            Map<String,Object> cartResult = cartService.addToCart(id,ticketId,ticketNums);

            returnMap.put("value", cartResult.get("value"));
            returnMap.put("success", true);
        } catch (Exception e) {
            returnMap.put("message", "异常：操作失败!");
            returnMap.put("success", false);
            e.printStackTrace();
        }
        return returnMap;
    }

    @RequestMapping(value="/getGoodsListOfCart",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getGoodsListOfCart(HttpServletRequest request,Integer currentPage,
                                                 Integer pageSize,Integer cartUserId){
        Map<String,Object> returnMap = new HashMap<String,Object>();

        try {
            Page<Cart> cartResult = cartService.getGoodsListOfCart(currentPage, pageSize, cartUserId);

            returnMap.put("page", cartResult);
            returnMap.put("success", true);
        } catch (Exception e) {
            returnMap.put("message", "异常：操作失败!");
            returnMap.put("success", false);
            e.printStackTrace();
        }
        return returnMap;
    }

    @RequestMapping(value="/removeGoodsFromCart",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> removeGoodsFromCart(HttpServletRequest request,Integer id,String cartGoodsArray){
        Map<String,Object> returnMap = new HashMap<String,Object>();

        try {
            Map<String,Object> cartResult = cartService.removeGoodsFromCart(id,cartGoodsArray);

            returnMap.put("value", cartResult);
            returnMap.put("success", true);
        } catch (Exception e) {
            returnMap.put("message", "异常：操作失败!");
            returnMap.put("success", false);
            e.printStackTrace();
        }
        return returnMap;
    }

    @RequestMapping(value="/editGoodsNums",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> editGoodsNums(HttpServletRequest request,Integer id,Integer cartId,Integer ticketNums){
        Map<String,Object> returnMap = new HashMap<String,Object>();

        try {
            Map<String,Object> cartResult = cartService.editGoodsNums(id,cartId,ticketNums);

            returnMap.put("value", cartResult);
            returnMap.put("success", true);
        } catch (Exception e) {
            returnMap.put("message", "异常：操作失败!");
            returnMap.put("success", false);
            e.printStackTrace();
        }
        return returnMap;
    }

    @RequestMapping(value="/getOneCartItem",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getOneCartItem(HttpServletRequest request,Integer userId,Integer cartId){
        Map<String,Object> returnMap = new HashMap<String,Object>();

        try {
            Map<String,Object> cartResult = cartService.getOneCartItem(userId,cartId);

            returnMap.put("value", cartResult);
            returnMap.put("success", true);
        } catch (Exception e) {
            returnMap.put("message", "异常：操作失败!");
            returnMap.put("success", false);
            e.printStackTrace();
        }
        return returnMap;
    }

}
