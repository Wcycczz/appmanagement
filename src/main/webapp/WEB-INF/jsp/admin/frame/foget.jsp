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
    <script src="${basePath }/static/scripts/boot.js" type="text/javascript"></script>
    <style type="text/css">
    html, body
    {        
        padding:0;
        margin:0;
        border:0;
        height:100%;
        overflow:hidden;
    }
    </style>
</head>
<body>    
    <form id="form1" method="post">
        <input name="id" class="mini-hidden" />
        <div style="padding-left:11px;padding-bottom:5px;">
            <table style="table-layout:fixed;" cellspacing="20">
                <tr>
                     <td style="width:80px;">手机号：</td>
                    <td style="width:150px;">    
                        <input name="phone" id="phone" class="mini-textbox"  maxlength="11" required="true" onvalidation="onPhoneValidation" allowCellValid="true" emptyText="请输入手机号"/>
                    </td>
                    <td style="width:50px;">    
                        <input type="button" id="btt" value="发送验证码" onclick="sendCode()"/>
                    </td>
                </tr>
                <tr>
                     <td style="width:80px;">验证码：</td>
                    <td style="width:150px;">    
                        <input name="code" class="mini-textbox"  maxlength="11" required="true"  emptyText="请输入验证码"/>
                    </td>
                </tr>
                <tr>
                    <td style="width:80px;">新密码：</td>
                    <td style="width:150px;">    
                        <input name="newpwd" class="mini-textbox"  maxlength="32" required="true"  emptyText="请输入新密码"/>
                    </td>
                </tr>
                <tr>
                    <td style="width:80px;">确认密码：</td>
                    <td style="width:150px;">    
                        <input name="confpwd" class="mini-textbox"  maxlength="32" required="true"  emptyText="请确认密码"/>
                    </td>
                </tr>
            </table>
        </div>
        
        <div style="text-align:center;padding:10px;">               
            <a class="mini-button" onclick="onOk" style="width:60px;margin-right:20px;">确定</a>       
            <a class="mini-button" onclick="onCancel" style="width:60px;">取消</a>       
        </div>        
    </form>
    <script type="text/javascript">
        mini.parse();
        var form = new mini.Form("form1");
        function SaveData() {
        	var path = '${basePath}';
            var o = form.getData(); 
            form.validate();
            if (form.isValid() == false) return;
            var json = mini.encode([o]);
            $.ajax({
                url: path+"/management/updatePwd",
		        type: 'post',
                data: { data: json },
                cache: false,
                success: function (text) {
                	alert(text.msg)
                    CloseWindow("save");
                },
                error: function (text) {
                    alert(text.msg);
                    CloseWindow();
                }
            });
        }
        
        function sendCode(){
        	var phone = $(" input[ name='phone' ] ").val();
        	var path = '${basePath}';
        	if(phone.length <1){
        		alert("手机号码不能为空!");
        		return;
        	} 
        	$.ajax({
				url : path+"/management/sendCode",
				type : "post",
				data : {
					"phone" : phone
				},
				success : function(da) {
					daojishi();
					return;
				},
				error: function(da){
					alert(da.msg);
				}
			});
        	
        }
        
        var countdown = 60;
		var val = document.getElementById("btt");
		function daojishi() {

			if (countdown == 0) {
				val.removeAttribute("disabled");//解绑事件
				val.value = "获取验证码";
				val.style.color = "#75acff";
				countdown = 60;
				clearTimeout(t);//清除计时器
				return;//结束遍历（or结束函数）
			} else {
				val.style.cursor = "wait";
				val.setAttribute("disabled", true);//删除事件
				val.value = countdown + "s" + "后重试";
				countdown--;
				val.style.color = "#cccccc";

			}
			setTimeout(function() {
				daojishi();
			}, 1000);

		}
        
        function GetData() {
            var o = form.getData();
            return o;
        }
        function CloseWindow(action) {            
            if (action == "close" && form.isChanged()) {
                if (confirm("数据被修改了，是否先保存？")) {
                    return false;
                }
            }
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();            
        }
        function onOk(e) {
            SaveData();
        }
        function onCancel(e) {
            CloseWindow("cancel");
        }
        function onPhoneValidation(e){
        	var re = /^[0-9]+.?[0-9]*$/;
            if(e.isValid){
            	if(!re.test(e.value)){
            		e.errorText = "必须输入数字";
                    e.isValid = false;
            	}
            	if(e.value.length < 11){
            		e.errorText = "手机号码错误";
                    e.isValid = false;
            	}
            }
        }
       
    </script>
</body>
</html>
