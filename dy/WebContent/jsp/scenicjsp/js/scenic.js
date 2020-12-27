/**
 * Created by zouyao on 2017/3/6.
 */
var scenicCtrl = m.controller("sceniccontroller",function ($rootScope,$scope) {

    $scope.travelLink = "http://www.shanxidiy.com/shanxi/";

    /**
     *景区列表初始化
     */
    $rootScope.se_init = function () {
        $scope.scenicSortConst = "排序规则";
        $scope.prevPage = "上一页";
        $scope.nextPage = "下一页";
        $scope.currentPage = 1;
        $scope.totalPage = 1;
        $scope.constRef = [["查看详情","查看评价","门票预定","编辑","删除"]];
        $scope.blurScenicName = "";
        $scope.orderType = "";

        $scope.getScenicPageList();
    };
    /**
     * 获取景区分页列表
     */
    $scope.getScenicPageList = function(){
        if($scope.currentPage == 0){
            this.currentPage = 1;
         }else{
            this.currentPage = $scope.currentPage;
         }
        $.ajax({
            type:"POST",
            url:"/scenic/getScenicPageList",
            data:{"currentPage":this.currentPage,"pageSize":5,"blurScenicName":$scope.blurScenicName,"orderType":$scope.orderType},
            contentType:"application/x-www-form-urlencoded",
            dataType:"json",
            success:function(data){
                console.log(data);
                $scope.$apply(function(){
                    if(data.page.list.length == 0 && $scope.currentPage > 1){
                        $scope.currentPage = $scope.currentPage - 1;
                        $scope.getScenicPageList();
                    }
                    $scope.scenicList = new Array();
                    var obj = {};
                    for(var temp in data.page.list){
                        obj['id'] = data.page.list[temp].id;
                        obj['scenicName'] = data.page.list[temp].scenicName;
                        obj['scenicDescription'] = data.page.list[temp].description;
                        obj['praise'] = data.page.list[temp].praise;
                        obj['ticket'] = data.page.list[temp].ticket;
                        obj['user'] = data.page.list[temp].user;
                        var datestr = new Date(parseInt(data.page.list[temp].createTime));
                        var temstr = datestr.getFullYear() + "年" + (parseInt(datestr.getMonth())+1) + "月" + datestr.getDate() + "日"
                            //+ datestr.getHours() + ":" + datestr.getMinutes() + ":" + datestr.getSeconds()
                            ;
                        obj['createTime'] = temstr;	//创建时间
                        $scope.scenicList.push(obj);obj = {};
                    }
                    //分页相关更新
                    $scope.currentPage = data.page.current;
                    $scope.totalPage = data.page.total;
                    if($scope.currentPage == 1){
                        $("#btnid-prevpage-scenic").attr("disabled","disabled");
                    }else{
                        $("#btnid-prevpage-scenic").removeAttr("disabled");
                    }
                    if($scope.currentPage == $scope.totalPage){
                        $("#btnid-nextpage-scenic").attr("disabled","disabled");
                    }else{
                        $("#btnid-nextpage-scenic").removeAttr("disabled");
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
                $scope.getScenicPageList();
            }
        }else if(obj=="下一页"){
            if($scope.currentPage == 0){
                //nothing to do
            }else if($scope.currentPage == $scope.totalPage){
                alert("当前已经是最后一页！");//其实并不会发生，因为disabled
            }else{
                $scope.currentPage = $scope.currentPage + 1;
                $scope.getScenicPageList();
            }
        }
    };
    /**
     * 排序规则
     * @param $event
     */
    $scope.scenicSortConstFunc = function ($event) {
        var obj = $event.target.innerHTML;
        $scope.currentPage = 1;
        if(obj == "全部"){
            $scope.orderType = "100000";
        }else if(obj == "按评分从低到高"){
            $scope.orderType = "010000";
        }else if(obj == "按评分从高到低"){
            $scope.orderType = "100000";
        }
        $scope.scenicSortConst = obj;
        $scope.getScenicPageList();
    };
    /**
     * 表格操作（模态框）
     */
    $scope.actionOnScenic = function(item,obj){
        if(obj == "查看详情"){
            $scope.scenicInfoTemp = item;
            console.log($scope.scenicInfoTemp);
            $("#modalid-scenicInfo").modal("toggle");
        }else if(obj == "查看评价"){
            $scope.getCommentsOfScenic(item);
            $("#modalid-comment-se").modal("toggle");
        }else if(obj == "门票预定"){
            if($scope.checkIfLogin()) {
                $scope.addToCartTemp = item;
                $scope.addToCart_goodsNums = 1;
                $("#modalid-addToCart-se").modal("toggle");
            }
        }else if(obj == "编辑"){
            $scope.editScenicInfoTemp = item;
            //console.log(item);
            $scope.getOneScenic(item);
            $scope.blurUserName = "";
            $scope.getUserPageList();
            $scope.editScenicUser = "";
            $scope.ifshowpic = false;
            $scope.editTicketPicture = "";
            $("#modalid-editScenic").modal("toggle");
        }else if(obj == "删除"){
            $scope.delScenicInfoTemp = item;
            $("#modalid-delconf-se").modal("toggle");
        }

    };
    /**
     * 删除景区（不推荐）
     */
    $scope.deleteOneScenic = function (item) {
        $.ajax({
            type:"POST",
            url:"/scenic/deleteOneScenic",
            data:{"userId":$scope.idOfLoger,"scenicId":item.id},
            contentType:"application/x-www-form-urlencoded",
            dataType:"json",
            success:function(data){
                console.log(data);
                $scope.$apply(function(){
                    $scope.justForModalInfomation = "删除景区成功!";
                    $("#modalid-toastInfo-se").modal("toggle");
                    $scope.getScenicPageList();
                });
            }
        });
    };
    /**
     * 获取一个景区的信息
     */
    $scope.getOneScenic = function (item) {
        $.ajax({
            type:"POST",
            url:"/scenic/getOneScenic",
            data:{"id":$rootScope.idOfLoger,"scenicId":item.id},
            contentType:"application/x-www-form-urlencoded",
            dataType:"json",
            success:function(data){
                console.log(data);
                $scope.$apply(function(){
                    $scope.editOneScenicTemp = new Array();
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
                    $scope.editOneScenicTemp.push(obj);
                });
            }
        });
    };
    /**
     * 获取用户列表
     */
    $scope.getUserPageList = function(){
        /*if($scope.currentPage == 0){
         this.currentPage = 1;
         }else{
         this.currentPage = $scope.currentPage;
         }*/
        $.ajax({
            type:"POST",
            url:"/login/getUserPageList",
            data:{"currentPage":1,"pageSize":500,"blurUserName":$scope.blurUserName},
            contentType:"application/x-www-form-urlencoded",
            dataType:"json",
            success:function(data){
                console.log(data);
                $scope.$apply(function(){
                    /*if(data.page.list.length == 0 && $scope.currentPage > 1){
                     $scope.currentPage = $scope.currentPage - 1;
                     $scope.getUserPageList();
                     }*/
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
                     $("#btnid-prevpage-userman").attr("disabled","disabled");
                     }else{
                     $("#btnid-prevpage-userman").removeAttr("disabled");
                     }
                     if($scope.currentPage == $scope.totalPage){
                     $("#btnid-nextpage-userman").attr("disabled","disabled");
                     }else{
                     $("#btnid-nextpage-userman").removeAttr("disabled");
                     }*/
                });
            }
        });
    };
    /**
     * 选择景区负责人
     */
    $scope.chooseUserOK = function (obj) {
        $scope.editScenicUser = obj;
    };
    /**
     * 编辑景区
     * @type {string}
     */
    /**
     * String scenicName, String scenicDescription,
     String ticketName, String picture, Float unitPrice,
     String ticketDescription, Integer userId, Integer scenicId
     */
    $scope.editOneScenic = function () {
        /*String name, String picture, Integer unitPrice,
         String description, Integer salesCount,
         Integer ownerId, String ownerName*/
        if($scope.editScenicUser.id <= 0 || $scope.editScenicUser == null || $scope.editScenicUser == ""){
            this.editUserId = 0;
        }else {
            this.editUserId = $scope.editScenicUser.id;
        }

        /*if($scope.editTicketPicture == ""){
            //alert("请上传图片!");
        }else{*/
            $.ajax({
                type:"POST",
                url:"/scenic/editOneScenic",
                data:{"scenicName":$scope.editOneScenicTemp[0].scenicName,"scenicDescription":$scope.editOneScenicTemp[0].scenicDescription,"picture":$scope.editTicketPicture,
                    "ticketName":$scope.editOneScenicTemp[0].ticket.ticketName, "unitPrice":$scope.editOneScenicTemp[0].ticket.unitPrice,"ticketDescription":$scope.editOneScenicTemp[0].ticket.description,
                    "userId":this.editUserId,"scenicId":$scope.editScenicInfoTemp.id},
                contentType:"application/x-www-form-urlencoded",
                dataType:"json",
                success:function(data){
                    console.log(data);
                    $scope.$apply(function(){
                        $scope.justForModalInfomation = "编辑景区成功!";
                        $("#modalid-toastInfo-se").modal("toggle");
                        $scope.getScenicPageList();
                    });
                }
            });
        //}
    };
    /**
     * 上传图片
     * @type {boolean}
     */
    $scope.doUpload = function () {
        var formData = new FormData($( "#uploadForm-se" )[0]);
        //var i = formData.entries();

        $.ajax({
            url: '/goods/addOneGoods_pic' ,
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (returndata) {
                $("#imgid-picPreview-se").attr("src",pathshowpic_se);
                $scope.editTicketPicture = pathshowpic_se;
                $scope.ifshowpic = true;
            },
            error: function (returndata) {
                console.log(returndata);
                $scope.ifshowpic = false;
            }
        });
    };
    /**
     * 加入购物车
     */
    $scope.addToCart = function () {
        $.ajax({
            type:"POST",
            url:"/cart/addToCart",
            data:{"id":$rootScope.idOfLoger,"ticketId":$scope.addToCartTemp.ticket.id,
                    "ticketNums":$scope.addToCart_goodsNums},
            contentType:"application/x-www-form-urlencoded",
            dataType:"json",
            success:function(data){
                console.log(data);
                $scope.$apply(function(){
                    $scope.justForModalInfomation = "添加进购物车成功!";
                    $("#modalid-toastInfo-se").modal("toggle");
                });
            }
        });
    };
    /**
     * 获取对一个景区的评价
     */
    $scope.getCommentsOfScenic = function (item) {
        $.ajax({
            type:"POST",
            url:"/comment/getCommentsOfScenic",
            data:{"currentPage":1,"pageSize":300, "scenicId":item.id},
            contentType:"application/x-www-form-urlencoded",
            dataType:"json",
            success:function(data){
                console.log(data);
                $scope.$apply(function(){
                    $scope.commentsList = new Array();
                    var obj = {};
                    for(var temp in data.page.list){
                        obj['id'] = data.page.list[temp].id;
                        obj['text'] = data.page.list[temp].text;
                        obj['user'] = data.page.list[temp].user;
                        obj['scenic'] = data.page.list[temp].scenic;
                        var datestr = new Date(parseInt(data.page.list[temp].createTime));
                        var temstr = datestr.getFullYear() + "年" + (parseInt(datestr.getMonth())+1) + "月" + datestr.getDate() + "日"
                            //+ datestr.getHours() + ":" + datestr.getMinutes() + ":" + datestr.getSeconds()
                            ;
                        obj['createTime'] = temstr;	//创建时间
                        $scope.commentsList.push(obj);obj = {};
                    }
                });
            }
        });
    };

});

var pathshowpic_se = "";

var showPreview_se = function (obj) {
    /*var Num="";
     for(var i=0;i<10;i++)
     {
     Num+=Math.floor(Math.random()*10);
     }*/
    pathshowpic_se = //$("#imgid-picPreview").attr("title")+
        "/goods-imgs/"+
        //Num+
        obj.value.substring(obj.value.lastIndexOf("\\")+1);
    //console.log($("#imgid-picPreview").attr("title"));
};