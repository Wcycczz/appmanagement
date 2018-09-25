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
    <script src="http://kindeditor.net/ke4/kindeditor-all-min.js?t=20160331.js" type="text/javascript"></script>
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
    <form id="form1" method="post"  target="subIfr" enctype="multipart/form-data" action="${basePath }/version/saveVersion">
        <input name="id" class="mini-hidden" />
        <div style="padding-left:11px;padding-bottom:5px;">
            <table style="table-layout:fixed;" cellspacing="20">
                <tr>
                    <td style="width:80px;">版本号：</td>
                    <td style="width:150px;">    
                        <input name="code" onvalidation="onCodeValidation" allowCellValid="true" class="mini-textbox"  maxlength="32" required="true"  emptyText="请输入版本号"/>
                    </td>
                </tr>
                 <tr>
                <td style="width:80px;">版本信息：</td>
                 <td style="width:150px;">    
                        <input name="info" class="mini-textbox"  maxlength="200" required="true"  emptyText="请输入版本信息"/>
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
                </tr>
                 <tr>
                    <td style="width:80px;">APK：</td>
                    <td style="width:150px;">    
                        <input type="file" name="file" id="file" style="width:70px";/>  
                    </td>
                </tr>
                 <tr>
                    <td style="width:80px;">平台：</td>
                    <td >                        
                    	<select name="platForm" class="mini-radiobuttonlist">
                        <option value="1">安卓</option>
                        <option value="2">IOS</option>
                    	</select>
                	</td>
                   
                </tr>
                <tr>
                <td style="width:80px;">是否强更：</td>
                    <td >                        
                    	<select name="forceflag" class="mini-radiobuttonlist">
                        <option value="1">否</option>
                        <option value="2">是</option>
                    	</select>
                	</td>
                </tr>
                
                 <tr>
                <td style="width:80px;">更新信息：</td>
                <!-- <td style="width:700px;"><textarea id="editor" name="info" rows="10" cols="80">
       				 </textarea>  </td> -->
       				 <td> <div name="updateInfo" id="editor" rows="10" cols="80">
       				 <input type="hidden" name="ui" id="ui"/>
        		</div>  </td>
                   
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
        var editor = KindEditor.create('#editor');
       
        var form = new mini.Form("form1");
        function SaveData() {
        	var path = '${basePath}';
            var o = form.getData(); 
            form.validate();
            if (form.isValid() == false) return;
            var img = $("#file").val();
            var u = editor.html();
            $("#ui").val(u);
            $("#form1").submit();
           /*  var json = mini.encode([o]);
            $.ajax({
                url: path+"/version/saveVersion",
		        type: 'post',
                data: { data: json ,content:editor.html()},
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
        
        function onCodeValidation(e){
        	var re = /^[0-9]*$/;
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
                    url: path+"/version/getVersionByCid?id=" + data.id,
                    cache: false,
                    success: function (text) {
                        var o = mini.decode(text);
                        editor.html(o.updateInfo);
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
