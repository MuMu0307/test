/**
 * Created by zouyao on 2017/3/6.
 */
var myScenicCtrl = m.controller("mySceniccontroller",function ($rootScope,$scope) {

    $rootScope.myse_init = function () {
        $scope.ifshowpic = false;
        $scope.editTicketPicture = "";
        $scope.getOneScenicOfMine();
    };

    /**
     * 获取一个景区的信息(我管理的)
     */
    $scope.getOneScenicOfMine = function () {
        $.ajax({
            type:"POST",
            url:"/scenic/getOneScenicOfMine",
            data:{"id":$rootScope.idOfLoger},
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
     * 编辑景区
     * @type {string}
     */
    /**
     * String scenicName, String scenicDescription,
     String ticketName, String picture, Float unitPrice,
     String ticketDescription, Integer userId, Integer scenicId
     */
    $scope.editOneScenicOfMine = function () {
        /*String name, String picture, Integer unitPrice,
         String description, Integer salesCount,
         Integer ownerId, String ownerName*/

        if($scope.editOneScenicTemp[0].scenicName == "" || $scope.editOneScenicTemp[0].scenicDescription == "" || $scope.editOneScenicTemp[0].ticket.ticketName == ""
            || $scope.editOneScenicTemp[0].ticket.unitPrice == "" || $scope.editOneScenicTemp[0].ticket.description == ""){
            alert("请将信息填写完整!");
            return ;
        }

        if($scope.editTicketPicture == ""){
            $scope.editTicketPicture = $scope.editOneScenicTemp[0].ticket.picture;
        }

        /*if($scope.editTicketPicture == ""){
         //alert("请上传图片!");
         }else{*/
        $.ajax({
            type:"POST",
            url:"/scenic/editOneScenic",
            data:{"scenicName":$scope.editOneScenicTemp[0].scenicName,"scenicDescription":$scope.editOneScenicTemp[0].scenicDescription,"picture":$scope.editTicketPicture,
                "ticketName":$scope.editOneScenicTemp[0].ticket.ticketName, "unitPrice":$scope.editOneScenicTemp[0].ticket.unitPrice,"ticketDescription":$scope.editOneScenicTemp[0].ticket.description,
                "userId":0,"scenicId":$scope.editOneScenicTemp[0].id},
            contentType:"application/x-www-form-urlencoded",
            dataType:"json",
            success:function(data){
                console.log(data);
                $scope.$apply(function(){
                    $scope.justForModalInfomation = "编辑景区成功!";
                    $("#modalid-toastInfo-myse").modal("toggle");
                });
                $rootScope.myse_init();
            }
        });
        //}
    };
    /**
     * 上传图片
     * @type {boolean}
     */
    $scope.doUpload_myse = function () {
        var formData = new FormData($( "#uploadForm-myse" )[0]);
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
                $("#imgid-picPreview-myse").attr("src",pathshowpic_myse);
                $scope.editTicketPicture = pathshowpic_myse;
                $scope.ifshowpic = true;
            },
            error: function (returndata) {
                console.log(returndata);
                $scope.ifshowpic = false;
            }
        });
    };

});
var pathshowpic_myse = "";

var showPreview_myse = function (obj) {
    /*var Num="";
     for(var i=0;i<10;i++)
     {
     Num+=Math.floor(Math.random()*10);
     }*/
    pathshowpic_myse = //$("#imgid-picPreview").attr("title")+
        "/goods-imgs/"+
            //Num+
        obj.value.substring(obj.value.lastIndexOf("\\")+1);
    //console.log($("#imgid-picPreview").attr("title"));
};
