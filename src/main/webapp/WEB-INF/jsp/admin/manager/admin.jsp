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
                    <td style="width:80px;">用户名：</td>
                    <td style="width:150px;">    
                        <input name="username" class="mini-textbox"  maxlength="32" required="true"  emptyText="请输入姓名"/>
                    </td>
                    <td style="width:80px;">密码：</td>
                    <td style="width:150px;">    
                        <input name="password" class="mini-textbox"  maxlength="32" required="true"  emptyText="请输入密码"/>
                    </td>
                </tr>
                
               <tr>
               <td style="width:80px;">手机号码：</td>
                    <td style="width:150px;">    
                        <input name="phone" class="mini-textbox" onvalidation="onPhoneValidation" allowCellValid="true"   maxlength="11" required="true"  emptyText="请输入手机号码"/>
                    </td>
                    <td style="width:80px;">状态：</td>
                	<td >                        
                    	<select name="status" class="mini-radiobuttonlist">
                        <option value="0">有效</option>
                        <option value="1">无效</option>
                    	</select>
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
                url: path+"/management/saveManager",
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

        function SetData(data) {
        	var path = '${basePath}';
            if (data.action == "edit") {
                data = mini.clone(data);
                $.ajax({
                    url: path+"/management/getManagerByCid?id=" + data.id,
                    cache: false,
                    success: function (text) {
                        var o = mini.decode(text);
                        form.setData(o);
                        form.setChanged(false);
                    }
                });
            }
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
