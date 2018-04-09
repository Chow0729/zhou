<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../../taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>房间管理</title>
</head>
<body>

	<ul class="nav nav-tabs">
		<li role="presentation" class="active"><a href="#roomTab"
			data-toggle="tab" id="myRoom">房间</a></li>
		<li role="presentation"><a href="#roomTypeTab" data-toggle="tab"
			id="myRoomType">房间类型</a></li>
	</ul>

	<div id="myContent" class="tab-content">
		<!-- 房间 开始 -->
		<div class="tab-pane fade in active" id="roomTab">
			<div class="panel-body" style="padding-bottom: 0px;">
				<div class="panel panel-default">
					<div class="panel-heading">查询条件</div>
					<div class="panel-body">
						<form id="formSearch" class="form-horizontal"
							onsubmit="return false;">
							<div class="form-group" style="margin-top: 15px">
								<div class="col-sm-1" style="padding-top: 0">
									<button class="btn btn-default dropdown-toggle" type="button"
										id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="true">
										<span id="serchFiledSelceted" style="font-weight: 700;">全部</span>
										<span class="caret"></span>
									</button>
									<ul class="dropdown-menu" aria-labelledby="dropdownMenu1"
										style="min-width: 100px; width: 80px; left: 13px;">
										<li><a href="javascript:void(0);"
											style="padding-left: 15px; font-weight: 700"
											onclick="selectedFiled(this)">角色名称</a></li>
										<li><a href="javascript:void(0);"
											style="padding-left: 15px; font-weight: 700"
											onclick="selectedFiled(this)">角色代码</a></li>
										<li role="separator" class="divider"></li>
										<li><a href="javascript:void(0);"
											style="padding-left: 15px; font-weight: 700"
											onclick="selectedFiled(this)">全部</a></li>
									</ul>
								</div>
								<div class="col-sm-3" style="position: relative;">
									<input type="text" class="form-control"
										id="txt_search_condition" placeholder="请输入查询内容">
								</div>
								<div class="col-sm-3">
									<button type="button" style="margin-left: 50px" id="btn_query"
										class="btn btn-primary">查询</button>
								</div>
							</div>
						</form>
					</div>
				</div>

				<div id="toolbar" class="btn-group">
					<button id="btn_add" type="button" class="btn btn-default">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
					</button>
					<button id="btn_edit" type="button" class="btn btn-default">
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
					</button>
					<button id="btn_delete" type="button" class="btn btn-default">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
					</button>
				</div>
				<table id="roomTable"></table>
			</div>
		</div>
		<!-- 房间 结束 -->

		<!-- 房间类型 开始 -->
		<div class="tab-pane fade" id="roomTypeTab">
			<div class="tab-pane fade in active" id="roomTab">
				<div class="panel-body" style="padding-bottom: 0px;">
					<div class="panel panel-default">
						<div class="panel-heading">查询条件</div>
						<div class="panel-body">
							<form id="formSearch" class="form-horizontal"
								onsubmit="return false;">
								<div class="form-group" style="margin-top: 15px">
									<div class="col-sm-1" style="padding-top: 0">
										<button class="btn btn-default dropdown-toggle" type="button"
											id="dropdownMenu1" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="true">
											<span id="typeSerchFiledSelceted" style="font-weight: 700;">全部</span>
											<span class="caret"></span>
										</button>
										<ul class="dropdown-menu" aria-labelledby="dropdownMenu1"
											style="min-width: 100px; width: 80px; left: 13px;">
											<li><a href="javascript:void(0);"
												style="padding-left: 15px; font-weight: 700"
												onclick="selectedFiled(this)">类型</a></li>
											<li role="separator" class="divider"></li>
											<li><a href="javascript:void(0);"
												style="padding-left: 15px; font-weight: 700"
												onclick="selectedFiled(this)">全部</a></li>
										</ul>
									</div>
									<div class="col-sm-3" style="position: relative;">
										<input type="text" class="form-control"
											id="txt_type_search_condition" placeholder="请输入查询内容">
									</div>
									<label class="control-label col-sm-1" for="txt_search_roomTypeStatus">房源状态</label>
									<div class="col-sm-2">
										<select class="form-control" id="txt_search_roomTypeStatus" onchange="changeRoomTypeStatus()">
											<option value="-1" selected="selected">全部</option>
											<option value="1">有房</option>
											<option value="0">无房</option>
											<option value="2">紧张</option>
										</select>
									</div>
									<div class="col-sm-3">
										<button type="button" style="margin-left: 50px" id="btn_room_type_query"
											class="btn btn-primary">查询</button>
									</div>
								</div>
							</form>
						</div>
					</div>

					<div id="RoomTypeToolbar" class="btn-group">
						<button id="btn_add_room_type" type="button" class="btn btn-default">
							<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
						</button>
						<button id="btn_edit_room_type" type="button" class="btn btn-default">
							<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
						</button>
						<button id="btn_delete_room_type" type="button" class="btn btn-default">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
						</button>
					</div> 
					<table id="roomTypeTable"></table>
				</div>
			</div>
			<!-- 房间类型结束 -->
		</div>

       <!--  添加房间类型模态框 开始 -->
       <div class="modal fade" id="room_type_modal" tabindex="-1" role="dialog"
        aria-labelledby="myModalLabel" aria-hidden="true">
        <form class="form-horizontal" id="roomTypeInfoForm">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">添加房间类型</h4>
                    </div>
                    <div class="modal-body">
                        <div class="panel-body" style="padding-bottom: 0px;">
                            <div class="panel panel-default">
                                <div class="panel-heading">请填写房间信息</div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-sm-12 form-group">
                                            <label class="col-sm-2 control-label" for="txt_roomTypeName">类型名称</label>
                                            <div class="col-sm-10" style="padding-right: 45px;padding-left: 10px;">
                                                <input class="form-control"
                                                    id="txt_roomTypeName" name="roomTypeName" type="text"
                                                    placeholder="请输入类型名称" />
                                            </div>
                                          </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-6 form-group">
                                            <label class="col-sm-4 control-label" for="txt_roomNum">房间数量</label>
                                            <div class="col-sm-8">
                                                <input class="form-control"
                                                    id="txt_roomNum" name="roomNumber" type="number"
                                                    placeholder="请输入房间数量" />
                                            </div>
                                        </div>
                                        <div class="col-sm-6 form-group">
                                            <label class="col-sm-4 control-label" for="txt_bedsNum">床位数量</label>
                                            <div class="col-sm-8">
													<input class="form-control" id="txt_bedsNum"
														name="bedsNum" type="number" placeholder="请输入床位数量" />
												</div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-6 form-group">
                                            <label class="col-sm-4 control-label" for="txt_roomPrice">房价</label>
                                            <div class="col-sm-8">
                                                <input class="form-control"
                                                    id="txt_roomPrice" name="roomPrice" type="number"
                                                    placeholder="请输入房价" />
                                            </div>
                                        </div>
                                        <div class="col-sm-6 form-group">
                                            <label class="col-sm-4 control-label" for="txt_status">状态</label>
                                            <div class="col-sm-8">
													<select class="form-control" id="txt_status" name="status"> 
													    <option value="-1">请选择</option>
													    <option value="1">有房</option>
													    <option value="0">无房</option>
													    <option value="2">紧张</option>
													</select>
												</div>
                                        </div>
                                    </div>
                                    <input type="hidden" id="createdTimeInput" name="createdTime"/>
                                    <input type="hidden" id="roleIdInput" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                        </button>
                        <button type="submit" class="btn btn-primary" id="btn_confirm"
                            name="submit">
                            <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>确认
                        </button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal -->
        </form>
    </div>
       <!--  添加房间类型模态框 结束 -->
        
		<script type="text/javascript" src="js/business/room_manage.js"></script>
		<script type="text/javascript" src="js/business/room_type_manage.js"></script>
</body>
</html>