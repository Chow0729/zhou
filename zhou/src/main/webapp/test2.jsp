<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../../taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
</head>
<body>
   
    <div>div</div>

    <canvas id="drawing" width="600" height="400">A drawing of something!</canvas>
    <script type="text/javascript">
    
        //鼠标右击
        $('div').mousedown(function(e) {
        	alert(e.which);
        });
    
    
    
        //绘制饼图
        var drawCircle = function(canvasId, data_arr, color_arr, text_arr){
            var drawing = document.getElementById(canvasId);
            var aaa = drawing.getContext;
            console.log(aaa);
            if(drawing.getContext) {
                var context = drawing.getContext('2d');
                var radius = drawing.height/2 -20,//半径
                    ox = radius +20, oy = radius +20;//圆心
                var width = 30, height = 10, //图例宽高
                    posX = ox * 2 +20, posY = 30;//图例位置
                var textX = posX + width + 5, textY = posY + 10;//文本位置
                var startAngle = 0, endAngle = 0;//起始、结束弧度
                context.strokeStyle = 'Purple';
                context.lineWidth = 3;
                context.strokeRect(0, 0, drawing.width, drawing.height);
                for(var i=0, len=data_arr.length; i<len; i++) {
                    //绘制饼图
                    endAngle += data_arr[i] * 2*Math.PI;
                    context.fillStyle = color_arr[i];
                    context.beginPath();
                    context.moveTo(ox, oy);
                    context.arc(ox, oy, radius, startAngle, endAngle, false);
                    context.closePath();
                    context.fill();
                    startAngle = endAngle;
                    //绘制图例
                    context.fillRect(posX, posY + 20 * i, width, height);
                    context.moveTo(posX, posY + 20 * i);
                    context.font = 'bold 12px Arial';
                    var percent = text_arr[i] + ' : ' + data_arr[i]*100 + '%';
                    context.fillText(percent, textX, textY + 20 * i);
                }

            }
        };

        var init = function(){
            var data_arr = [0.05, 0.25, 0.6, 0.1],
                color_arr = ['#00FF21', '#FFAA00', '#00AABB', '#FF4400'],
                text_arr =['第一季度', '第二季度', '第三季度', '第四季度'];
            drawCircle('drawing', data_arr, color_arr, text_arr);
        };

        init();

    </script>
</body>
</html>