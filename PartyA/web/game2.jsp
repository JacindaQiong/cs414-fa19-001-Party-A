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
    //开始画象棋布局
    //定义一个棋盘所有的棋子，这是一个二维数组
    var chess = [
        //['名称(txt)','横坐标x','纵坐标y','哪边下子','判断是红棋还是黑棋','判断是什么棋']
        //chess.length = 32，一共32颗棋子
        ['B',25+150,25,0,0,0],['B',25+200,25,0,0,0],['B',25+250,25,0,0,0],['B',25+300,25,0,0,0],['B',25+350,25,0,0,0],['B',25+250,25+50,0,0,0],
        ['B',25+150,25+550,0,0,0],['B',25+200,25+550,0,0,0],['B',25+250,25+550,0,0,0],['B',25+300,25+550,0,0,0],['B',25+350,25+550,0,0,0],['B',25+250,25+500,0,0,0],
        ['B',25+0,25+150,0,0,0],['B',25+0,25+200,0,0,0],['B',25+0,25+250,0,0,0],['B',25+0,25+300,0,0,0],['B',25+0,25+350,0,0,0],['B',25+50,25+250,0,0,0],
        ['B',25+550,25+150,0,0,0],['B',25+550,25+200,0,0,0],['B',25+550,25+250,0,0,0],['B',25+550,25+300,0,0,0],['B',25+550,25+350,0,0,0],['B',25+500,25+250,0,0,0],

        ['W',25+250,25+150,0,1,1],['W',25+250,25+350,0,1,1],
        ['W',25+200,25+200,0,1,1],['W',25+250,25+200,0,1,1],['W',25+300,25+200,0,1,1],['W',25+200,25+300,0,1,1],['W',25+250,25+300,0,1,1],['W',25+300,25+300,0,1,1],
        ['W',25+150,25+250,0,1,1],['W',25+200,25+250,0,1,1],['W',25+300,25+250,0,1,1],['W',25+350,25+250,0,1,1],

        ['K',25+250,25+250,0,1,2]
    ];

    //建一个数组来存放我们点击的那个棋子的所有参数
    //参数：x坐标，y坐标，哪边下子，判断是红棋还是黑棋，判断是什么棋
    var desc_click = [0,0,0,-1,0];
    //0 black; 1 white
    var whose = 0;

    window.onload = function (){
        var canvas = document.getElementById("canvas");
        var canvas1 = document.getElementById("canvas1");

        canvas.width = 600;
        canvas.height = 600;

        canvas1.width = 600;
        canvas1.height = 600;

        var context = canvas.getContext("2d");
        var context1 = canvas1.getContext("2d");

        draw_ChessBoard(context1);
        draw_Chess_All(context);
        update_h2();//更换当前的出手的人

        canvas.onclick = function(e){
            //三个参数 第一个是当前的context，第二个是点击的棋子到画板左边的距离，第三个是当前点击的棋子到画板上边的距离
            get_Chess(context,e.clientX - canvas.offsetLeft,e.clientY - canvas.offsetTop);
            update_h2();
        };
    }

    //获取棋子 三个参数 第一个是在canvas上画 第二个是坐标x第三个是坐标y
    function get_Chess(context,x,y){
        //定义俩个变量
        var sub_x = 0,sub_y = 0;
        if (x<30||y<30||x>470||y>570) {return false};
        //这里就是一个四舍五入和向下取整，以便棋子能在棋盘上按格走
        if (x%100>80||x%100<20) {sub_x = 100*Math.round(x/100)};
        if (x%100>30&&x%100<70) {sub_x = x>100?(Math.floor(x/100)*100 + 50):50};
        if (y%100>80||y%100<20) {sub_y = 100*Math.round(y/100)};
        if (y%100>30&&y%100<70) {sub_y = y>100?(Math.floor(y/100)*100 + 50):50};

        if(sub_x > 0 && sub_y > 0){
            for (var i = 0;i < chess.length; i++) {
                if (chess[i][1] == sub_x && chess[i][2] == sub_y && chess[i][3] > 0) {
                    if (chess[i][4] == whose) {//可见第5个参数是判断当前该哪边出手的数
                        draw_check(context,sub_x,sub_y);//画棋子
                        //参数：x坐标,y坐标，数组里的第几个，数组的第五个参数即该哪边下，第六个参数
                        desc_click = [sub_x,sub_y,i,chess[i][4],chess[i][5]];
                        return false;
                    }
                    if(desc_click[3] == whose&&chess[i][4]!=whose){
                        //这一步判断是否成功换人下子
                        if(go(sub_x,sub_y,desc_click[4],true)){
                            chess[desc_click[2]][1] = sub_x;//
                            chess[desc_click[2]][2] = sub_y;//
                            chess[i][3] = 0;//棋子第三个参数赋值为0
                            whose = whose == 0?1:0;//是不是"楚"如果是就换，如果不是就是"楚"
                            repaint(context);//调用函数重新画整个棋盘
                            if(chess[i][5] == 5) {document.getElementById("canvas").onclick = null;}//
                        }
                    }
                    return false;
                }
            }
        }
        if(sub_x >= 50&&sub_x<=450&&sub_y>=50&&sub_y<=550&&desc_click[3] == whose){
            if(go(sub_x,sub_y,desc_click[4])){
                chess[desc_click[2]][1] = sub_x;
                chess[desc_click[2]][2] = sub_y;
                repaint(context);
                desc_click = [0,0,0,-1,0];
                whose = whose ==0?1:0;
            }
        }
    }

    function repaint(context){
        context.clearRect(0,0,550,550);
        draw_Chess_All(context);
    }

    function draw_ChessBoard(context){
        context.lineWidth = 2;
        context.clearRect(25,25,600,600);
        context.fillStyle = "#b3b37d";
        context.fillRect(25,25,600,600);
        context.beginPath();
        for(var i = 0; i <12; i++) {
            context.moveTo(25+i * 50, 25);
            context.lineTo(25+i * 50, 25+550);
            context.stroke();
            context.moveTo(25, 25+i * 50);
            context.lineTo(25+550, 25+i * 50);
            context.stroke();
        }

        // context.save();
        // context.font = "bold 40px KaiTi_GB2312";
        // context.translate(400,255);
        // context.rotate(90*Math.PI/180);
        // context.fillText("汉",0,40);
        // context.restore();

    }
    //
    function draw_Chess_All(context){
        for (var i = 0;i < chess.length; i++) {
            // if (chess[i][3]>0) {
                draw_Chess_One(context,chess[i][0],chess[i][1],chess[i][2],chess[i][4]);
            // }
        }
    }

    //绘制棋子 参数：context即当前的画板 x坐标 y坐标 填充的文字 判断是红棋还是黑棋
    function draw_Chess_One(context,txt,x,y,team){
        //team=0 black; team=1 white
        var color = team>0?"#ff0000":"#000000";
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
    //绘制选中某个棋子的那个框
    function draw_check(context,x,y){
        //重新画整个棋盘
        repaint(context);
        //开始画左上角
        context.beginPath();
        context.moveTo(x-23,y-10);
        context.lineTo(x-23,y-23);
        context.lineTo(x-10,y-23);
        //开始画右上角
        context.moveTo(x+10,y-23);
        context.lineTo(x+23,y-23);
        context.lineTo(x+23,y-10);
        //开始画左下角
        context.moveTo(x-23,y+10);
        context.lineTo(x-23,y+23);
        context.lineTo(x-10,y+23);
        //开始画右下角
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
            case 2://如果是马怎么走
                can_go = false;
                if(x1-50==x2&&y1-100==y2&&!is_chess(x1,y1-50)){can_go = true};
                if(x1+50==x2&&y1-100==y2&&!is_chess(x1,y1-50)){can_go = true};
                if(x1-50==x2&&y1+100==y2&&!is_chess(x1,y1+50)){can_go = true};
                if(x1+50==x2&&y1+100==y2&&!is_chess(x1,y1+50)){can_go = true};
                if(x1-100==x2&&y1-50==y2&&!is_chess(x1-50,y1)){can_go = true};
                if(x1-100==x2&&y1+50==y2&&!is_chess(x1-50,y1)){can_go = true};
                if(x1+100==x2&&y1-50==y2&&!is_chess(x1+50,y1)){can_go = true};
                if(x1+100==x2&&y1+50==y2&&!is_chess(x1+50,y1)){can_go = true};
                break;
            case 3://象怎么走
                can_go = false;
                if(whose == 1 && y2 > 250) {break};
                if(whose == 0 && y2 < 300) {break};
                if(x1-100==x2&&y1-100==y2&&!is_chess(x1-50,y1-50)) {can_go=true};
                if(x1+100==x2&&y1-100==y2&&!is_chess(x1+50,y1-50)) {can_go=true};
                if(x1-100==x2&&y1+100==y2&&!is_chess(x1-50,y1+50)) {can_go=true};
                if(x1+100==x2&&y1+100==y2&&!is_chess(x1+50,y1+50)) {can_go=true};
                break;
            case 4://士怎么走
                can_go= false;
                if(x2<200||x2>300){break};
                if(whose == 1&&y2>150){break};
                if(x1+50==x2&&y1+50==y2){can_go = true};
                if(x1+50==x2&&y1-50==y2){can_go = true};
                if(x1-50==x2&&y1+50==y2){can_go = true};
                if(x1-50==x2&&y1-50==y2){can_go = true};
                break;
            case 5://帅怎么走
                can_go = false;
                if(x2<200||x2>300){break};
                //红棋的帅
                if(whose == 1&&y2>150){break};
                //黑棋的将
                if(whose==0&&y2<400){break};
                //判断怎么能走
                if(x1+50==x2&&y1==y2){can_go=true};
                if(x1-50==x2&&y1==y2){can_go=true};
                if(x1==x2&&y1+50==y2){can_go=true};
                if(x1==x2&&y1-50==y2){can_go=true};
                break;
            case 6://炮怎么走
                //不能原地走
                if(x1!=x2&&y1!=y2){
                    can_go = false;
                    break;
                }
                //如果吃子
                if(eat){
                    for(var i = 0;i < chess.length; i++){
                        if(chess[i][1]==x1&&chess[i][2]>min_y&&chess[i][2]<max_y&&chess[i][3]!=0){num++};
                        if(chess[i][2]==y1&&chess[i][1]>min_x&&chess[i][1]<max_x&&chess[i][3]!=0){num++};
                    }
                    if(num!=1){can_go = false};
                    break;
                }
                //
                for(var i=0;i<chess.length;i++){
                    if(chess[i][1]==x1&&chess[i][2]>min_y&&chess[i][2]<max_y&&chess[i][3]!=0){can_go = false};
                    if(chess[i][2]==y1&&chess[i][1]>min_x&&chess[i][1]<max_x&&chess[i][3]!=0){can_go = false};
                }
                break;
            case 7://兵怎么走
                if(whose==1&&y1>y2){can_go = false};
                if(whose==0&&y1<y2){can_go = false};
                if(whose==1&&y1<=250&&x1!=x2){can_go = false};
                if(whose==0&&y1>=300&&x1!=x2){can_go = false};
                if(max_x-min_x+max_y-min_y>50){can_go = false};
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