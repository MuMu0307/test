<%--
  Created by IntelliJ IDEA.
  User: zouy
  Date: 2016/12/24
  Time: 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>modal jsp</title>
</head>
<body>
<!-- 查看商品详情  模态弹出框 -->
<div class="modal fade" id="modalid-infoGoods">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <!--  >模态弹出窗标题<  -->
                <h4 class="modal-title">商品详情</h4>
            </div>
            <div class="modal-body">
                <!--  >模态弹出窗主体内容<  -->
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">商品名</label>
                        <div class="col-sm-9">
                            <input type="text" disabled class="form-control" ng-model="infoGoodsModalTemp.name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-9">
                            <input type="text" disabled class="form-control" ng-model="infoGoodsModalTemp.description">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">单价(元)</label>
                        <div class="col-sm-9">
                            <input type="text" disabled class="form-control" ng-model="infoGoodsModalTemp.unitPrice">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">销量</label>
                        <div class="col-sm-9">
                            <input type="text" disabled class="form-control" ng-model="infoGoodsModalTemp.salesCount">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">卖家</label>
                        <div class="col-sm-9">
                            <input type="text" disabled class="form-control" ng-model="infoGoodsModalTemp.ownerName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">上架时间</label>
                        <div class="col-sm-9">
                            <input type="text" disabled class="form-control" ng-model="infoGoodsModalTemp.createTime">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" data-dismiss="modal">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 查看(编辑)用户信息  模态弹出框 -->
<div class="modal fade" id="modalid-viewUserInfo">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <!--  >模态弹出窗标题<  -->
                <h4 class="modal-title">用户信息</h4>
            </div>
            <div class="modal-body">
                <!--  >模态弹出窗主体内容<  -->
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 col-sm-offset-2 control-label">用户名</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="username" ng-model="editLogerArray[0].username" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 col-sm-offset-2 control-label">邮箱</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="email" ng-model="editLogerArray[0].email" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 col-sm-offset-2 control-label">电话</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="tel" ng-model="editLogerArray[0].tel" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 col-sm-offset-2 control-label">注册时间</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" ng-model="editLogerArray[0].createTime" disabled>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" ng-click="editLoger()" data-dismiss="modal">确定(提交)</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 管理员查看用户信息  模态弹出框 -->
<div class="modal fade" id="modalid-manageUserInfo">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <!--  >模态弹出窗标题<  -->
                <h4 class="modal-title">用户信息</h4>
            </div>
            <div class="modal-body">
                <!--  >模态弹出窗主体内容<  -->
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 col-sm-offset-2 control-label">用户名</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="username" ng-model="manageUserInfoTemp.username" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 col-sm-offset-2 control-label">邮箱</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="email" ng-model="manageUserInfoTemp.email" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 col-sm-offset-2 control-label">电话</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="tel" ng-model="manageUserInfoTemp.tel" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 col-sm-offset-2 control-label">注册时间</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" ng-model="manageUserInfoTemp.createTime" disabled>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" ng-click="" data-dismiss="modal">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 管理员管理用户  模态弹出框 -->
<div class="modal fade" id="modalid-manageUser">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <!--  >模态弹出窗标题<  -->
                <h4 class="modal-title">用户管理</h4>
            </div>
            <div class="modal-body">
                <!--  >模态弹出窗主体内容<  -->
                <form class="form-horizontal" role="form">
                    <table style="height: 10px;max-height: 60px;" class="table table-hover table-striped table-bordered">
                        <caption>用户列表</caption>
                        <thead>
                        <tr><th>用户名</th><th>状态</th><th>电话</th><th>邮箱</th><th>注册时间</th><th>操作</th></tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="item in userList">
                            <td ng-bind="item.username"></td>
                            <td ng-bind="item.isDelete"></td>
                            <td ng-bind="item.tel"></td>
                            <td ng-bind="item.email"></td>
                            <td ng-bind="item.createTime"></td>
                            <td><%--<button ng-click="manageUserInfo(item)" type="button" class="btn btn-info btn-sm">
                                <span class="glyphicon glyphicon-tasks"></span>&nbsp;<span>查看资料</span></button>--%>
                                <button ng-show="item.isDelete == '正常'" ng-disabled="item.username == 'root'" ng-click="freezeUser(item)" type="button" class="btn btn-danger btn-sm">
                                <span class="glyphicon glyphicon-remove"></span>&nbsp;<span>封号</span></button>
                                <button ng-show="item.isDelete == '已被封号'" ng-click="unFreezeUser(item)" type="button" class="btn btn-success btn-sm">
                                    <span class="glyphicon glyphicon-ok"></span>&nbsp;<span>解封</span></button></td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" data-dismiss="modal"><span class="glyphicon glyphicon-ok"></span>&nbsp;确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 修改密码  模态弹出框 -->
<div class="modal fade" id="modalid-modifyPWD">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <!--  >模态弹出窗标题<  -->
                <h4 class="modal-title">修改密码</h4>
            </div>
            <div class="modal-body">
                <!--  >模态弹出窗主体内容<  -->
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 col-sm-offset-2 control-label">旧密码</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="username" ng-model="editPassWord" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 col-sm-offset-2 control-label">新密码</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="email" ng-model="editPassWordNew" >
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" ng-click="modifyPWD()" data-dismiss="modal">确定</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 加入购物车  模态弹出框 -->
<div class="modal fade" id="modalid-addToCart">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <!--  >模态弹出窗标题<  -->
                <h4 class="modal-title">加入购物车</h4>
            </div>
            <div class="modal-body">
                <!--  >模态弹出窗主体内容<  -->
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 col-sm-offset-2 control-label">商品名称</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="username" ng-model="addToCartTemp.name" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 col-sm-offset-2 control-label">单价(元)</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="email" ng-model="addToCartTemp.unitPrice" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 col-sm-offset-2 control-label">购买数量</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="email" ng-model="addToCart_goodsNums" >
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" ng-click="addToCart()" data-dismiss="modal">确定</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 提示信息 模态弹出框-->
<div class="modal fade" id="modalid-toastInfo">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">提示信息</h4>
            </div>
            <div class="modal-body">
                <p><span ng-bind="justForModalInfomation"></span></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" data-dismiss="modal">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 修改密码成功后注销 模态弹出框-->
<div class="modal fade" id="modalid-mpswoffconf">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">提示信息</h4>
            </div>
            <div class="modal-body">
                <p><span ng-bind="justForModalInfomation"></span></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" ng-click="offFunction()" data-dismiss="modal">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 注销确认 模态弹出框-->
<div class="modal fade" id="modalid-offconf">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">提示信息</h4>
            </div>
            <div class="modal-body">
                <p>确认要注销吗?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="offFunction()">确定</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

</body>
</html>
