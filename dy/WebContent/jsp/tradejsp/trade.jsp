<%--
  Created by IntelliJ IDEA.
  User: zouy
  Date: 2016/12/23
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>H5交易平台</title>
    <!-- 引入 Bootstrap -->
    <link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        body{
            padding:0px;
        }
        th,td,ul{
            text-align:center;
        }
    </style>
</head>
<body style="background-color: #f7f7f7;" ng-app="mainapp" ng-controller="maincontroller">
<%@ include  file="trade_include.jsp"%>
<div >
<!--top operation-->
<div class="container" style="background-color: #f7f7f7;padding: 0px;">
    <nav class="navbar navbar-default" role="navigation" style="background-color: #f0ad4e;">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="index.html"
                style="margin-top:-5px;"><img src="jsp/tradejsp/assets/img/logo.png" alt="logo"></a>
            </div>
            <div>
                <form class="navbar-form navbar-right" role="search">
                    <div class="form-group">
                        <div class="btn-group">
                            <button ng-show="idOfLoger > 0" type="button" class="btn btn-danger" ng-click="topOperation(constRef[0][5])">
                                <span class="glyphicon glyphicon-off"></span>&nbsp;
                                <span ng-bind="constRef[0][5]"></span>
                            </button>
                            <button ng-show="idOfLoger == null" type="button" class="btn btn-default" ng-click="topOperation(constRef[0][0])">
                                <span class="glyphicon glyphicon-user"></span>&nbsp;
                                <span ng-bind="constRef[0][0]"></span>
                            </button>
                            <button type="button" class="btn btn-success" ng-click="topOperation(constRef[0][1])">
                                <span class="glyphicon glyphicon-tasks"></span>&nbsp;
                                <span ng-bind="constRef[0][1]"></span>
                            </button>
                            </button>
                            <button ng-show="idOfLoger > 0" type="button" class="btn btn-info" ng-click="topOperation(constRef[0][2])">
                                <span class="glyphicon glyphicon-home"></span>&nbsp;
                                <span ng-bind="constRef[0][2]"></span>
                            </button>
                            <button ng-show="idOfLoger > 0" type="button" class="btn btn-danger" ng-click="topOperation(constRef[0][6])">
                                <span class="glyphicon glyphicon-lock"></span>&nbsp;
                                <span ng-bind="constRef[0][6]"></span>
                            </button>
                            <button ng-show="idOfLoger == 8" type="button" class="btn btn-primary" ng-click="topOperation(constRef[0][3])">
                                <span class="glyphicon glyphicon-th"></span>&nbsp;
                                <span ng-bind="constRef[0][3]"></span>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </nav>
</div>

