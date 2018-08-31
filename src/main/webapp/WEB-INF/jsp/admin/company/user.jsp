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
    <script>
    $().ready(function() {
    	//<c:if test="${not empty msg}">
    		//alert('${msg}');
    		 //CloseWindow();
    	//</c:if>
		
    });
    </script>
</head>
<body>    
    <form id="form1" method="post" target="subIfr" enctype="multipart/form-data" action="${basePath }/company/saveUser">
        <input name="id" class="mini-hidden" />
        <div style="padding-left:11px;padding-bottom:5px;">
            <table style="table-layout:fixed;" cellspacing="20">
                <tr>
                    <td style="width:80px;">姓名：</td>
                    <td style="width:150px;">    
                        <input name="name" class="mini-textbox"  maxlength="32" required="true"  emptyText="请输入姓名"/>
                    </td>
                    <td style="width:80px;">身份证号码：</td>
                    <td style="width:150px;">    
                        <input name="idCard" onvalidation="onCardValidation" allowCellValid="true" class="mini-textbox"  maxlength="18" required="true"  emptyText="请输入身份证号码"/>
                    </td>
                </tr>
               <tr>
                     <td style="width:80px;">所属公司：</td>
                    <td style="width:150px;">    
                        <input name="cid" class="mini-combobox" valueField="cId" textField="name" 
                            url="${basePath }/company/getCus"
                            onvaluechanged="onDeptChanged" required="true"
                             emptyText="请选择公司"
                            />
                    </td>
                    <td style="width:80px;">手机号码：</td>
                    <td style="width:150px;">    
                        <input name="mobile" onvalidation="onPhoneValidation" allowCellValid="true" maxlength="11"  class="mini-textbox"  required="true"  emptyText="请输入手机号码"/>
                    </td>
                </tr>
                <tr>
                    <td style="width:80px;">密码：</td>
                    <td style="width:150px;">    
                        <input name="password" class="mini-textbox"  maxlength="32" required="true"  emptyText="请输入密码"/>
                    </td>
                    <td style="width:80px;">邮箱：</td>
                    <td style="width:150px;">    
                        <input name="email" maxlength="32"  class="mini-textbox"  vtype="email"/>
                    </td>
                </tr>
                <tr>
                    <td style="width:80px;">地区：</td>
                    <td style="width:150px;">    
                        <input name="area" class="mini-textbox"  maxlength="32" />
                    </td>
                     <td style="width:80px;">住址：</td>
                    <td style="width:150px;">    
                        <input name="address" maxlength="32"  class="mini-textbox"  />
                    </td>
                </tr>
                <tr>
                 <td style="width:80px;">图像：</td>
                    <td style="width:150px;">  
                    <input type="file" name="file" id="file" style="width:70px";/>  
                    </td>
                    <td style="width:80px;">性别：</td>
                	<td >                        
                    	<select name="gender" class="mini-radiobuttonlist">
                        <option value="1">男</option>
                        <option value="2">女</option>
                    	</select>
                	</td>
                	
                </tr>
                <tr>
                <td style="width:80px;">管理员：</td>
                	<td >                        
                    	<select name="type" class="mini-radiobuttonlist">
                        <option value="1">是</option>
                        <option value="0">否</option>
                    	</select>
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
    <iframe id="subIfr" name="subIfr" height="0" width="0" frameborder="0"></iframe>
    <script type="text/javascript">
        mini.parse();
        var form = new mini.Form("form1");
        function SaveData() {
            form.validate();
            if (form.isValid() == false) return;
            var img = $("#file").val();
            $("#form1").submit();
            //var json = mini.encode([o]);
            /* $.ajax({
                url: path+"/company/saveUser",
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
            }); */
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
        
        function onCardValidation(e){
        	var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
            if(e.isValid){
            	if(!reg.test(e.value)){
            		e.errorText = "身份证格式错误";
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
                    url: path+"/company/getUserByCid?id=" + data.id,
                    cache: false,
                    success: function (text) {
                        var o = mini.decode(text);
                        form.setData(o);
                        form.setChanged(false);
                    }
                });
            }
        }
        
        function onDeptChanged(e) {
            var deptCombo = mini.getbyName("cid");
            var dept_id = deptCombo.getValue();
        }
        
        function onUploadSuccess(e) {
            alert("上传成功：" + e.serverData);
            this.setText("");
        }
        function onUploadError(e) {
        }
        function onFileSelect(e) {
            //alert("选择文件");
        }



       
    </script>
</body>
</html>
