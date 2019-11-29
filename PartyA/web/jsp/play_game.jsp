<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Game board</title>
    <style type="text/css">
        body{
            background: #eee;
        }
        #canvas,#canvas1{
            position: absolute;
            left: 50%;
            top: 50%;
            margin-left: -250px;
            margin-top: -250px;
        }
        #canvas{
            z-index: 10;
        }
        #canvas1{
            z-index: 1;
            background: #b3b37d;
        }
        #whoseTurn{
            width: 100px;
            height: 50px;
            position: absolute;
            left: 50%;
            top: 50%;
            margin-left: -50px;
            margin-top: -280px;
            text-align: center;
        }
    </style>
    <script type="text/javascript" src="<%=basePath %>js/jquery-1.3.2.js"></script>

</head>
<body onselectstart="return false;" style="background: rgba(220,227,137,0.4)">
<h2 id="whoseTurn"></h2>
<canvas id="canvas" width="" height=""></canvas>
<canvas id="canvas1" width="" height=""></canvas>
</body>
</html>
<script type="text/javascript">
    function setMove(fromX, fromY, toX, toY){
        $.ajax({
            url: "move?fromX="+fromX+"&fromY="+fromY+"&toX="+toX+"&toY="+toY,
            contentType : "application/json;charset=utf-8",
            dataType: "text",
            type: "get",
            async: true,
            success : function(data) {
                // alert(data);　　　　　
                // mycallback(data,name);//回调函数　　　　
            }
        });
    }
    var offset = 50;
    var unit = 50;
    var width = 600;
    var height = 600;
    var chess = [
        //[txt, x, y]
        ['B',offset+3*unit,offset],['B',offset+4*unit,offset],['B',offset+5*unit,offset],['B',offset+6*unit,offset],['B',offset+7*unit,offset],['B',offset+5*unit,offset+1*unit],
        ['B',offset+3*unit,offset+10*unit],['B',offset+4*unit,offset+10*unit],['B',offset+5*unit,offset+10*unit],['B',offset+6*unit,offset+10*unit],['B',offset+7*unit,offset+10*unit],['B',offset+5*unit,offset+9*unit],
        ['B',offset,offset+3*unit],['B',offset,offset+4*unit],['B',offset,offset+5*unit],['B',offset,offset+6*unit],['B',offset,offset+7*unit],['B',offset,offset+5*unit],
        ['B',offset+10*unit,offset+3*unit],['B',offset+10*unit,offset+4*unit],['B',offset+10*unit,offset+5*unit],['B',offset+10*unit,offset+6*unit],['B',offset+10*unit,offset+7*unit],['B',offset+9*unit,offset+5*unit],
        ['W',offset+5*unit,offset+3*unit],['W',offset+5*unit,offset+7*unit],
        ['W',offset+4*unit,offset+4*unit],['W',offset+5*unit,offset+4*unit],['W',offset+6*unit,offset+4*unit],['W',offset+4*unit,offset+6*unit],['W',offset+5*unit,offset+6*unit],['W',offset+6*unit,offset+6*unit],
        ['W',offset+3*unit,offset+5*unit],['W',offset+4*unit,offset+5*unit],['W',offset+6*unit,offset+5*unit],['W',offset+7*unit,offset+5*unit],
        ['K',offset+5*unit,offset+5*unit]
    ];
    //store current piece info: x，y
    var desc_click = [-1,-1];
    //0 black; 1 white
    var whoseTurn = 0;
    window.onload = function (){
        var canvas = document.getElementById("canvas");
        var canvas1 = document.getElementById("canvas1");
        canvas.width = width;
        canvas.height = height;
        canvas1.width = width;
        canvas1.height = height;
        var context = canvas.getContext("2d");
        var context1 = canvas1.getContext("2d");
        draw_ChessBoard(context1);
        draw_all_pieces(context);
        update_h2();
        canvas.onclick = function(e){
            // alert("e.clientX="+e.clientX+",e.clientY="+e.clientY);
            // alert("canvas.offsetLeft="+canvas.offsetLeft+",canvas.offsetTop="+canvas.offsetTop);
            if(desc_click[0]==-1&&desc_click[1]==-1){
                get_Chess(context,e.clientX - canvas.offsetLeft,e.clientY - canvas.offsetTop);
            }else{
                put_Chess(context,e.clientX - canvas.offsetLeft,e.clientY - canvas.offsetTop);
            }
        };
    }
    function put_Chess(context,x,y){
        var to_x = 0,to_y = 0, i;
        if (x%100>80||x%100<20) {to_x = 100*Math.round(x/100)};
        if (x%100>30&&x%100<70) {to_x = x>100?(Math.floor(x/100)*100 + 50):50};
        if (y%100>80||y%100<20) {to_y = 100*Math.round(y/100)};
        if (y%100>30&&y%100<70) {to_y = y>100?(Math.floor(y/100)*100 + 50):50};
        for (i = 0;i < chess.length; i++) {
            if (chess[i][1] == desc_click[0] && chess[i][2] == desc_click[1]) {
                // alert("move before:x="+chess[i][1]+",y="+chess[i][2]);
                setMove(chess[i][1], chess[i][2], to_x, to_y);
                chess[i][1] = to_x;
                chess[i][2] = to_y;
                //reset desc_click
                desc_click=[-1,-1];
                break;
            }
        }
        draw_all_pieces(context);
        whoseTurn = (whoseTurn===0?1:0);
        update_h2();
    }
    function get_Chess(context,x,y){
        var from_x = 0,from_y = 0, i;
        if (x%100>80||x%100<20) {from_x = 100*Math.round(x/100)};
        if (x%100>30&&x%100<70) {from_x = x>100?(Math.floor(x/100)*100 + 50):50};
        if (y%100>80||y%100<20) {from_y = 100*Math.round(y/100)};
        if (y%100>30&&y%100<70) {from_y = y>100?(Math.floor(y/100)*100 + 50):50};
        for (i = 0;i < chess.length; i++) {
            if (chess[i][1] == from_x && chess[i][2] == from_y) {
                var txt = (whoseTurn===0)?"B":"W,K";
                // alert("whoseTurn:"+turn);
                if(txt.indexOf(chess[i][0])!= -1){
                    draw_chosen_piece(context,from_x,from_y);
                    desc_click = [from_x,from_y];
                    return false;
                }else{
                    alert("it's not your turn! ");
                }
            }
        }
    }
    
    function draw_ChessBoard(context){
        context.lineWidth = 2;
        context.clearRect(0,0,width,height);
        context.fillStyle = "#b3b37d";
        context.fillRect(0,0,width,height);
        context.beginPath();
        for(var i = 0; i <11; i++) {
            context.moveTo(offset+i * 50, offset+0);
            context.lineTo(offset+i * 50, offset+500);
            context.stroke();
            context.moveTo(offset+0, offset+i * 50);
            context.lineTo(offset+500, offset+i * 50);
            context.stroke();
        }
        context.restore();
        // draw cross: left-top
        context.beginPath();
        context.moveTo(offset, offset);
        context.lineTo(offset+unit, offset+unit);
        context.stroke();
        context.moveTo(offset+unit, offset);
        context.lineTo(offset, offset+unit);
        context.stroke();
        //right-top
        context.moveTo(offset+9*unit, offset);
        context.lineTo(offset+10*unit, offset+unit);
        context.stroke();
        context.moveTo(offset+10*unit, offset);
        context.lineTo(offset+9*unit, offset+unit);
        context.stroke();
        //left-bottom
        context.moveTo(offset, offset+9*unit);
        context.lineTo(offset+unit, offset+10*unit);
        context.stroke();
        context.moveTo(offset+unit, offset+9*unit);
        context.lineTo(offset, offset+10*unit);
        context.stroke();
        //right-bottom
        context.moveTo(offset+9*unit, offset+9*unit);
        context.lineTo(offset+10*unit, offset+10*unit);
        context.stroke();
        context.moveTo(offset+10*unit, offset+9*unit);
        context.lineTo(offset+9*unit, offset+10*unit);
        context.stroke();

    }
    function draw_all_pieces(context){
        context.clearRect(0,0,width,height);
        for (var i = 0;i < chess.length; i++) {
            draw_one_piece(context,chess[i][0],chess[i][1],chess[i][2]);
        }
    }
    function draw_one_piece(context, txt, x, y){
        var color;
        if(txt=='B'){
            color = "Black";
        }else if(txt=='W'){
            color = "White";
        }else if(txt=='K'){
            color = "#fe687a";
        }
        context.save();
        context.beginPath();
        var b_Color = context.createLinearGradient(x+15,y-15,x-15,y+15);
        b_Color.addColorStop(0,"#f3f5d5");
        b_Color.addColorStop(1,"#8c834d");
        context.arc(x,y,22,0,2*Math.PI);
        context.fillStyle = b_Color;
        context.fill();
        context.beginPath();
        context.arc(x,y,17,0,2*Math.PI);
        context.fillStyle = "#f5da94";
        context.strokeStyle = "#c9c876";
        context.stroke();
        context.fill();
        context.beginPath();
        context.fillStyle = color;
        context.font = "900 24px KaiTi_GB2312";
        context.fillText(txt,x-12,y+8);
        context.restore();
    }
    function draw_chosen_piece(context, x, y){
        context.beginPath();
        context.moveTo(x-23,y-10);
        context.lineTo(x-23,y-23);
        context.lineTo(x-10,y-23);
        context.moveTo(x+10,y-23);
        context.lineTo(x+23,y-23);
        context.lineTo(x+23,y-10);
        context.moveTo(x-23,y+10);
        context.lineTo(x-23,y+23);
        context.lineTo(x-10,y+23);
        context.moveTo(x+23,y+10);
        context.lineTo(x+23,y+23);
        context.lineTo(x+10,y+23);
        context.stroke();
    }
    function update_h2(){
        var h2 = document.getElementById("whoseTurn");
        h2.innerHTML = (whoseTurn === 0?"WhoseTurn:Black":"WhoseTurn:White");
        h2.style.color = whoseTurn == 0?"#000000":"#ff0000";
    }
</script>