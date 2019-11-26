<%@ page language="java" pageEncoding="UTF-8"%>

<%@ page contentType="text/html;charset=UTF-8"%>
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
            /*宽度是250px,高度是250px*/
            margin-left: -250px;
            margin-top: -250px;
        }
        /*层级较高*/
        #canvas{
            z-index: 10;
        }
        /*层级低但是有背景颜色*/
        #canvas1{
            z-index: 1;
            background: #b3b37d;
        }
        #title{
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
</head>
<body onselectstart="return false;">
<h2 id="title"></h2>
<canvas id="canvas" width="" height=""></canvas>
<canvas id="canvas1" width="" height=""></canvas>
</body>
</html>
<script type="text/javascript">
    var chess = [
        //[txt, x, y, whoseTurn]
        ['B',150,0,0],['B',200,0,0],['B',250,0,0],['B',300,0,0],['B',350,0,0],['B',250,50,0],
        ['B',150,500,0],['B',200,500,0],['B',250,500,0],['B',300,500,0],['B',350,500,0],['B',250,500,0],
        ['B',0,150,0],['B',0,200,0],['B',0,250,0],['B',0,300,0],['B',0,350,0],['B',50,250,0],
        ['B',500,150,0],['B',500,200,0],['B',500,250,0],['B',500,300,0],['B',500,350,0],['B',500,250,0],

        ['W',250,150,0],['W',250,350,0],
        ['W',200,200,0],['W',250,200,0],['W',300,200,0],['W',200,300,0],['W',250,300,0],['W',300,300,0],
        ['W',150,250,0],['W',200,250,0],['W',300,250,0],['W',350,250,0],

        ['K',250,250,0]
    ];

    //index, x，y
    var desc_click = [-1,-1,-1];
    //0 black; 1 white
    var whose = 0;

    window.onload = function (){
        var canvas = document.getElementById("canvas");
        var canvas1 = document.getElementById("canvas1");

        canvas.width = 500;
        canvas.height = 500;

        canvas1.width = 500;
        canvas1.height = 500;

        var context = canvas.getContext("2d");
        var context1 = canvas1.getContext("2d");

        draw_ChessBoard(context1);
        draw_Chess_All(context);
        // update_h2();//更换当前的出手的人

        canvas.onclick = function(e){
            // alert("e.clientX="+e.clientX+",e.clientY="+e.clientY);
            // alert("canvas.offsetLeft="+canvas.offsetLeft+",canvas.offsetTop="+canvas.offsetTop);
            if(desc_click[0]==-1){
                get_Chess(context,e.clientX - canvas.offsetLeft,e.clientY - canvas.offsetTop);
            }else{
                // update_h2();
                put_Chess(context,e.clientX - canvas.offsetLeft,e.clientY - canvas.offsetTop);
            }
        };
    }

    function put_Chess(context,x,y){
        var sub_x = 0,sub_y = 0, i, turn;
        if (x%100>80||x%100<20) {sub_x = 100*Math.round(x/100)};
        if (x%100>30&&x%100<70) {sub_x = x>100?(Math.floor(x/100)*100 + 50):50};
        if (y%100>80||y%100<20) {sub_y = 100*Math.round(y/100)};
        if (y%100>30&&y%100<70) {sub_y = y>100?(Math.floor(y/100)*100 + 50):50};

        for (i = 0;i < chess.length; i++) {
            if (chess[i][1] == desc_click[1] && chess[i][2] == desc_click[2]) {
                alert("move before:x="+chess[i][1]+",y="+chess[i][2]);
                chess[i][1] = sub_x;
                chess[i][2] = sub_y;
                alert("move after:x="+chess[i][1]+",y="+chess[i][2]);
                desc_click=[-1,-1,-1];
                break;
            }
        }
        repaint(context);
        whose = whose==0?1:0;
    }
    function get_Chess(context,x,y){
        var sub_x = 0,sub_y = 0, i, turn;
        if (x%100>80||x%100<20) {sub_x = 100*Math.round(x/100)};
        if (x%100>30&&x%100<70) {sub_x = x>100?(Math.floor(x/100)*100 + 50):50};
        if (y%100>80||y%100<20) {sub_y = 100*Math.round(y/100)};
        if (y%100>30&&y%100<70) {sub_y = y>100?(Math.floor(y/100)*100 + 50):50};
        // alert("sub_x="+sub_x+",sub_y="+sub_y);
        for (i = 0;i < chess.length; i++) {
            if (chess[i][1] == sub_x && chess[i][2] == sub_y) {
                turn = (whose==0)?"B":"W,K";
                // alert("whoseTurn:"+turn);
                if(turn.indexOf(chess[i][0])!= -1){
                    draw_check(context,sub_x,sub_y);
                    desc_click = [i,sub_x,sub_y];
                    return false;
                }else{
                    alert("it's not your turn! ");
                }

                // if(desc_click[3] == whose&&chess[i][4]!=whose){
                    // if(go(sub_x,sub_y,desc_click[4],true)){
                        // if(go(sub_x,sub_y,desc_click[4],true)){

                        // chess[desc_click[2]][1] = sub_x;//
                        // chess[desc_click[2]][2] = sub_y;//
                        // chess[i][3] = 0;
                        // whose = whose == 0?1:0;
                        // repaint(context);
                        // if(chess[i][5] == 5) {document.getElementById("canvas").onclick = null;}
                    // }
                // }
                // return false;
            }
        }

        // if(sub_x >= 50&&sub_x<=450&&sub_y>=50&&sub_y<=500){
            // if(go(sub_x,sub_y,desc_click[4])){
                chess[desc_click[2]][1] = sub_x;
                chess[desc_click[2]][2] = sub_y;
                repaint(context);
                desc_click = [0,0,0];
                whose = whose ==0?1:0;
            // }
        // }
    }

    function repaint(context){
        context.clearRect(0,0,500,500);
        draw_Chess_All(context);
    }

    function draw_ChessBoard(context){
        context.lineWidth = 2;
        context.clearRect(0,0,500,500);
        context.fillStyle = "#b3b37d";
        context.fillRect(0,0,500,500);
        context.beginPath();
        for(var i = 0; i <11; i++) {
            context.moveTo(i * 50, 0);
            context.lineTo(i * 50, 500);
            context.stroke();
            context.moveTo(0, i * 50);
            context.lineTo(500, i * 50);
            context.stroke();
        }
    }

    function draw_Chess_All(context){
        for (var i = 0;i < chess.length; i++) {
                draw_Chess_One_Piece(context,chess[i][0],chess[i][1],chess[i][2]);
        }
    }

    function draw_Chess_One_Piece(context, txt, x, y){
        var color;
        if(txt=='B'){
            color = "Black";
        }else if(txt=='W'){
            color = "White";
        }else if(txt=='K'){
            color = "Red";
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

    function draw_check(context,x,y){
        repaint(context);
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
    //吃子
    function go(x2,y2,txt,eat){
        //定义x1是点击的那个对象数组的第一个值 y1是第二个值
        var x1 = desc_click[0];
        var y1 = desc_click[1];
        //定义最大值和最小值
        var min_x = x1 > x2?x2:x1;
        var max_x = x1 > x2?x1:x2;
        var min_y = y1 > y2?y2:y1;
        var max_y = y1 > y2?y1:y2;
        //定义是否可以吃子
        var can_go = true;
        var num = 0;
        //判断是不是俩个将帅面对面
        if (is_face_to_face(x2)) {
            return false;
        }
        //下棋
        switch (txt){
            case 1:
                // 如果俩车不在同一条直线上
                if(x1!=x2&&y1!=y2){
                    //不能吃子
                    can_go = false;
                    break;
                }
                //开始遍历
                for(var i = 0;i < chess.length; i++){
                    //
                    if(chess[i][1] == x1 && chess[i][2] > min_y && chess[i][2] < max_y && chess[i][3] != 0){
                        can_go = false;
                    }
                    //
                    if(chess[i][2] == y1 && chess[i][1] > min_x && chess[i][1] < max_x && chess[i][3] != 0){
                        can_go = false;
                    }
                }
                break;

        }
        return can_go;
    }
    //判断这颗棋子是否存在
    function is_chess(x,y){
        //定义existe判断是否这颗棋子还活着默认是死了
        var existe = false;
        for(var i = 0;i<chess.length;i++){
            //如果某颗棋子的横纵坐标都有并且chess[i][3]任然等于1这个参数是true表示活着
            if(chess[i][1]==x && chess[i][2]==y && chess[i][3]==1){existe = true;};
        }
        return existe;
    }
    //这是一个判断俩个帅是否是面对面（有bug）
    function is_face_to_face(x){
        //定义四个参数红色的和黑色的x,y坐标
        var r_x = 0,r_y=0,b_x=0,b_y=0,num=0;
        //定义一个状态
        var state = false;
        //
        for(var i=0;i<chess.length;i++){
            //chess[i][5]值就是从1到7分别代表不同的棋子，例如1表示军7表示兵卒
            //5表示将帅
            if(chess[i][5]==5 && chess[i][4]==1){//如果将帅活着并且1表示红棋
                //获取到这个棋子的横纵坐标
                r_x=chess[i][1];
                r_y=chess[i][2];
            }
            if(chess[i][5]==5 && chess[i][4]==0){//如果将帅活着并且1表示黑棋
                //获取到当前棋子的横纵坐标
                b_x=chess[i][1];
                b_y=chess[i][2];
            }
        }
        //如果红色和黑色的横坐标相同时
        if(r_x == b_x){
            //遍历一遍chess数组
            for(var i = 0;i<chess.length;i++){
                //如果当前棋子的位置没变并且是红棋落子时
                if(chess[i][1] == r_x && chess[i][3] > 0){num++;}
            }
            //？？有问题
            if(num - 2 == 1 && x != r_x){state = true;}
        }
        return state;
    }

    function update_h2(){
        var h2 = document.getElementById("title");
        h2.innerHTML = whose == 0?"Black":"White";
        h2.style.color = whose == 0?"#000000":"#ff0000";
    }


</script>