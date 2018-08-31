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
		mini.parse();
		var code = 0;
		<c:if test="${not empty code}">
			code = '${code}';
		</c:if>
		if(code == 0){
			alert("保存成功");
			window.parent.CloseWindow();
		}else{
			alert('${msg}');
		}
	});
</script>
</head>
<body >   
<div id="loginWindow" class="mini-window" style="width:350px;height:165px;">

</div>


    

    
    <script type="text/javascript">
    function CloseWindow(action) {            
        if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
        else window.close();            
    }
    </script>

</body>
</html>