<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	request.setCharacterEncoding("UTF-8");
	String context = request.getContextPath() ;
	request.setAttribute("basePath", context) ;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>睿良企业管理后台</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <link href="${basePath }/static/css/demo.css" rel="stylesheet" type="text/css" />

    <script src="${basePath }/static/scripts/boot.js" type="text/javascript"></script>
</head>
<body>
    <div style="width:800px;">
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:100%;">
                        <a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
                        <a class="mini-button" iconCls="icon-add" onclick="edit()">编辑</a>
                        <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>       
                    </td>
                    <td style="white-space:nowrap;">
                        <input id="key" class="mini-textbox" emptyText="姓名,身份证,手机号" style="width:150px;" onenter="onKeyEnter"/>   
                        <a class="mini-button" onclick="search()">查询</a>
                    </td>
                </tr>
            </table>           
        </div>
    </div>
    <div id="datagrid1" class="mini-datagrid" style="width:1100px;height:450px;" allowResize="true"
        url="${basePath }/company/getUser"  idField="id" multiSelect="true">
        <div property="columns">
            <div type="checkcolumn" ></div>        
            <div field="id" width="40" headerAlign="center" allowSort="true">ID</div>    
            <div field="name" width="100" headerAlign="center" allowSort="true">姓名</div> 
            <div field="idCard" width="110" headerAlign="center" allowSort="true">身份证号</div> 
            <div field="mobile" width="70" headerAlign="center" allowSort="true">手机号码</div> 
            <div field="email" width="120" headerAlign="center" allowSort="true">邮箱</div>
            <div field="area" width="120" headerAlign="center" allowSort="true">地区</div> 
            <div field="address" width="120" headerAlign="center" allowSort="true">详细地址</div> 
            <div field="cTime" width="100" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss" allowSort="true">创建日期</div>
        </div>
    </div>
    

    <script type="text/javascript">
       mini.parse();
        var grid = mini.get("datagrid1");
        grid.load();
        var path = '${basePath}';
        function add() {
            mini.open({
                url: path + "/company/toUser",
                title: "新增管理员", width: 600, height: 400,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = { action: "new"};
                    iframe.contentWindow.SaveData(data);
                },
                ondestroy: function (action) {
                    grid.reload();
                }
            });
        } 
        function edit() {
            var row = grid.getSelected();
            if (row) {
                mini.open({
                    url: path + "/company/toUser",
                    title: "编辑管理员", width: 600, height: 400,
                    onload: function () {
                        var iframe = this.getIFrameEl();
                        var data = { action: "edit", id: row.id };
                        iframe.contentWindow.SetData(data);
                        
                    },
                    ondestroy: function (action) {
                        grid.reload();
                    }
                });
                
            } else {
                alert("请选中一条记录");
            }
            
        }
        function remove() {
            var rows = grid.getSelecteds();
            if (rows.length > 0) {
                if (confirm("确定删除选中记录？")) {
                    var ids = [];
                    for (var i = 0, l = rows.length; i < l; i++) {
                        var r = rows[i];
                        ids.push(r.id);
                    }
                    var id = ids.join(',');
                   grid.loading("操作中，请稍后......");
                    $.ajax({
                        url: path+"/company/deleteUserByids?id=" +id,
                        success: function (text) {
                            grid.reload();
                        },
                        error: function () {
                        }
                    });
                }
            } else {
                alert("请选中一条记录");
            }
        } 
         function search() {
            var key = mini.get("key").getValue();
            grid.load({ key: key });
        }
        function onKeyEnter(e) {
            search();
        } 
    </script>
</body>
</html>