<!--center(left -> right)-->
<div class="container" style="background-color: #f7f7f7;margin-top: 2px;">
    <div class="row" >
        <div class="col-xs-2" style="border: 1px solid #d9edf7;padding: 2px;">
            <ul class="nav nav-pills nav-stacked">
                <!--这里a标签不能加href属性，否则会相对首页进行#跳转，但在静态页面无影响-->
                <li class="active" id="liid-goodslist"><a style="cursor: pointer;" ng-click="rightDiv(constRef[1][0])" ng-bind="constRef[1][0]"></a></li>
                <li id="liid-shopcart"><a style="cursor: pointer;" ng-click="rightDiv(constRef[1][1])" ng-bind="constRef[1][1]"></a></li>
                <li id="liid-orderform"><a style="cursor: pointer;" ng-click="rightDiv(constRef[1][2])" ng-bind="constRef[1][2]"></a></li>
                <li id="liid-publishgoods"><a style="cursor: pointer;" ng-click="rightDiv(constRef[1][3])" ng-bind="constRef[1][3]"></a></li>
                <li id="liid-mypublish"><a style="cursor: pointer;" ng-click="rightDiv(constRef[1][4])" ng-bind="constRef[1][4]"></a></li>
            </ul>
        </div>

        <div class="col-xs-10" style="background-color: #f7f7f7;" id="divid-goodslist">
            <caption>
                <nav class="navbar navbar-default" role="navigation">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <a class="navbar-brand">商品列表</a>
                        </div>
                        <div>
                            <form class="navbar-form navbar-right" role="search"  onsubmit="return ;">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default"><span ng-bind="goodsSortConst"></span></button>
                                    <button type="button" class="btn btn-default dropdown-toggle"
                                            data-toggle="dropdown">
                                        <span class="caret"></span>
                                        <span class="sr-only">切换下拉菜单</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a style="cursor: pointer;" ng-click="goodsSortConstFunc($event)">全部</a></li>
                                        <li class="divider"></li>
                                        <li><a style="cursor: pointer;" ng-click="goodsSortConstFunc($event)">按价格从低到高</a></li>
                                        <li><a style="cursor: pointer;" ng-click="goodsSortConstFunc($event)">按价格从高到低</a></li>
                                        <li class="divider"></li>
                                        <li><a style="cursor: pointer;" ng-click="goodsSortConstFunc($event)">按时间由近及远</a></li>
                                        <li><a style="cursor: pointer;" ng-click="goodsSortConstFunc($event)">按时间由远及近</a></li>
                                        <li class="divider"></li>
                                        <li><a style="cursor: pointer;" ng-click="goodsSortConstFunc($event)" >按销量从多到少</a></li>
                                        <li><a style="cursor: pointer;" ng-click="goodsSortConstFunc($event)">按销量从少到多</a></li>
                                    </ul>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="请输入商品名" ng-model="searchGoodsName">
                                </div>
                                <button type="button" class="btn btn-default" ng-click="getGoodsPageList()">
                                    <span class="glyphicon glyphicon-search"></span>&nbsp;<span ng-bind="constRef[0][4]"></span>
                                </button>
                            </form>
                        </div>
                    </div>
                </nav>
            </caption>
            <!--<table class="table table-hover table-striped">
                <thead>
                <tr><th>商品名</th><th>图片</th><th>商品描述</th><th>单价(元)</th><th>销量</th><th>上架时间</th><th>操作</th></tr>
                </thead>
                <tbody>
                <tr ng-repeat="item in goodsList">
                    <td ng-bind="item.goodsName"></td>
                    <td ng-bind="item.picture"></td>
                    <td ng-bind="item.description"></td>
                    <td ng-bind="item.unitPrice"></td>
                    <td ng-bind="item.salesVolume"></td>
                    <td ng-bind="item.createTime"></td>
                    <td><button ng-click="actionOnUser(item,constRef[0][0])" type="button" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-user"></span>&nbsp;<span ng-bind="constRef[0][0]"></span></button>
                        <button ng-click="actionOnUser(item,constRef[0][1])" type="button" class="btn btn-warning btn-sm"><span class="glyphicon glyphicon-exclamation-sign"></span>&nbsp;<span ng-bind="constRef[0][1]"></span></button>
                        <button ng-click="actionOnUser(item,constRef[0][2])" type="button" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-remove"></span>&nbsp;<span ng-bind="constRef[0][2]"></span></button></td>
                </tr>
                </tbody>
            </table>-->
            <div style="width:930px;min-width: 930px;">
                <%--goods001--%>
                <div ng-show="goodsList.length >= 1" style="float:left;height: 350px;">
                    <img style="margin-right: 30px;" width="200px" height="200px" ng-src="{{goodsList[0].picture}}" alt="no img"/>
                    <div>
                        <h6>商品名称：<span ng-bind="goodsList[0].name"></span></h6>
                        <h6>单价(元)：<span ng-bind="goodsList[0].unitPrice"></span></h6>
                        <h6>销量：<span ng-bind="goodsList[0].salesCount"></span></h6>
                        <h6>发布时间：<span ng-bind="goodsList[0].createTime"></span></h6>
                        <div>
                            <button class="btn btn-info" ng-click="infoGoodsModal(goodsList[0])">查看详情</button>
                            <button class="btn btn-warning" ng-click="addToCartModal(goodsList[0])">加入购物车</button>
                        </div>
                    </div>
                </div>
                <%--goods002--%>
                <div ng-show="goodsList.length >= 2" style="float:left;height: 350px;">
                    <img style="margin-right: 30px;" width="200px" height="200px" ng-src="{{goodsList[1].picture}}" alt="no img"/>
                    <div>
                        <h6>商品名称：<span ng-bind="goodsList[1].name"></span></h6>
                        <h6>单价(元)：<span ng-bind="goodsList[1].unitPrice"></span></h6>
                        <h6>销量：<span ng-bind="goodsList[1].salesCount"></span></h6>
                        <h6>发布时间：<span ng-bind="goodsList[1].createTime"></span></h6>
                        <div>
                            <button class="btn btn-info" ng-click="infoGoodsModal(goodsList[1])">查看详情</button>
                            <button class="btn btn-warning" ng-click="addToCartModal(goodsList[1])">加入购物车</button>
                        </div>
                    </div>
                </div>
                <%--goods003--%>
                <div ng-show="goodsList.length >= 3" style="float:left;height: 350px;">
                    <img style="margin-right: 30px;" width="200px" height="200px" ng-src="{{goodsList[2].picture}}" alt="no img"/>
                    <div>
                        <h6>商品名称：<span ng-bind="goodsList[2].name"></span></h6>
                        <h6>单价(元)：<span ng-bind="goodsList[2].unitPrice"></span></h6>
                        <h6>销量：<span ng-bind="goodsList[2].salesCount"></span></h6>
                        <h6>发布时间：<span ng-bind="goodsList[2].createTime"></span></h6>
                        <div>
                            <button class="btn btn-info" ng-click="infoGoodsModal(goodsList[2])">查看详情</button>
                            <button class="btn btn-warning" ng-click="addToCartModal(goodsList[2])">加入购物车</button>
                        </div>
                    </div>
                </div>
                <%--goods004--%>
                <div ng-show="goodsList.length >= 4" style="float:left;height: 350px;">
                    <img style="margin-right: 30px;" width="200px" height="200px" ng-src="{{goodsList[3].picture}}" alt="no img"/>
                    <div>
                        <h6>商品名称：<span ng-bind="goodsList[3].name"></span></h6>
                        <h6>单价(元)：<span ng-bind="goodsList[3].unitPrice"></span></h6>
                        <h6>销量：<span ng-bind="goodsList[3].salesCount"></span></h6>
                        <h6>发布时间：<span ng-bind="goodsList[3].createTime"></span></h6>
                        <div>
                            <button class="btn btn-info" ng-click="infoGoodsModal(goodsList[3])">查看详情</button>
                            <button class="btn btn-warning" ng-click="addToCartModal(goodsList[3])">加入购物车</button>
                        </div>
                    </div>
                </div>
                <%--goods005--%>
                <div ng-show="goodsList.length >= 5" style="float:left;height: 350px;">
                    <img style="margin-right: 30px;" width="200px" height="200px" ng-src="{{goodsList[4].picture}}" alt="no img"/>
                    <div>
                        <h6>商品名称：<span ng-bind="goodsList[4].name"></span></h6>
                        <h6>单价(元)：<span ng-bind="goodsList[4].unitPrice"></span></h6>
                        <h6>销量：<span ng-bind="goodsList[4].salesCount"></span></h6>
                        <h6>发布时间：<span ng-bind="goodsList[4].createTime"></span></h6>
                        <div>
                            <button class="btn btn-info" ng-click="infoGoodsModal(goodsList[4])">查看详情</button>
                            <button class="btn btn-warning" ng-click="addToCartModal(goodsList[4])">加入购物车</button>
                        </div>
                    </div>
                </div>
                <%--goods006--%>
                <div ng-show="goodsList.length >= 6" style="float:left;height: 350px;">
                    <img style="margin-right: 30px;" width="200px" height="200px" ng-src="{{goodsList[5].picture}}" alt="no img"/>
                    <div>
                        <h6>商品名称：<span ng-bind="goodsList[5].name"></span></h6>
                        <h6>单价(元)：<span ng-bind="goodsList[5].unitPrice"></span></h6>
                        <h6>销量：<span ng-bind="goodsList[5].salesCount"></span></h6>
                        <h6>发布时间：<span ng-bind="goodsList[5].createTime"></span></h6>
                        <div>
                            <button class="btn btn-info" ng-click="infoGoodsModal(goodsList[5])">查看详情</button>
                            <button class="btn btn-warning" ng-click="addToCartModal(goodsList[5])">加入购物车</button>
                        </div>
                    </div>
                </div>
                <%--goods007--%>
                <div ng-show="goodsList.length >= 7" style="float:left;height: 350px;">
                    <img style="margin-right: 30px;" width="200px" height="200px" ng-src="{{goodsList[6].picture}}" alt="no img"/>
                    <div>
                        <h6>商品名称：<span ng-bind="goodsList[6].name"></span></h6>
                        <h6>单价(元)：<span ng-bind="goodsList[6].unitPrice"></span></h6>
                        <h6>销量：<span ng-bind="goodsList[6].salesCount"></span></h6>
                        <h6>发布时间：<span ng-bind="goodsList[6].createTime"></span></h6>
                        <div>
                            <button class="btn btn-info" ng-click="infoGoodsModal(goodsList[6])">查看详情</button>
                            <button class="btn btn-warning" ng-click="addToCartModal(goodsList[6])">加入购物车</button>
                        </div>
                    </div>
                </div>
                <%--goods008--%>
                <div ng-show="goodsList.length >= 8" style="float:left;height: 350px;">
                    <img style="margin-right: 30px;" width="200px" height="200px" ng-src="{{goodsList[7].picture}}" alt="no img"/>
                    <div>
                        <h6>商品名称：<span ng-bind="goodsList[7].name"></span></h6>
                        <h6>单价(元)：<span ng-bind="goodsList[7].unitPrice"></span></h6>
                        <h6>销量：<span ng-bind="goodsList[7].salesCount"></span></h6>
                        <h6>发布时间：<span ng-bind="goodsList[7].createTime"></span></h6>
                        <div>
                            <button class="btn btn-info" ng-click="infoGoodsModal(goodsList[7])">查看详情</button>
                            <button class="btn btn-warning" ng-click="addToCartModal(goodsList[7])">加入购物车</button>
                        </div>
                    </div>
                </div>
                <%--goods009--%>
                <%--<div ng-show="test[0][0] == '登录'" style="float:left;height: 350px;">
                    <img style="margin-right: 30px;" width="200px" height="200px" src="" alt="no img"/>
                    <div>
                        <h6>商品名称：<span>hello</span></h6><h6>商品描述：<span>hoolo</span></h6><h6>发布时间：<span>2019</span></h6>
                        <div>
                            <button class="btn btn-info">查看详情</button>
                            <button class="btn btn-warning">加入购物车</button>
                        </div>
                    </div>
                </div>--%>
            </div>
            <div style="clear:both;"></div>
            <div style="margin-bottom: 20px;"><!--分页bar ng-if则不能给button设置disabled因为ng-if不满足条件不会生成相应dom元素-->
                <span ng-show="true">
                    <input type="button" ng-click="makePagingList(prevPage)" class="btn btn-default btnid-prevpage" value={{prevPage}} />&nbsp;
                    <input type="text" disabled style="text-align:center;width:50px;" ng-model="currentPage" />&nbsp;
                    <input type="button" ng-click="makePagingList(nextPage)"  class="btn btn-default btnid-nextpage" value="{{nextPage}}" />&nbsp;
                    <span>共&nbsp;</span>
                    <input type="text" readonly="readonly" style="text-align:center;width:50px;border:none;" ng-model="totalPage" />
                    <span>&nbsp;页</span>
                </span>
            </div>
        </div>

        <%--购物车--%>
        <div class="col-xs-10" style="background-color: #f7f7f7;display: none;" id="divid-shopcart">
            <caption>
                <nav class="navbar navbar-default" role="navigation">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <a class="navbar-brand">购物车</a>
                        </div>
                        <div>
                            <form class="navbar-form navbar-right" role="search"  onsubmit="return ;">
                                <!--<div class="form-group">
                                    <input type="text" class="form-control" placeholder="请输入用户名" ng-model="searchUserName">
                                </div>-->
                                <%--<button type="button" class="btn btn-default" ng-click="actionOnUser(this,constRef[0][3])">
                                    <span class="glyphicon glyphicon-search"></span>&nbsp;<span ng-bind="constRef[0][3]"></span>
                                </button>--%>
                            </form>
                        </div>
                    </div>
                </nav>
            </caption>
            <div style="height: 600px;overflow-y: scroll;">
                <table class="table table-hover table-striped table-bordered">
                    <thead>
                        <tr><th>选择</th><th>商品名</th><th>卖家</th><th>图片</th><th>商品描述</th><th>单价(元)</th><th>购买数量</th><%--<th>操作</th>--%></tr>
                    </thead>
                    <tbody>
                        <tr ng-if="goodsListOfCart.length <= 0"><td colspan="7">购物车空空如也...</td></tr>
                        <tr ng-repeat="item in goodsListOfCart">
                            <td style="vertical-align:middle"><label style="margin-top: -25px;" class="checkbox-inline"><input type="checkbox" style="cursor:pointer;" ng-click="cartCheckBoxs($event,item)"></label></td>
                            <td style="vertical-align:middle" ng-bind="item.goodsName"></td>
                            <td style="vertical-align:middle" ng-bind="item.goodsOwnerName"></td>
                            <td style="vertical-align:middle"><img ng-src="{{item.picture}}" alt="" width="100px" height="100px"></td>
                            <td style="vertical-align:middle" ng-bind="item.description"></td>
                            <td style="vertical-align:middle" ng-bind="item.goodsUnitPrice"></td>
                            <td style="vertical-align:middle" ng-bind="item.goodsNums"></td>
                            <%--<td><button ng-click="actionOnUser(item,constRef[0][0])" type="button" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-user"></span>&nbsp;<span ng-bind="constRef[0][0]"></span></button></td>--%>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div style="margin-top:20px;margin-bottom: 20px;" ng-show="goodsListOfCart.length > 0">
                <button class="btn btn-success" ng-click="addOneOrder()">生成订单</button>
                <button class="btn btn-danger" ng-click="deleteFromCart()">从购物车删除</button>
            </div>
            <%--<div><!--分页bar ng-if则不能给button设置disabled因为ng-if不满足条件不会生成相应dom元素-->
                <span ng-show="userList.length > 0">
                    <input type="button" ng-click="makePagingList(prevPage)" class="btn btn-default btnid-prevpage" value={{prevPage}} />&nbsp;
                    <input type="text" disabled style="text-align:center;width:50px;" ng-model="currentPage" />&nbsp;
                    <input type="button" ng-click="makePagingList(nextPage)"  class="btn btn-default btnid-nextpage" value="{{nextPage}}" />&nbsp;
                    <span>共&nbsp;</span>
                    <input type="text" readonly="readonly" style="text-align:center;width:50px;border:none;" ng-model="totalPage" />
                    <span>&nbsp;页</span>
                </span>
            </div>--%>
        </div>

        <%--订单--%>
        <div class="col-xs-10" style="background-color: #f7f7f7;display: none;" id="divid-orderform">
            <caption>
                <nav class="navbar navbar-default" role="navigation">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <a class="navbar-brand">订单</a>
                        </div>
                        <div>
                            <form class="navbar-form navbar-right" role="search"  onsubmit="return ;">
                                <button type="button" class="btn btn-warning" ng-click="getOrder('all')">
                                    全部订单
                                </button>
                                <button type="button" class="btn btn-danger" ng-click="getOrder('notDone')">
                                    未支付订单
                                </button>
                                <button type="button" class="btn btn-success" ng-click="getOrder('isDone')">
                                    已支付订单
                                </button>
                            </form>
                        </div>
                    </div>
                </nav>
            </caption>
            <div style="height: 600px;overflow-y: scroll;">
                <table class="table table-hover table-striped table-bordered">
                    <thead>
                    <tr><th>订单编号</th><th>订单详情</th><th>状态</th><th>创建时间</th><th>操作</th></tr>
                    </thead>
                    <tbody>
                    <tr ng-if="goodsListOfOrder[0].length <= 0"><td colspan="7">暂时没有相应订单...</td></tr>
                    <tr ng-repeat="item in goodsListOfOrder" ng-if="item.length > 0">
                        <%--<span ng-repeat="index1 in item track by $index">--%>
                            <td style="vertical-align:middle" ng-bind="item[0].orderId"></td>
                            <td style="vertical-align:middle">
                                <div ng-repeat="item_ in item">
                                    <div style="float: left;">
                                        <img ng-src="{{item_.picture}}" alt="" width="100px" height="100px">
                                    </div>
                                    <div style="float: left;width: 150px;margin-bottom: 5px;
                                    height:100px;background-color: floralwhite;">
                                        <h5>商品名: <span ng-bind="item_.goodsName"></span></h5>
                                        <h5>购买数量: <span ng-bind="item_.goodsNums"></span></h5>
                                        <h5>单价(元): <span ng-bind="item_.goodsUnitPrice"></span></h5>
                                        <h5>总价(元): <span ng-bind="item_.goodsNums*item_.goodsUnitPrice | number : 1"></span></h5>
                                    </div>
                                    <div style="clear: both;"></div>
                                </div>
                            </td>
                            <%--<td style="vertical-align:middle"><span ng-bind="item_.goodsNums*item_.goodsUnitPrice"></span></td>--%>
                            <td style="vertical-align:middle" ng-bind="item[0].haveDone"></td>
                            <td style="vertical-align:middle" ng-bind="item[0].createTime"></td>
                            <td style="vertical-align:middle">
                                <button ng-show="item[0].haveDone == '未支付'" class="btn btn-warning" ng-click="completeOrder(item[0])">支付</button>
                                <button class="btn btn-danger" ng-click="deleteOneOrder(item[0])">删除</button>
                            </td>
                        <%--</span>--%>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="col-xs-10" style="background-color: #f7f7f7;display: none;" id="divid-publishgoods">
                <caption>
                    <nav class="navbar navbar-default" role="navigation">
                        <div class="container-fluid">
                            <div class="navbar-header">
                                <a class="navbar-brand">发布商品</a>
                            </div>
                            <div>
                                <form class="navbar-form navbar-right" role="search"  onsubmit="return ;">
                                    <!--<div class="form-group">
                                        <input type="text" class="form-control" placeholder="请输入角色名" ng-model="searchRoleName">
                                    </div>
                                    <button type="button" class="btn btn-default" ng-click="actionOnRole(this,constRef[2][3])">
                                        <span class="glyphicon glyphicon-search"></span>&nbsp;<span ng-bind="constRef[2][3]"></span>
                                    </button>
                                    <button type="button" class="btn btn-default" ng-click="actionOnRole(this,constRef[2][2])">
                                        <span class="glyphicon glyphicon-plus"></span>&nbsp;<span ng-bind="constRef[2][2]"></span>
                                    </button>-->
                                </form>
                            </div>
                        </div>
                    </nav>
                </caption>
                <div style="margin-bottom: 20px;">
                    <%--<form role="form">--%>
                        <div class="form-group" style="width: 60%">
                            <label >商品名称：</label>
                            <input type="text" class="form-control" ng-model="addGoods_name"  placeholder="请输入商品名称">
                        </div>
                        <div class="form-group" style="width: 60%">
                            <form id= "uploadForm">
                                <p >上传商品图片： <input class="form-control" onchange="showPreview(this)" type="file" name="file"/></p>
                                <input type="button" class="btn btn-success" value="上传" ng-click="doUpload()" />
                            </form>
                        </div>
                        <div class="form-group" style="width: 60%" ng-show="ifshowpic">
                            <label >图片预览：</label>
                            <img width="300px" title="<%= request.getSession().getServletContext().getRealPath("/")%>" id="imgid-picPreview" height="300px" src="" alt="goods img">
                        </div>
                        <div class="form-group" style="width: 60%">
                            <label >商品单价(元)：</label>
                            <input type="text" class="form-control" ng-model="addGoods_unitPrice"  placeholder="请输入商品单价(元)">
                        </div>
                        <div class="form-group" style="width: 60%">
                            <label >商品描述</label>
                            <textarea class="form-control" rows="6" cols="20"
                                      ng-model="addGoods_description" placeholder="请输入商品描述"></textarea>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox"> <span ng-click="tradeProtocol()">已阅读用户交易协议</span>
                            </label>
                        </div>
                        <button type="submit" class="btn btn-success" ng-click="addOneGoods()">确认发布</button>
                        <%--<button type="submit" class="btn btn-default">重置</button>--%>
                    <%--</form>--%>
                </div>
        </div>

        <div class="col-xs-10" style="background-color: #f7f7f7;display: none;" id="divid-mypublish">
            <caption>
                <nav class="navbar navbar-default" role="navigation">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <a class="navbar-brand">商品列表</a>
                        </div>
                        <div>
                            <form class="navbar-form navbar-right" role="search"  onsubmit="return ;">
                                <%--<div class="btn-group">
                                    <button type="button" class="btn btn-default"><span ng-bind="goodsSortConst"></span></button>
                                    <button type="button" class="btn btn-default dropdown-toggle"
                                            data-toggle="dropdown">
                                        <span class="caret"></span>
                                        <span class="sr-only">切换下拉菜单</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a style="cursor: pointer;" ng-click="goodsSortConstFunc($event)">全部</a></li>
                                        <li class="divider"></li>
                                        <li><a style="cursor: pointer;" ng-click="goodsSortConstFunc($event)">按价格从低到高</a></li>
                                        <li><a style="cursor: pointer;" ng-click="goodsSortConstFunc($event)">按价格从高到低</a></li>
                                        <li class="divider"></li>
                                        <li><a style="cursor: pointer;" ng-click="goodsSortConstFunc($event)">按时间由近及远</a></li>
                                        <li><a style="cursor: pointer;" ng-click="goodsSortConstFunc($event)">按时间由远及近</a></li>
                                        <li class="divider"></li>
                                        <li><a style="cursor: pointer;" ng-click="goodsSortConstFunc($event)" >按销量从多到少</a></li>
                                        <li><a style="cursor: pointer;" ng-click="goodsSortConstFunc($event)">按销量从少到多</a></li>
                                    </ul>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="请输入商品名" ng-model="searchGoodsName">
                                </div>
                                <button type="button" class="btn btn-default" ng-click="getGoodsPageList()">
                                    <span class="glyphicon glyphicon-search"></span>&nbsp;<span ng-bind="constRef[0][4]"></span>
                                </button>--%>
                            </form>
                        </div>
                    </div>
                </nav>
            </caption>
            <div style="height: 600px;overflow-y: scroll;">
                <table class="table table-hover table-striped table-bordered">
                    <thead>
                    <tr><th>商品名</th><th>图片</th><th>商品描述</th><th>单价(元)</th><th>销量</th><th>上架时间</th><th>操作</th></tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="item in myPubGoodsList">
                        <td style="vertical-align:middle" ng-bind="item.name"></td>
                        <td style="vertical-align:middle"><img width="100px" height="100px" ng-src="{{item.picture}}" alt=""></td>
                        <td style="vertical-align:middle" ng-bind="item.description"></td>
                        <td style="vertical-align:middle" ng-bind="item.unitPrice"></td>
                        <td style="vertical-align:middle" ng-bind="item.salesCount"></td>
                        <td style="vertical-align:middle" ng-bind="item.createTime"></td>
                        <td style="vertical-align:middle"><button class="btn btn-danger" ng-click="deleteOneGoods(item)">删除(下架)</button></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <!--<div class="clearfix visible-xs"></div>-->
    </div>
</div>

</div><%--app.ctrl-end--%>
<script src="http://cdn.static.runoob.com/libs/jquery/2.0.2/jquery.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
<%--<script src="http://apps.bdimg.com/libs/angular-file-upload/1.3.1/angular-file-upload.min.js"></script>--%>
<%--<script src="jsp/tradejsp/js/ng-file-upload-shim.min.js"></script>
<script src="jsp/tradejsp/js/ng-file-upload.min.js"></script>--%>
<%--<script src="jsp/tradejsp/js/ng-file-upload-all.min.js"></script>--%>
<%--<script src="jsp/tradejsp/js/ajaxfileupload.js"></script>--%>
<script src="jsp/tradejsp/js/js.js"></script>
<script src="jsp/tradejsp/js/md5.js"></script>

</body>
</html>
