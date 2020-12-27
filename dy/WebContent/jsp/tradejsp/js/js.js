/**
 * Created by zouyao on 2017/2/26.
 */
angular.module("mainapp",[])
    .constant('constRef',[["登录","注册","个人信息","管理用户","搜索","注销","修改密码"],//topOperation
        ["商品列表","购物车","订单","发布商品","我的发布"],//left nav bar
        ["查看详情","删除","新增角色","搜索","编辑","取消","提交","用户管理"],// role table
        ["系统消息","编辑","注销"],//
        ["搜索","新增文件","查看详情","下载","删除"]])//file table
    .controller("maincontroller", function($scope,constRef){
        $scope.constRef = constRef;
        $scope.goodsSortConst = "排序规则";
        $scope.prevPage = "上一页";
        $scope.nextPage = "下一页";

        $scope.GetQueryString = function(name){
            var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if(r!=null){return  decodeURI(r[2]);} return null;
        };
        $scope.htmlInit = function(){
            $scope.usernameOfLoger = $scope.GetQueryString("userName");
            $scope.idOfLoger = $scope.GetQueryString("userId");
            if(parseInt($scope.idOfLoger) < 1 || $scope.idOfLoger == null){
                //window.location.href = "/login.html";
            }
            console.log($scope.usernameOfLoger);
            console.log($scope.idOfLoger);
        };
        $scope.htmlInit();//init the userId(from loger)

        $scope.checkIfLogin = function () {
            if(parseInt($scope.idOfLoger) < 1 || $scope.idOfLoger == null){
                $scope.justForModalInfomation = "请先登录!";
                $("#modalid-toastInfo").modal("toggle");
                return false;
            }else {
                return true;
            }
        };

        $scope.topOperation = function(obj){
            if(obj == "登录"){
                window.location.href = "/login.html";
            }else if(obj == "注册"){
                window.location.href = "/signup.html";
            }else if(obj == "个人信息"){
                $scope.getLoger();
                $("#modalid-viewUserInfo").modal("toggle");
            }else if(obj == "管理用户"){
                $scope.getUserPageList();
                $("#modalid-manageUser").modal("toggle");
            }else if(obj == "注销"){
                $("#modalid-offconf").modal("toggle");
            }else if(obj == "修改密码"){
                $scope.getLoger();
                $scope.editPassWord = "";
                $scope.editPassWordNew = "";
                $("#modalid-modifyPWD").modal("toggle");
            }
        };

        $scope.getLoger = function(){
            $.ajax({
                type:"POST",
                url:"/login/getLoger",
                data:{"id":$scope.idOfLoger},
                contentType:"application/x-www-form-urlencoded",
                dataType:"json",
                success:function(data){
                    console.log(data);
                    $scope.$apply(function(){
                        $scope.editLogerArray = new Array();
                        var obj = {};
                        obj['id'] = data.value[0].id;
                        obj['username'] = data.value[0].username;
                        obj['email'] = data.value[0].email;
                        obj['tel'] = data.value[0].tel;
                        var datestr = new Date(parseInt(data.value[0].createTime));
                        var temstr = datestr.getFullYear() + "年" + (parseInt(datestr.getMonth())+1) + "月" + datestr.getDate() + "日"
                            //+ datestr.getHours() + ":" + datestr.getMinutes() + ":" + datestr.getSeconds()
                            ;
                        obj['createTime'] = temstr;	//创建时间
                        $scope.editLogerArray.push(obj);
                    });
                }
            });
        };

        $scope.editLoger = function(){
            $.ajax({
                type:"POST",
                url:"/login/editLoger",
                data:{"id":$scope.idOfLoger,"username":$scope.editLogerArray[0].username,
                    "tel":$scope.editLogerArray[0].tel,"email":$scope.editLogerArray[0].email},
                contentType:"application/x-www-form-urlencoded",
                dataType:"json",
                success:function(data){
                    console.log(data);
                    $scope.$apply(function(){
                        $scope.justForModalInfomation = "编辑个人信息成功!";
                        $("#modalid-toastInfo").modal("toggle");
                    });
                }
            });
        };

        $scope.modifyPWD = function(){
            $.ajax({
                type:"POST",
                url:"/login/modifyPWD",
                data:{"id":$scope.idOfLoger,"passWord":hex_md5($scope.editPassWord),
                "newPassWord":hex_md5($scope.editPassWordNew)},
                contentType:"application/x-www-form-urlencoded",
                dataType:"json",
                success:function(data){
                    console.log(data);
                    $scope.$apply(function(){
                        if(data.message == "旧密码不正确"){
                            $scope.justForModalInfomation = "旧密码不正确!";
                            $("#modalid-toastInfo").modal("toggle");
                        }else {
                            $scope.justForModalInfomation = "修改密码成功!请重新登录!";
                            $("#modalid-mpswoffconf").modal("toggle");
                        }
                    });
                }
            });
        };

        $scope.offFunction = function () {
            window.location.href = "/login.html";
        };

        $scope.freezeUser = function (item) {
            console.log(item);
            $.ajax({
                type:"POST",
                url:"/login/deleteOneUser",
                data:{"id":item.id},
                contentType:"application/x-www-form-urlencoded",
                dataType:"json",
                success:function(data){
                    console.log(data);
                    $scope.getUserPageList();
                }
            });
        };

        $scope.unFreezeUser = function (item) {
            console.log(item);
            $.ajax({
                type:"POST",
                url:"/login/deleteOneUserInverse",
                data:{"id":item.id},
                contentType:"application/x-www-form-urlencoded",
                dataType:"json",
                success:function(data){
                    console.log(data);
                    $scope.getUserPageList();
                }
            });
        };

        $scope.manageUserInfo = function (item) {
            $scope.manageUserInfoTemp = item;
            $("#modalid-manageUserInfo").modal("toggle");
        };

        $scope.getUserPageList = function(){
            /*if($scope.currentPage == 0){
                this.currentPage = 1;
            }else{
                this.currentPage = $scope.currentPage;
            }*/
            $.ajax({
                type:"POST",
                url:"/login/getUserPageList",
                data:{"currentPage":this.currentPage,"pageSize":100,"blurUserName":$scope.searchUserName},
                contentType:"application/x-www-form-urlencoded",
                dataType:"json",
                success:function(data){
                    console.log(data);
                    /**16-11-5 16:05
                     * angularjs  必须在$scope上下文中刷新数据才能更新视图
                     * 要用$scope.$apply(function(){
				 *	//更新数据
				 *	})
                     * 用$http服务的ajax获取可以直接修改数据，因为这个服务是在$scope上下文中的，但是jquery方法不是
                     */
                    $scope.$apply(function(){
                        if(data.page.list.length == 0){
                            $scope.currentPage = $scope.currentPage - 1;
                            $scope.getUserPageList();
                        }
                        $scope.userList = new Array();
                        var obj = {};
                        for(var temp in data.page.list){
                            obj['id'] = data.page.list[temp].id;
                            obj['username'] = data.page.list[temp].username;
                            obj['isDelete'] = data.page.list[temp].isDelete==true?"已被封号":"正常";
                            obj['email'] = data.page.list[temp].email;
                            obj['tel'] = data.page.list[temp].tel;
                            var datestr = new Date(parseInt(data.page.list[temp].createTime));
                            var temstr = datestr.getFullYear() + "年" + (parseInt(datestr.getMonth())+1) + "月" + datestr.getDate() + "日"
                                //+ datestr.getHours() + ":" + datestr.getMinutes() + ":" + datestr.getSeconds()
                                ;
                            obj['createTime'] = temstr;	//创建时间
                            $scope.userList.push(obj);obj = {};
                        }
                        //分页相关更新
                        /*$scope.currentPage = data.page.current;
                        $scope.totalPage = data.page.total;
                        if($scope.currentPage == 1){
                            for(var i1=0;i1<$(".btnid-prevpage").length;i1++){
                                $(".btnid-prevpage").eq(i1).attr("disabled","disabled");
                            }
                        }else{
                            for(var i2=0;i2<$(".btnid-prevpage").length;i2++){
                                $(".btnid-prevpage").eq(i2).removeAttr("disabled");
                            }
                        }
                        if($scope.currentPage == $scope.totalPage){
                            for(var i3=0;i3<$(".btnid-nextpage").length;i3++){
                                $(".btnid-nextpage").eq(i3).attr("disabled","disabled");
                            }
                        }else{
                            for(var i4=0;i4<$(".btnid-nextpage").length;i4++){
                                $(".btnid-nextpage").eq(i4).removeAttr("disabled");
                            }
                        }*/
                    });
                }
            });
        };

        $scope.rightDiv = function (obj) {
            $scope.currentPage = 0;
            $scope.totalPage = 0;
            if(obj == "商品列表"){
                $("#divid-goodslist").show();
                $("#divid-shopcart").hide();
                $("#divid-orderform").hide();
                $("#divid-publishgoods").hide();
                $("#divid-mypublish").hide();
                $("#liid-goodslist").attr("class","active");
                $("#liid-shopcart").removeClass("active");
                $("#liid-orderform").removeClass("active");
                $("#liid-publishgoods").removeClass("active");
                $("#liid-mypublish").removeClass("active");

                $scope.getGoodsPageList();
            }else if(obj == "购物车"){
                if($scope.checkIfLogin() == true){
                    $("#divid-goodslist").hide();
                    $("#divid-shopcart").show();
                    $("#divid-orderform").hide();
                    $("#divid-publishgoods").hide();
                    $("#divid-mypublish").hide();
                    $("#liid-goodslist").removeClass("active");
                    $("#liid-shopcart").attr("class","active");
                    $("#liid-orderform").removeClass("active");
                    $("#liid-publishgoods").removeClass("active");
                    $("#liid-mypublish").removeClass("active");

                    $scope.getgoodsListOfCart();
                }
            }else if(obj == "订单"){
                if($scope.checkIfLogin() == true){
                    $("#divid-goodslist").hide();
                    $("#divid-shopcart").hide();
                    $("#divid-orderform").show();
                    $("#divid-publishgoods").hide();
                    $("#divid-mypublish").hide();
                    $("#liid-goodslist").removeClass("active");
                    $("#liid-shopcart").removeClass("active");
                    $("#liid-orderform").attr("class","active");
                    $("#liid-publishgoods").removeClass("active");
                    $("#liid-mypublish").removeClass("active");

                    $scope.getOrder("all");
                }
            }else if(obj == "发布商品"){
                if($scope.checkIfLogin() == true){
                    $("#divid-goodslist").hide();
                    $("#divid-shopcart").hide();
                    $("#divid-orderform").hide();
                    $("#divid-publishgoods").show();
                    $("#divid-mypublish").hide();
                    $("#liid-goodslist").removeClass("active");
                    $("#liid-shopcart").removeClass("active");
                    $("#liid-orderform").removeClass("active");
                    $("#liid-publishgoods").attr("class","active");
                    $("#liid-mypublish").removeClass("active");

                    $scope.addGoods_name = "";
                    $scope.addGoods_picture = "";
                    $scope.addGoods_unitPrice = 1.0;
                    $scope.addGoods_description= "";
                    $scope.addGoods_ownerId= $scope.idOfLoger;
                    $scope.addGoods_ownerName= $scope.usernameOfLoger;
                    $scope.ifshowpic = false;
                }
            }else if(obj == "我的发布"){
                if($scope.checkIfLogin() == true){
                    $("#divid-goodslist").hide();
                    $("#divid-shopcart").hide();
                    $("#divid-orderform").hide();
                    $("#divid-publishgoods").hide();
                    $("#divid-mypublish").show();
                    $("#liid-goodslist").removeClass("active");
                    $("#liid-shopcart").removeClass("active");
                    $("#liid-orderform").removeClass("active");
                    $("#liid-publishgoods").removeClass("class","active");
                    $("#liid-mypublish").attr("active");

                    $scope.getMyPubGoods();
                }
            }
        };

        $scope.tradeProtocol = function () {

        };

        /**
         * 搜索商品的排序规则
         * @param $event
         */
        $scope.sortRuleArray = "";
        $scope.goodsSortConstFunc = function ($event) {
            //console.log($event.target.innerHTML);
            var temp = $event.target.innerHTML;
            if(temp == "按价格从低到高"){
                $scope.sortRuleArray = "0p0100";
            }else if(temp == "按价格从高到低"){
                $scope.sortRuleArray = "0p1000";
            }else if(temp == "按时间由近及远"){
                $scope.sortRuleArray = "00tcr0";
            }else if(temp == "按时间由远及近"){
                $scope.sortRuleArray = "00trc0";
            }else if(temp == "按销量从多到少"){
                $scope.sortRuleArray = "000sms";
            }else if(temp == "按销量从少到多"){
                $scope.sortRuleArray = "000ssm";
            }else if(temp == "全部"){
                $scope.sortRuleArray = "000000";
            }
            $scope.goodsSortConst = temp;
            $scope.getGoodsPageList();
        };

        $scope.addToCartModal = function (obj) {
            if($scope.checkIfLogin() == true) {
                $scope.addToCartTemp = obj;
                $scope.addToCart_goodsNums = 1;
                $("#modalid-addToCart").modal("toggle");
            }
        };

        /**
         * 加入购物车
         */
        $scope.addToCart = function () {
            $.ajax({
                type:"POST",
                url:"/cart/addToCart",
                data:{"cartUserId":$scope.idOfLoger,"picture":$scope.addToCartTemp.picture,"description":$scope.addToCartTemp.description,
                    "goodsId":$scope.addToCartTemp.id, "goodsName":$scope.addToCartTemp.name,
                    "goodsUnitPrice":$scope.addToCartTemp.unitPrice, "goodsNums":$scope.addToCart_goodsNums,
                    "goodsOwnerId":$scope.addToCartTemp.ownerId, "goodsOwnerName":$scope.addToCartTemp.ownerName},
                contentType:"application/x-www-form-urlencoded",
                dataType:"json",
                success:function(data){
                    console.log(data);
                    $scope.$apply(function(){
                        $scope.justForModalInfomation = "添加进购物车成功!";
                        $("#modalid-toastInfo").modal("toggle");
                    });
                }
            });
        };

        $scope.cartCheckBoxsArray = new Array();
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
            this.cartGoodsArray = "";
            for(var index in $scope.cartCheckBoxsArray){
                this.cartGoodsArray += $scope.cartCheckBoxsArray[index].toString()+"-";
            }
            console.log(this.cartGoodsArray);
            $.ajax({
                type:"POST",
                url:"/cart/removeGoodsFromCart",
                data:{"id":$scope.idOfLoger,"cartGoodsArray":this.cartGoodsArray},
                contentType:"application/x-www-form-urlencoded",
                dataType:"json",
                success:function(data){
                    console.log(data);
                    $scope.$apply(function(){
                        $scope.justForModalInfomation = "已从购物车中删除指定商品!";
                        $("#modalid-toastInfo").modal("toggle");
                    });
                    $scope.getgoodsListOfCart();
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
                        $("#modalid-toastInfo").modal("toggle");
                    });
                    $scope.getgoodsListOfCart();
                }
            });
        };

        /**
         * 查看购物车
         */
        $scope.getgoodsListOfCart = function () {
            $.ajax({
                type:"POST",
                url:"/cart/getgoodsListOfCart",
                data:{"currentPage":this.currentPage,"pageSize":100,"cartUserId":$scope.idOfLoger},
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
                            obj['cartUserId'] = data.page.list[temp].cartUserId;
                            obj['goodsId'] = data.page.list[temp].goodsId;
                            obj['goodsName'] = data.page.list[temp].goodsName;
                            obj['goodsUnitPrice'] = data.page.list[temp].goodsUnitPrice;
                            obj['goodsNums'] = data.page.list[temp].goodsNums;
                            obj['goodsOwnerId'] = data.page.list[temp].goodsOwnerId;
                            obj['goodsOwnerName'] = data.page.list[temp].goodsOwnerName;
                            obj['picture'] = data.page.list[temp].picture;
                            obj['description'] = data.page.list[temp].description;
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

        /**
         * 支付订单
         * @param obj
         */
        $scope.completeOrder = function (obj) {
            $.ajax({
                type:"POST",
                url:"/order/completeOrder",
                data:{"id":$scope.idOfLoger,"orderId":obj.orderId},
                contentType:"application/x-www-form-urlencoded",
                dataType:"json",
                success:function(data){
                    console.log(data);
                    $scope.$apply(function(){
                        $scope.justForModalInfomation = "成功支付订单!";
                        $("#modalid-toastInfo").modal("toggle");
                    });
                    $scope.getOrder("all");
                }
            });
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
                data:{"id":$scope.idOfLoger,"orderId":obj.orderId},
                contentType:"application/x-www-form-urlencoded",
                dataType:"json",
                success:function(data){
                    console.log(data);
                    $scope.$apply(function(){
                        $scope.justForModalInfomation = "成功删除订单!";
                        $("#modalid-toastInfo").modal("toggle");
                    });
                    $scope.getOrder("all");
                }
            });
        };

        /**
         * 查看订单
         */
        $scope.getOrder = function (obj) {
            $.ajax({
                type:"POST",
                url:"/order/getOrder",
                data:{"currentPage":this.currentPage,"pageSize":100,"userId":$scope.idOfLoger,
                    "orderType":obj},
                contentType:"application/x-www-form-urlencoded",
                dataType:"json",
                success:function(data){
                    console.log(data);
                    $scope.$apply(function(){
                        /*if(data.page.list.length == 0){
                         $scope.currentPage = $scope.currentPage - 1;
                         $scope.getGoodsPageList();
                         }*/
                        var arr = new Array();         //先声明一维
                        for(var arri=0;arri<10;arri++){          //一维长度为50
                            arr[arri]=new Array();    //在声明二维
                            /*for(var arrj=0;arrj<1;arrj++){      //二维长度为0 -> push增长
                             arr[arri][arrj]=null;
                             }*/
                        }
                        $scope.goodsListOfOrder = arr; ;//二维数组存放有区别订单
                        $scope.goodsListOfOrderTemp = new Array();
                        var obj = {};
                        for(var temp in data.page.list){
                            obj['id'] = data.page.list[temp].id;
                            obj['orderId'] = data.page.list[temp].orderId;
                            obj['userId'] = data.page.list[temp].userId;
                            obj['goodsId'] = data.page.list[temp].goodsId;
                            obj['goodsName'] = data.page.list[temp].goodsName;
                            obj['goodsNums'] = data.page.list[temp].goodsNums;
                            obj['goodsUnitPrice'] = data.page.list[temp].goodsUnitPrice;
                            obj['goodsOwnerId'] = data.page.list[temp].goodsOwnerId;
                            obj['goodsOwnerName'] = data.page.list[temp].goodsOwnerName;
                            obj['picture'] = data.page.list[temp].picture;
                            obj['description'] = data.page.list[temp].description;
                            obj['haveDone'] = data.page.list[temp].haveDone==false?"未支付":"已支付";
                            var datestr = new Date(parseInt(data.page.list[temp].createTime));
                            var temstr = datestr.getFullYear() + "年" + (parseInt(datestr.getMonth())+1) + "月" + datestr.getDate() + "日"
                                //+ datestr.getHours() + ":" + datestr.getMinutes() + ":" + datestr.getSeconds()
                                ;
                            obj['createTime'] = temstr;	//创建时间
                            $scope.goodsListOfOrderTemp.push(obj);obj = {};
                        }
                        console.log($scope.goodsListOfOrderTemp);
                        //二维数组存放有区别订单
                        for(var i=0;i<$scope.goodsListOfOrderTemp.length;i++){
                            if($scope.goodsListOfOrderTemp[i].flag != "flag"){
                                $scope.goodsListOfOrder[i].push($scope.goodsListOfOrderTemp[i]);
                            }
                            for(var j=i+1;j<$scope.goodsListOfOrderTemp.length;j++){
                                if($scope.goodsListOfOrderTemp[j].flag != "flag" &&
                                    $scope.goodsListOfOrderTemp[i].flag != "flag"){
                                    if($scope.goodsListOfOrderTemp[j].orderId ==
                                        $scope.goodsListOfOrderTemp[i].orderId ){
                                        $scope.goodsListOfOrder[i].push($scope.goodsListOfOrderTemp[j]);
                                        $scope.goodsListOfOrderTemp[j].flag = "flag";
                                    }
                                }
                            }
                        }
                        //删除长度为0的空行
                        //直接用ng-if即可
                        /*var _temp_index = new Array();
                        for(var _temp in $scope.goodsListOfOrder){
                            if($scope.goodsListOfOrder[_temp].length == 0){
                                //$scope.goodsListOfOrder.splice(_temp,1);
                                _temp_index.push(_temp);
                                //console.log($scope.goodsListOfOrder);
                                console.log(_temp_index);
                            }
                        }
                        for(var _temp_i=0;_temp_i<_temp_index.length;_temp_i++){

                        }*/
                        //console.log($scope.goodsListOfOrder);
                    });
                }
            });
        };

        /**
         * 新增商品
         * @type {string}
         */
        $scope.addOneGoods = function () {
            /*String name, String picture, Integer unitPrice,
                String description, Integer salesCount,
                Integer ownerId, String ownerName*/
            if($scope.addGoods_picture == ""){
                alert("请上传图片!");
            }else{
                $.ajax({
                    type:"POST",
                    url:"/goods/addOneGoods",
                    data:{"name":$scope.addGoods_name,"picture":$scope.addGoods_picture,"unitPrice":$scope.addGoods_unitPrice,
                        "description":$scope.addGoods_description, "ownerId":$scope.addGoods_ownerId,"ownerName":$scope.addGoods_ownerName},
                    contentType:"application/x-www-form-urlencoded",
                    dataType:"json",
                    success:function(data){
                        console.log(data);
                        $scope.$apply(function(){
                            $scope.justForModalInfomation = "发布商品成功!";
                            $("#modalid-toastInfo").modal("toggle");
                        });
                    }
                });
            }
        };

        /**
         * 上传图片
         * @type {boolean}
         */
        $scope.ifshowpic = false;
        $scope.doUpload = function () {
            var formData = new FormData($( "#uploadForm" )[0]);
            var i = formData.entries();

            console.log(i.next()); // {done:false, value:["k1", "v1"]}
            console.log(i.next()); // {done:false, value:["k1", "v1"]}
            $.ajax({
                url: '/goods/addOneGoods_pic' ,
                type: 'POST',
                data: formData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (returndata) {
                    console.log(returndata);
                    console.log(pathshowpic);
                    $("#imgid-picPreview").attr("src",pathshowpic);
                    $scope.addGoods_picture = pathshowpic;
                    $scope.ifshowpic = true;
                },
                error: function (returndata) {
                    console.log(returndata);
                    $scope.ifshowpic = false;
                }
            });
        };

        /**
         * 删除(下架)我发布的商品
         * @param obj
         */
        $scope.deleteOneGoods = function (obj) {
            $.ajax({
                type:"POST",
                url:"/goods/deleteOneGoods",
                data:{"goodsId":obj.id, "userId":$scope.idOfLoger},
                contentType:"application/x-www-form-urlencoded",
                dataType:"json",
                success:function(data){
                    console.log(data);
                    $scope.$apply(function(){
                        $scope.justForModalInfomation = "已成功下架指定商品!";
                        $("#modalid-toastInfo").modal("toggle");
                    });
                    $scope.getMyPubGoods();
                }
            });
        };

        /**
         * 获取我发布的商品
         */
        $scope.getMyPubGoods = function () {
            $.ajax({
                type:"POST",
                url:"/goods/getMyPubGoods",
                data:{"currentPage":this.currentPage,"pageSize":100,"blurGoodsName":$scope.searchGoodsName,
                    "userId":$scope.idOfLoger},
                contentType:"application/x-www-form-urlencoded",
                dataType:"json",
                success:function(data){
                    console.log(data);
                    $scope.$apply(function(){
                        $scope.myPubGoodsList = new Array();
                        var obj = {};
                        for(var temp in data.page.list){
                            obj['id'] = data.page.list[temp].id;
                            obj['name'] = data.page.list[temp].name;
                            obj['description'] = data.page.list[temp].description;
                            obj['unitPrice'] = data.page.list[temp].unitPrice;
                            obj['picture'] = data.page.list[temp].picture;
                            obj['salesCount'] = data.page.list[temp].salesCount;
                            obj['ownerId'] = data.page.list[temp].ownerId;
                            obj['ownerName'] = data.page.list[temp].ownerName;
                            var datestr = new Date(parseInt(data.page.list[temp].createTime));
                            var temstr = datestr.getFullYear() + "年" + (parseInt(datestr.getMonth())+1) + "月" + datestr.getDate() + "日"
                                //+ datestr.getHours() + ":" + datestr.getMinutes() + ":" + datestr.getSeconds()
                                ;
                            obj['createTime'] = temstr;	//创建时间
                            $scope.myPubGoodsList.push(obj);obj = {};
                        }
                    });
                }
            });
        };

        $scope.currentPage = 0;
        $scope.totalPage = 0;
        $scope.searchGoodsNameUrlSufix = "";
        $scope.searchGoodsName = "";
        /**
         * 获取商品分页列表
         */
        $scope.getGoodsPageList = function(){
            if($scope.currentPage == 0){
                this.currentPage = 1;
            }else{
                this.currentPage = $scope.currentPage;
            }
            $.ajax({
                type:"POST",
                url:"/goods/getGoodsPageList",
                data:{"currentPage":this.currentPage,"pageSize":8,"blurGoodsName":$scope.searchGoodsName,
                "sortRuleArray":$scope.sortRuleArray},
                contentType:"application/x-www-form-urlencoded",
                dataType:"json",
                success:function(data){
                    console.log(data);
                    /**16-11-5 16:05
                     * angularjs  必须在$scope上下文中刷新数据才能更新视图
                     * 要用$scope.$apply(function(){
				 *	//更新数据
				 *	})
                     * 用$http服务的ajax获取可以直接修改数据，因为这个服务是在$scope上下文中的，但是jquery方法不是
                     */
                    $scope.$apply(function(){
                        if(data.page.list.length == 0 && $scope.currentPage != 1){
                            $scope.currentPage = $scope.currentPage - 1;
                            $scope.getGoodsPageList();
                        }
                        $scope.goodsList = new Array();
                        var obj = {};
                        for(var temp in data.page.list){
                            obj['id'] = data.page.list[temp].id;
                            obj['name'] = data.page.list[temp].name;
                            obj['description'] = data.page.list[temp].description;
                            obj['unitPrice'] = data.page.list[temp].unitPrice;
                            obj['picture'] = data.page.list[temp].picture;
                            obj['salesCount'] = data.page.list[temp].salesCount;
                            obj['ownerId'] = data.page.list[temp].ownerId;
                            obj['ownerName'] = data.page.list[temp].ownerName;
                            var datestr = new Date(parseInt(data.page.list[temp].createTime));
                            var temstr = datestr.getFullYear() + "年" + (parseInt(datestr.getMonth())+1) + "月" + datestr.getDate() + "日"
                                //+ datestr.getHours() + ":" + datestr.getMinutes() + ":" + datestr.getSeconds()
                                ;
                            obj['createTime'] = temstr;	//创建时间
                            $scope.goodsList.push(obj);obj = {};
                        }
                        //分页相关更新
                        $scope.currentPage = data.page.current;
                        $scope.totalPage = data.page.total;
                        if($scope.currentPage == 1){
                            for(var i1=0;i1<$(".btnid-prevpage").length;i1++){
                                $(".btnid-prevpage").eq(i1).attr("disabled","disabled");
                            }
                        }else{
                            for(var i2=0;i2<$(".btnid-prevpage").length;i2++){
                                $(".btnid-prevpage").eq(i2).removeAttr("disabled");
                            }
                        }
                        if($scope.currentPage == $scope.totalPage){
                            for(var i3=0;i3<$(".btnid-nextpage").length;i3++){
                                $(".btnid-nextpage").eq(i3).attr("disabled","disabled");
                            }
                        }else{
                            for(var i4=0;i4<$(".btnid-nextpage").length;i4++){
                                $(".btnid-nextpage").eq(i4).removeAttr("disabled");
                            }
                        }
                    });
                }
            });
        };
        $scope.getGoodsPageList();//初步加载

        /**
         * 分页操作
         * @param obj
         */
        /*$scope.beforePaging = function(obj){//different tables
            var  activeId = $(".active").attr("id");
            if(activeId == "liid-usermanage"){
                $scope.makePagingList(obj,"用户管理");
            }else if(activeId == "liid-rolemanage"){
                $scope.makePagingList(obj,"角色管理");
            }
        };*/
        $scope.makePagingList = function(obj){
            if(obj=="上一页"){
                if($scope.currentPage == 0){
                    //nothing to do
                }else if($scope.currentPage == 1){
                    alert("当前已经是第一页！");//其实并不会发生，因为disabled
                }else{
                    $scope.currentPage = $scope.currentPage - 1;
                    $scope.getGoodsPageList();
                }
            }else if(obj=="下一页"){
                if($scope.currentPage == 0){
                    //nothing to do
                }else if($scope.currentPage == $scope.totalPage){
                    alert("当前已经是最后一页！");//其实并不会发生，因为disabled
                }else{
                    $scope.currentPage = $scope.currentPage + 1;
                    $scope.getGoodsPageList();
                }
            }
        };

        /**
         * 查看详情
         * @param obj
         */
        $scope.infoGoodsModal = function (obj) {
            console.log(obj);
            $scope.infoGoodsModalTemp = obj;
            $("#modalid-infoGoods").modal("toggle");
        };

});

//plain js

var pathshowpic = "";

var showPreview = function (obj) {
    var Num="";
    for(var i=0;i<10;i++)
    {
        Num+=Math.floor(Math.random()*10);
    }
    pathshowpic = //$("#imgid-picPreview").attr("title")+
        "/goods-imgs/"+
        //Num+
        obj.value.substring(obj.value.lastIndexOf("\\")+1);
    //console.log($("#imgid-picPreview").attr("title"));
};

var toggleModal = function () {
    $("#modalid-infoGoods").modal("toggle");
}