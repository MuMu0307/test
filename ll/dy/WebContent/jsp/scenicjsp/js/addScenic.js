/**
 * Created by zouyao on 2017/3/6.
 */
var addScenicCtrl = m.controller("addSceniccontroller",function ($rootScope,$scope) {

    /**
     * 新增景区初始化
     */
    $rootScope.as_init = function () {
        $scope.addScenicName = "";
        $scope.addScenicDescription = "";
        $scope.addTicketPicture= "";
        $scope.addTicketName= "";
        $scope.addTicketUnitPrice = "";
        $scope.addTicketDescription = "";
        $scope.addScenicUser= "";

        $scope.ifshowpic = false;
    };
    /**
     * 上传图片
     * @type {boolean}
     */
    $scope.doUpload = function () {
        var formData = new FormData($( "#uploadForm" )[0]);
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
                //console.log(returndata);
                //console.log(pathshowpic);
                $("#imgid-picPreview").attr("src",pathshowpic);
                $scope.addTicketPicture = pathshowpic;
                $scope.ifshowpic = true;
            },
            error: function (returndata) {
                console.log(returndata);
                $scope.ifshowpic = false;
            }
        });
    };
    /**
     * 新增景区
     * @type {string}
     */
    /**
     * String scenicName, String scenicDescription,
     String ticketName, String picture, Float unitPrice,
     String ticketDescription, Integer userId
     */
    $scope.addOneScenic = function () {
        /*String name, String picture, Integer unitPrice,
         String description, Integer salesCount,
         Integer ownerId, String ownerName*/
        if($scope.addScenicUser.id <= 0 || $scope.addScenicUser == null || $scope.addScenicUser == ""){
            this.addUserId = 0;
        }else {
            this.addUserId = $scope.addScenicUser.id;
        }

        if($scope.addScenicName == "" || $scope.addScenicDescription == "" || $scope.addTicketName == ""
            || $scope.addTicketUnitPrice == "" || $scope.addTicketDescription == ""){
            alert("请将信息填写完整!");
            return ;
        }

        if($scope.addTicketPicture == ""){
            alert("请上传图片!");
        }else{
            $.ajax({
                type:"POST",
                url:"/scenic/addOneScenic",
                data:{"scenicName":$scope.addScenicName,"scenicDescription":$scope.addScenicDescription,"picture":$scope.addTicketPicture,
                    "ticketName":$scope.addTicketName, "unitPrice":$scope.addTicketUnitPrice,"ticketDescription":$scope.addTicketDescription,
                    "userId":this.addUserId},
                contentType:"application/x-www-form-urlencoded",
                dataType:"json",
                success:function(data){
                    console.log(data);
                    $scope.$apply(function(){
                        $scope.justForModalInfomation = "新增景区成功!";
                        $("#modalid-toastInfo-as").modal("toggle");
                    });
                }
            });
        }
    };

    $(function () { $("[data-toggle='tooltip']").tooltip(); });

    /**
     * 选择景区负责人
     */
    $scope.chooseUserOnDuty = function () {
        //$scope.userCheckBoxsArray = new Array();
        $scope.currentPage = 1;
        $scope.blurUserName = "";
        $scope.getUserPageList();
        $("#modalid-chooseUserOnDuty").modal("toggle");
    };
    /*$scope.userCheckBoxs = function ($event,item) {
        if($event.target.checked == true){
            $scope.userCheckBoxsArray.push(item.id);
        }else{
            $scope.userCheckBoxsArray.splice(
                $scope.userCheckBoxsArray.indexOf(item.id),1
            );
        }
        console.log($scope.userCheckBoxsArray);
    };*/
    $scope.chooseUserOK = function (obj) {
        $scope.addScenicUser = obj;
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
            data:{"currentPage":this.currentPage,"pageSize":500,"blurUserName":$scope.blurUserName},
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

});

var pathshowpic = "";

var showPreview = function (obj) {
    /*var Num="";
    for(var i=0;i<10;i++)
    {
        Num+=Math.floor(Math.random()*10);
    }*/
    pathshowpic = //$("#imgid-picPreview").attr("title")+
        "/goods-imgs/"+
            //Num+
        obj.value.substring(obj.value.lastIndexOf("\\")+1);
    //console.log($("#imgid-picPreview").attr("title"));
};

