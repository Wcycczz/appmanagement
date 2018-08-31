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

    <style type="text/css">
    body
    {
        width:100%;height:100%;margin:0;overflow:hidden;
    }
    </style>
    <script src="${basePath }/static/scripts/boot.js" type="text/javascript"></script>
    <script>
	$().ready(function() {

	<c:if test="${not empty msg}">
	alert('${msg}');
	</c:if>

	});
</script>
</head>
<body >   
<div id="loginWindow" class="mini-window" title="用户登录" style="width:350px;height:165px;">
<form action="${basePath }/management/login" method="post" id="act" onsubmit="return logincheck()">
    <div id="loginForm" style="padding:15px;padding-top:10px;">
        <table >
            <tr>
                <td style="width:60px;"><label for="username$text">帐号：</label></td>
                <td>
                    <input id="username" name="username"  class="mini-textbox"  style="width:150px;"/>
                </td>    
            </tr>
            <tr>
                <td style="width:60px;"><label for="pwd$text">密码：</label></td>
                <td>
                    <input id="password" name="password"  class="mini-password"   style="width:150px;"/>
                    &nbsp;&nbsp;<a  onclick="onClick()" href="#">忘记密码?</a>
                </td>
            </tr>            
            <tr>
                <td></td>
                <td style="padding-top:5px;">
                <input type="submit" value="登录"  style="width:50px;"></input>
                <input type="reset" value="重置"  style="width:50px;"></input>
                </td>
            </tr>
        </table>
    </div>
</form>
</div>


    

    
    <script type="text/javascript">
        mini.parse();
        var loginWindow = mini.get("loginWindow");
        loginWindow.show(); 
        var path = '${basePath}';
        function logincheck() {
            var name = $('input[name="username"]').val();
            var pwd = $('input[name="password"]').val();
            if(null == name || " " == name || "" == name){
            	alert("用户名不能为空");
            	return false;
            }
            if(null == pwd || " " == pwd || "" == pwd){
            	alert("密码不能为空");
            	return false;
            } 
			return true;
            
        }
        
        function onClick(){
                mini.open({
                    url: path + "/management/goForget",
                    title: "设置密码", width: 400, height: 300,
                    
                });
        }
    </script>

</body>
</html>