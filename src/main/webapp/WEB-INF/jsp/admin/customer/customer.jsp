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
                    <td style="width:80px;">公司名称：</td>
                    <td style="width:150px;">    
                        <input name="name" class="mini-textbox"  maxlength="32" required="true"  emptyText="请输入名称"/>
                    </td>
                    <td style="width:80px;">公司编码：</td>
                    <td style="width:150px;">    
                        <input name="cId" class="mini-textbox"  maxlength="32" required="true"  emptyText="请输入编码"/>
                    </td>
                </tr>
                
               <tr>
                    <td style="width:80px;">apiKey：</td>
                    <td style="width:150px;">    
                        <input name="apiKey" class="mini-textbox"  maxlength="32" required="true"  emptyText="请输入apiKey"/>
                    </td>
                    <td style="width:80px;">管理员人数：</td>
                    <td style="width:150px;">    
                        <input name="mgrQua" maxlength="4" onvalidation="onMgrValidation" class="mini-textbox" allowCellValid="true" required="true"  emptyText="请输入人数"/>
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
                url: path+"/customer/saveCustomer",
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
        
        function onMgrValidation(e){
        	var re = /^[0-9]+.?[0-9]*$/;
            if(e.isValid){
            	if(!re.test(e.value)){
            		e.errorText = "必须输入数字";
                    e.isValid = false;
            	}
            }
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
                    url: path+"/customer/getCustomerByCid?id=" + data.id,
                    cache: false,
                    success: function (text) {
                        var o = mini.decode(text);
                        form.setData(o);
                        form.setChanged(false);
                    }
                });
            }
        }
       
    </script>
</body>
</html>
