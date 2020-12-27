/**
 * Created by zouyao on 2017/3/6.
 */
var orderCtrl = m.controller("ordercontroller",function ($rootScope,$scope) {

    $rootScope.order_init = function () {
        $scope.prevPage = "上一页";
        $scope.nextPage = "下一页";
        $scope.currentPage = 1;
        $scope.totalPage = 1;
        $scope.orderType = "all";

        $scope.getOrders("all",1);
    };


    /**
     * 查看订单
     */
    $scope.getOrders = function (obj,cpage) {
        if(cpage == 1){
            this.currentPage = 1;
        }
        if(obj == "all"){
            $scope.orderType = "all";
        }else if(obj == "notDone"){
            $scope.orderType = "notDone";
        }else if(obj == "isDone"){
            $scope.orderType = "isDone";
        }
        if($scope.currentPage == 0){
            this.currentPage = 1;
        }else{
            this.currentPage = $scope.currentPage;
        }
        $.ajax({
            type:"POST",
            url:"/order/getOrders",
            data:{"currentPage":this.currentPage,"pageSize":5,"userId":$rootScope.idOfLoger,
                "orderType":obj},
            contentType:"application/x-www-form-urlencoded",
            dataType:"json",
            success:function(data){
                console.log(data);
                $scope.$apply(function(){
                    if(data.page.list.length == 0 && $scope.currentPage > 1){
                         $scope.currentPage = $scope.currentPage - 1;
                         $scope.getOrders($scope.orderType,-1);
                     }
                    $scope.goodsListOfOrder = new Array();
                    var obj = {};
                    for(var temp in data.page.list){
                        obj['id'] = data.page.list[temp].id;
                        obj['carts'] = data.page.list[temp].carts;
                        obj['user'] = data.page.list[temp].user;
                        obj['haveDone'] = data.page.list[temp].haveDone==false?"未支付":"已支付";
                        var datestr = new Date(parseInt(data.page.list[temp].createTime));
                        var temstr = datestr.getFullYear() + "年" + (parseInt(datestr.getMonth())+1) + "月" + datestr.getDate() + "日"
                            //+ datestr.getHours() + ":" + datestr.getMinutes() + ":" + datestr.getSeconds()
                            ;
                        obj['createTime'] = temstr;	//创建时间
                        $scope.goodsListOfOrder.push(obj);obj = {};
                    }
                    //分页相关更新
                    $scope.currentPage = data.page.current;
                    $scope.totalPage = data.page.total;
                    if($scope.currentPage == 1){
                        $("#btnid-prevpage-order").attr("disabled","disabled");
                    }else{
                        $("#btnid-prevpage-order").removeAttr("disabled");
                    }
                    if($scope.currentPage == $scope.totalPage){
                        $("#btnid-nextpage-order").attr("disabled","disabled");
                    }else{
                        $("#btnid-nextpage-order").removeAttr("disabled");
                    }
                });
            }
        });
    };
    /**
     * 分页操作
     * @param obj
     */
    $scope.makePagingList = function(obj){
        if(obj=="上一页"){
            if($scope.currentPage == 0){
                //nothing to do
            }else if($scope.currentPage == 1){
                alert("当前已经是第一页！");//其实并不会发生，因为disabled
            }else{
                $scope.currentPage = $scope.currentPage - 1;
                $scope.getOrders($scope.orderType,-1);
            }
        }else if(obj=="下一页"){
            if($scope.currentPage == 0){
                //nothing to do
            }else if($scope.currentPage == $scope.totalPage){
                alert("当前已经是最后一页！");//其实并不会发生，因为disabled
            }else{
                $scope.currentPage = $scope.currentPage + 1;
                $scope.getOrders($scope.orderType,-1);
            }
        }
    };
    /**
     * 支付订单
     * @param obj
     */
    $scope.completeOrder = function () {
        $.ajax({
            type:"POST",
            url:"/order/completeOrder",
            data:{"id":$rootScope.idOfLoger,"orderId":$scope.completeOrderModalTemp.id},
            contentType:"application/x-www-form-urlencoded",
            dataType:"json",
            success:function(data){
                console.log(data);
                $scope.$apply(function(){
                    $scope.justForModalInfomation = "成功支付订单!";
                    $("#modalid-toastInfo-order").modal("toggle");
                });
                $scope.getOrders($scope.orderType,-1);
            }
        });
    };
    $scope.completeOrderModal = function (obj) {
        $scope.completeOrderModalTemp = obj;
        $("#modalid-alipay-order").modal("toggle");
    };
    /**
     * 删除订单
     * @param obj
     */
    $scope.deleteOneOrder = function (obj) {
        //console.log(obj.orderId);
        $.ajax({
            type:"POST",
            url:"/order/deleteOneOrder",
            data:{"id":$rootScope.idOfLoger,"orderId":obj.id},
            contentType:"application/x-www-form-urlencoded",
            dataType:"json",
            success:function(data){
                console.log(data);
                $scope.$apply(function(){
                    $scope.justForModalInfomation = "成功删除订单!";
                    $("#modalid-toastInfo-order").modal("toggle");
                });
                console.log($scope.orderType);
                $scope.getOrders($scope.orderType,-1);
            }
        });
    };
    /**
     * 判断该用户是否对该景区进行过评分
     */
    $scope.ifHavePraise = function (item) {
        
    };
    /**
     * 景区评分
     */
    $scope.praiseScenicModal = function (item) {
        $scope.myPraise = 1;
        $.ajax({
            type:"POST",
            url:"/scenic/getOneScenicByTicketId",
            data:{"id":$rootScope.idOfLoger,"ticketId":item.id},
            contentType:"application/x-www-form-urlencoded",
            dataType:"json",
            success:function(data){
                console.log(data);
                $scope.$apply(function(){
                    $scope.praiseOneScenicTemp = new Array();
                    var obj = {};
                    obj['id'] = data.value.id;
                    obj['scenicName'] = data.value.scenicName;
                    obj['scenicDescription'] = data.value.description;
                    obj['praise'] = data.value.praise;
                    obj['ticket'] = data.value.ticket;
                    obj['user'] = data.value.user;
                    var datestr = new Date(parseInt(data.value.createTime));
                    var temstr = datestr.getFullYear() + "年" + (parseInt(datestr.getMonth())+1) + "月" + datestr.getDate() + "日"
                        //+ datestr.getHours() + ":" + datestr.getMinutes() + ":" + datestr.getSeconds()
                        ;
                    obj['createTime'] = temstr;	//创建时间
                    $scope.praiseOneScenicTemp.push(obj);
                    $scope.ifHavePraise($scope.praiseOneScenicTemp);
                    $("#modalid-praise-order").modal("toggle");
                });
            }
        });
    };
    $scope.praiseScenic = function () {
        $.ajax({
            type:"POST",
            url:"/praise/addOnePraise",
            data:{"userId":$rootScope.idOfLoger,"praise":$scope.myPraise,"scenicId":$scope.praiseOneScenicTemp[0].id},
            contentType:"application/x-www-form-urlencoded",
            dataType:"json",
            success:function(data){
                console.log(data);
                $scope.$apply(function(){
                    $scope.justForModalInfomation = "评分成功!欢迎下次再来游玩!";
                    $("#modalid-toastInfo-order").modal("toggle");
                });
            }
        });
    };
    /**
     * 景区评价
     */
    $scope.commentScenicModal = function (item) {
        $scope.myComment = "";
        $.ajax({
            type:"POST",
            url:"/scenic/getOneScenicByTicketId",
            data:{"id":$rootScope.idOfLoger,"ticketId":item.id},
            contentType:"application/x-www-form-urlencoded",
            dataType:"json",
            success:function(data){
                console.log(data);
                $scope.$apply(function(){
                    $scope.commentOneScenicTemp = new Array();
                    var obj = {};
                    obj['id'] = data.value.id;
                    obj['scenicName'] = data.value.scenicName;
                    obj['scenicDescription'] = data.value.description;
                    obj['praise'] = data.value.praise;
                    obj['ticket'] = data.value.ticket;
                    obj['user'] = data.value.user;
                    var datestr = new Date(parseInt(data.value.createTime));
                    var temstr = datestr.getFullYear() + "年" + (parseInt(datestr.getMonth())+1) + "月" + datestr.getDate() + "日"
                        //+ datestr.getHours() + ":" + datestr.getMinutes() + ":" + datestr.getSeconds()
                        ;
                    obj['createTime'] = temstr;	//创建时间
                    $scope.commentOneScenicTemp.push(obj);
                    $("#modalid-comment-order").modal("toggle");
                });
            }
        });
    };
    $scope.commentScenic = function () {
        $.ajax({
            type:"POST",
            url:"/comment/addOneComment",
            data:{"userId":$rootScope.idOfLoger,"text":$scope.myComment,"scenicId":$scope.commentOneScenicTemp[0].id},
            contentType:"application/x-www-form-urlencoded",
            dataType:"json",
            success:function(data){
                console.log(data);
                $scope.$apply(function(){
                    $scope.justForModalInfomation = "评论成功!欢迎下次再来游玩!";
                    $("#modalid-toastInfo-order").modal("toggle");
                });
            }
        });
    };

});