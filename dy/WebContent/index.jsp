<%--
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>测试</title>
    <link rel="stylesheet" href="login&signup/loginassets/assets/bootstrap/css/bootstrap.min.css">
  </head>
  <body ng-app="mainapp" ng-controller="maincontroller">
  <!-- Javascript -->
  <!--<script src="login&signup/signupassets/assets/js/jquery-1.11.1.min.js"></script>-->
  <script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
  <script src="login&signup/loginassets/assets/bootstrap/js/bootstrap.min.js"></script>
  <script src="login&signup/loginassets/assets/js/jquery.backstretch.min.js"></script>
  <script src="login&signup/loginassets/assets/js/scripts.js"></script>
  <script src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
  <script src="login&signup/md5.js"></script>
  <script src="login&signup/login.js"></script>

  <!--[if lt IE 10]>
  <script src="login&signup/loginassets/assets/js/placeholder.js"></script>
  <![endif]-->
  <input type="text" class="form-control" placeholder="请输入参数" ng-model="param"/>
  <button class="btn btn-info" ng-click="testFunc()">测试</button>
  <script>
      /**
      *"scenicName":"西湖景区","scenicDescripton":"好好玩","ticketName":"西湖门票",
       "picture":"/picxihu","unitPrice":1.6,"ticketDescription":"menpiao","userId":0
       */
      angular.module("mainapp",[])
          .controller("maincontroller",function($scope){
              $scope.param = "";
              $scope.testFunc = function(){
                  $.ajax({
                      type:"POST",
                      url:"/scenic/deleteOneScenic",
                      data:{"scenicId":4,"userId":17},
                      contentType:"application/x-www-form-urlencoded",
                      dataType:"json",
                      success:function(data){
                          console.log(data);
                      }
                  });
              };
          })
  </script>

  </body>
</html>
