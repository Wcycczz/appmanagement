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

    <style type="text/css">
    body{
        margin:0;padding:0;border:0;width:100%;height:100%;overflow:hidden;
    }    
  
    </style>
</head>
<body>
    
<!--Layout-->
<div id="layout1" class="mini-layout" style="width:100%;height:100%;">
    <div class="app-header" region="north" height="70" showSplit="false" showHeader="false">
        <h1 style="margin:0;padding:15px;cursor:default;font-family:微软雅黑,黑体,宋体;">睿良企业后台管理</h1>
         <div style="position:absolute;top:12px;right:10px;">
         <a class="mini-button mini-button-iconTop" iconCls="icon-date"   plain="true" >当前时间:${sessionScope.time}</a>
         <a class="mini-button mini-button-iconTop" iconCls="icon-user"   plain="true" >欢迎您:${sessionScope.admin.username}</a>
         <a class="mini-button mini-button-iconTop" iconCls="icon-edit" onclick="retpwd"  plain="true" >修改密码</a> 
         <a class="mini-button mini-button-iconTop" iconCls="icon-close" onclick="onClick"  plain="true" >注销</a> 
            
        </div>
        
    </div>
    <div title="south" region="south" showSplit="false" showHeader="false" height="30" >
        <div style="line-height:28px;text-align:center;cursor:default">Copyright © 深圳睿良企业管理有限公司版权所有 </div>
    </div>
    <div title="center" region="center" style="border:0;" bodyStyle="overflow:hidden;">
        <!--Splitter-->
        <div class="mini-splitter" style="width:100%;height:100%;" borderStyle="border:0;">
            <div size="180" maxSize="250" minSize="100" showCollapseButton="true" style="border:0;">
                <!--OutlookTree-->
                <div id="leftTree" class="mini-outlooktree" url="${basePath }/static/txt/outlooktree.txt" onnodeclick="onNodeSelect"
                    textField="text" idField="id" parentField="pid" urlField="url">
                </div>
                
            </div>
            <div showCollapseButton="false" style="border:0;">
            <div id="mainTabs" class="mini-tabs" activeIndex="2" style="width:100%;height:100%;"      
                     plain="false" onactivechanged="onTabsActiveChanged">
                </div>
            </div>    
        </div>
    </div>
</div>

    

    <script type="text/javascript">
        mini.parse();
        var path = '${basePath}';
        var tree = mini.get("leftTree");
        function showTab(node) {
            var tabs = mini.get("mainTabs");
            var id = "tab$" + node.id;
            var tab = tabs.getTab(id);
            if (!tab) {
                tab = {};
                tab._nodeid = node.id;
                tab.name = id;
                tab.title = node.text;
                tab.showCloseButton = true;
                tab.url = path+node.url;
                tabs.addTab(tab);
            }
            tabs.activeTab(tab);
        } 

        function onNodeSelect(e) {
            var node = e.node;
            var isLeaf = e.isLeaf;
            if (isLeaf) {
                showTab(node);
            }
        }


        function onTabsActiveChanged(e) {
            var tabs = e.sender;
            var tab = tabs.getActiveTab();
            if (tab && tab._nodeid) {
                
                var node = tree.getNode(tab._nodeid);
                if (node && !tree.isSelectedNode(node)) {
                    tree.selectNode(node);
                }
            }
        }
        
        function retpwd(e){
        	mini.open({
                url: path + "/management/goRest",
                title: "修改密码", width: 400, height: 300,
                ondestroy: function (action) {
                	if(action == 'success'){
                		window.location.href=path+"/management/goLogin";
                	}
                	
                }
            });
        }
        
        function onClick(e){
        	window.location.href=path+"/management/goLogin";
        }
    </script>

</body>
</html>