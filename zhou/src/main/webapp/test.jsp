<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../../taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>readAsDataUrl测试</title>
<script type="text/javascript">  
function readAsDataURL()  
{  
    if(typeof FileReader=='undifined') {        //判断浏览器是否支持filereader  
     
        result.innerHTML="<p>抱歉，你的浏览器不支持 FileReader</p>";  
        return false;  
    }  
    var file=document.getElementById("imagefile").files[0];  
    if(!/image\/\w+/.test(file.type))           //判断获取的是否为图片文件  
    {  
        alert("请确保文件为图像文件");  
        return false;  
    }  
    var reader=new FileReader(); 
    //调用reader.readAsDataURL方法时触发的方法   所以会先弹出00,再弹出11
    reader.onload=function(e) {  
    	alert("11");
        var result=document.getElementById("result");  
        result.innerHTML='<img src="'+this.result+'" alt=""/>'  
    }
    alert("00");
    //读取文件的方式
    reader.readAsDataURL(file);  
      
}  
</script>  
</head>
<body>
<p>  
    <label>请选择一个文件：</label>  
    <input type="file" id="imagefile"/>  
    <input type="button" value="读取图像" onClick="readAsDataURL();" />  
</p>  
<div id="result">  
    <!-- 这里用来显示图片结果-->  
</div> 
</body>
</html>