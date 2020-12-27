/**
 * Created by zouyao on 2017/3/6.
 */
var cartCtrl = m.controller("cartcontroller",function ($rootScope,$scope) {

    $rootScope.cart_init = function () {
        $scope.cartCheckBoxsArray = new Array();
        $scope.getGoodsListOfCart();
    };

    /**
     * 查看购物车
     */
    $scope.getGoodsListOfCart = function () {
        $.ajax({
            type:"POST",
            url:"/cart/getGoodsListOfCart",
            data:{"currentPage":1,"pageSize":100,"cartUserId":$rootScope.idOfLoger},
            contentType:"application/x-www-form-urlencoded",
            dataType:"json",
            success:function(data){
                console.log(data);
                $scope.$apply(function(){
                    /*if(data.page.list.length == 0){
                         $scope.currentPage = $scope.currentPage - 1;
                         $scope.getGoodsPageList();
                     }*/
                    $scope.goodsListOfCart = new Array();
                    var obj = {};
                    for(var temp in data.page.list){
                        obj['id'] = data.page.list[temp].id;
                        obj['ticketNums'] = data.page.list[temp].ticketNums;
                        obj['ticket'] = data.page.list[temp].ticket;
                        var datestr = new Date(parseInt(data.page.list[temp].createTime));
                        var temstr = datestr.getFullYear() + "年" + (parseInt(datestr.getMonth())+1) + "月" + datestr.getDate() + "日"
                            //+ datestr.getHours() + ":" + datestr.getMinutes() + ":" + datestr.getSeconds()
                            ;
                        obj['createTime'] = temstr;	//创建时间
                        $scope.goodsListOfCart.push(obj);obj = {};
                    }
                });
            }
        });
    };

    $scope.cartCheckBoxs = function ($event,item) {
        if($event.target.checked == true){
            $scope.cartCheckBoxsArray.push(item.id);
        }else{
            $scope.cartCheckBoxsArray.splice(
                $scope.cartCheckBoxsArray.indexOf(item.id),1
            );
        }
        console.log($scope.cartCheckBoxsArray);
    };

    /**
     * 从购物车删除
     */
    $scope.deleteFromCart = function () {
        if($scope.cartCheckBoxsArray.length == 0){
            $scope.justForModalInfomation = "未选中任何商品!";
            $("#modalid-toastInfo-cart").modal("toggle");
            return ;
        }
        this.cartGoodsArray = "";
        for(var index in $scope.cartCheckBoxsArray){
            this.cartGoodsArray += $scope.cartCheckBoxsArray[index].toString()+"-";
        }
        console.log(this.cartGoodsArray);
        $.ajax({
            type:"POST",
            url:"/cart/removeGoodsFromCart",
            data:{"id":$rootScope.idOfLoger,"cartGoodsArray":this.cartGoodsArray},
            contentType:"application/x-www-form-urlencoded",
            dataType:"json",
            success:function(data){
                console.log(data);
                $scope.$apply(function(){
                    $scope.justForModalInfomation = "已从购物车中删除指定商品!";
                    $("#modalid-toastInfo-cart").modal("toggle");
                });
                $scope.getGoodsListOfCart();
                $scope.cartCheckBoxsArray = new Array();
            }
        });
    };

    $scope.editTicketNums = function (item) {
        $.ajax({
            type:"POST",
            url:"/cart/getOneCartItem",
            data:{"userId":$rootScope.idOfLoger,"cartId":item.id},
            contentType:"application/x-www-form-urlencoded",
            dataType:"json",
            success:function(data){
                console.log(data);
                $scope.$apply(function(){
                    $scope.editTicketNumsTemp = data.value.value;
                    $("#modalid-editNums-cart").modal("toggle");
                });
            }
        });
    };
    /**
     * 修改购买数量
     */
    $scope.editGoodsNumsFunc = function () {
        $.ajax({
            type:"POST",
            url:"/cart/editGoodsNums",
            data:{"id":$rootScope.idOfLoger,"cartId":$scope.editTicketNumsTemp.id,"ticketNums":$scope.editTicketNumsTemp.ticketNums},
            contentType:"application/x-www-form-urlencoded",
            dataType:"json",
            success:function(data){
                console.log(data);
                $scope.$apply(function(){
                    $scope.justForModalInfomation = "修改成功!";
                    $("#modalid-toastInfo-cart").modal("toggle");
                });
                $scope.getGoodsListOfCart();
            }
        });
    };
    /**
     * 生成订单
     */
    $scope.addOneOrder = function () {
        this.orderGoodsArray = "";
        for(var index in $scope.cartCheckBoxsArray){
            this.orderGoodsArray += $scope.cartCheckBoxsArray[index].toString()+"-";
        }
        console.log(this.orderGoodsArray);
        $.ajax({
            type:"POST",
            url:"/order/addOneOrder",
            data:{"id":$scope.idOfLoger,"orderGoodsArray":this.orderGoodsArray},
            contentType:"application/x-www-form-urlencoded",
            dataType:"json",
            success:function(data){
                console.log(data);
                $scope.$apply(function(){
                    $scope.justForModalInfomation = "成功生成订单,快去支付吧!";
                    $("#modalid-toastInfo-cart").modal("toggle");
                });
                $scope.getGoodsListOfCart();
                $scope.cartCheckBoxsArray = new Array();
            }
        });
    };

});