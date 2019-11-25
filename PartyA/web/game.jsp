<!DOCTYPE html>
<html lang="zh-CN">
<meta charset="utf-8">
<head><title>chess</title></head>
<body>
<canvas id="myCanvas" width="440" height="440" style="border:3px solid black;">
partyA
</canvas>  <br/>
<button id="reset" onclick="controller.init(ctx)">reset</button>
</body>
<script>


    var controller = {
        turn:0,
        round:true,
        color:"black",
        whiteTable:new Array(),
        blackTable:new Array(),
        row:0,
        col:0,
        over:false,
        trans:function() {
            controller.turn = controller.turn+1;
            // this.round = !this.round;
            // if (!this.round) {
            //     this.blackTable[this.row][this.col] = 1;
            //     this.ifWin(this.blackTable)
            //     this.color = "white";
            // }
            // else {
            //     this.whiteTable[this.row][this.col] = 1;
            //     this.ifWin(this.whiteTable)
            //     this.color = "black";
            // }
        },
        ifWin:function(table) {
            var arr1 = new Array();
            var arr2 = new Array();
            var arr3 = new Array();
            var arr4 = new Array();
            var n = 0;
            for(x = 0; x<= lineNums; x++) {
                for(y = 0; y <= lineNums; y++)
                {
                    var x1 = this.row - n;
                    var x2 = this.row + n;
                    var y1 = this.col - n;
                    var y2 = this.col + n;
                    if(y == this.col) {
                        arr1[x] = table[x][y];
                    }
                    if(x == this.row) {
                        arr2[y] = table[x][y];
                    }
                }
                if(this.inBounds(x1) && this.inBounds(y2)) {
                    arr3[x1] = table[x1][y2];
                }
                if(this.inBounds(x1) && this.inBounds(y1)) {
                    arr4[x1] = table[x1][y1];
                }
                if(this.inBounds(x2) && this.inBounds(y1)) {
                    arr3[x2] = table[x2][y1];
                }
                if(this.inBounds(x2) && this.inBounds(y2)) {
                    arr4[x2] = table[x2][y2];
                }
                n = n + 1;
            }
            this.getSum(arr1, this.row);
            this.getSum(arr2, this.col);
            this.getSum(arr3, this.row);
            this.getSum(arr4, this.row);
        },
        inBounds:function(i) {
            if(i>=0 && i<=15){
                return true;
            }
            else{
                return false;
            }
        },
        getSum:function(array, pos) {
            num = 5;
            posr = pos + 1;
            while(num > 0){
                if(array[pos]>0  && this.inBounds(pos)) {
                    num = num - 1;
                    pos = pos - 1;
                }
                else{
                    break;
                }
            }
            while(num > 0){
                if(array[posr]>0 && this.inBounds(pos)) {
                    num  = num - 1;
                    posr = posr + 1;
                }
                else {
                    break;
                }
            }
            if(num == 0) {
                this.over = true;
                this.gameOver();
            }
        },
        exist:function(x, y) {
            this.row = x / ratio;
            this.col = y / ratio;
            var nums = this.whiteTable[this.row][this.col] + this.blackTable[this.row][this.col];
            if( nums > 0) {
                return true;
            }
            else{
                return false;
            }
        },
        gameOver:function() {
            ctx.font="30px Arial";
            ctx.fillStyle = "#ff7274";
            if(this.round) {
                ctx.fillText("白棋胜利",240,240);
            }
            else {
                ctx.fillText("黑棋胜利",240,240);
            }
        },
        init:function() {
            this.round = true;
            this.color = "black";
            this.over  = false;
            this.drawBoard();
            this.drawCross();
            this.initPiece();
            for(i = 0; i<= lineNums; i++) {
                this.whiteTable[i]=new Array();
                this.blackTable[i]=new Array();
                for(n = 0; n <= lineNums; n++) {
                    this.whiteTable[i][n]=0;
                    this.blackTable[i][n]=0;
                }
            }
        },
        drawCross:function() {
            ctx.lineWidth="2";
            ctx.strokeStyle="rgba(4,2,3,0.97)";
            ctx.moveTo(0, 0);
            ctx.lineTo(ratio, ratio);
            ctx.stroke();
            ctx.moveTo(ratio, 0);
            ctx.lineTo(0, ratio);
            ctx.stroke();

            ctx.moveTo(10*ratio, 0);
            ctx.lineTo(11*ratio, ratio);
            ctx.stroke();
            ctx.moveTo(11*ratio, 0);
            ctx.lineTo(10*ratio, ratio);
            ctx.stroke();

            ctx.moveTo(0, 10*ratio);
            ctx.lineTo(ratio, 11*ratio);
            ctx.stroke();
            ctx.moveTo(ratio, 10*ratio);
            ctx.lineTo(0, 11*ratio);
            ctx.stroke();

            ctx.moveTo(10*ratio, 10*ratio);
            ctx.lineTo(11*ratio, 11*ratio);
            ctx.stroke();
            ctx.moveTo(11*ratio, 10*ratio);
            ctx.lineTo(10*ratio, 11*ratio);
            ctx.stroke();

            ctx.moveTo(5*ratio, 5*ratio);
            ctx.lineTo(6*ratio, 6*ratio);
            ctx.stroke();
            ctx.moveTo(6*ratio, 5*ratio);
            ctx.lineTo(5*ratio, 6*ratio);
            ctx.stroke();

        },
        drawBoard:function() {
            ctx.lineWidth="2";
            ctx.beginPath();
            ctx.clearRect(0,0,width,width);
            ctx.fillStyle = "#4fff29";
            ctx.fillRect(0,0,width,width);
            for(var i = 1; i < (lineNums - 1); i++) {
                ctx.moveTo(i * ratio, 0);
                ctx.lineTo(i * ratio, width);
                ctx.stroke();
                ctx.moveTo(0, i * ratio);
                ctx.lineTo(width, i * ratio);
                ctx.stroke();
            }
        },
        initPiece:function() {
            // initial black pieces
            ctx.beginPath();
            ctx.arc(0.5*ratio, 3.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "black";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(0.5*ratio, 4.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "black";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(0.5*ratio, 5.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "black";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(0.5*ratio, 6.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "black";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(0.5*ratio, 7.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "black";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.fillStyle = "black";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(ratio+ratio/2, (5*ratio)+ratio/2, ratio/2, 0, 2*Math.PI);
            ctx.fillStyle = "black";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.fillStyle = "black";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(10.5*ratio, 3.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "black";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(10.5*ratio, 4.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "black";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(10.5*ratio, 5.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "black";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(10.5*ratio, 6.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "black";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(10.5*ratio, 7.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "black";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(9.5*ratio, 5.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "black";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(3.5*ratio, 0.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "black";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(4.5*ratio, 0.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "black";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(5.5*ratio, 0.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "black";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(6.5*ratio, 0.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "black";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(7.5*ratio, 0.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "black";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(5.5*ratio, 1.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "black";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(3.5*ratio, 10.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "black";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(4.5*ratio, 10.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "black";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(5.5*ratio, 10.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "black";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(6.5*ratio, 10.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "black";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(7.5*ratio, 10.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "black";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(5.5*ratio, 9.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "black";
            ctx.fill();
            ctx.stroke();

            // initial white pieces
            ctx.beginPath();
            ctx.arc(5.5*ratio, 3.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "white";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(5.5*ratio, 7.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "white";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(4.5*ratio, 4.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "white";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(5.5*ratio, 4.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "white";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(6.5*ratio, 4.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "white";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.fillStyle = "white";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(4.5*ratio, 6.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "white";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(5.5*ratio, 6.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "white";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(6.5*ratio, 6.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "white";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(3.5*ratio, 5.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "white";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(4.5*ratio, 5.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "white";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(6.5*ratio, 5.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "white";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(7.5*ratio, 5.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "white";
            ctx.fill();
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(5.5*ratio, 5.5*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = "#ff7274";
            ctx.fill();
            ctx.stroke();

        },
        clearPiece:function(posX, posY) {
            ctx.lineWidth="2";
            ctx.beginPath();
            ctx.clearRect(posX,posY,ratio,ratio);
            ctx.fillStyle = "#4fff29";
            ctx.fillRect(posX,posY,ratio,ratio);

            ctx.moveTo(posX,posY);
            ctx.lineTo(posX+ratio,posY);
            ctx.stroke();
            ctx.moveTo(posX,posY);
            ctx.lineTo(posX,posY+ratio);
            ctx.stroke();

            ctx.moveTo(posX+ratio,posY+ratio);
            ctx.lineTo(posX+ratio,posY);
            ctx.stroke();
            ctx.moveTo(posX+ratio,posY+ratio);
            ctx.lineTo(posX+ratio,posY);
            ctx.stroke();

        },
        placePiece:function(posX, posY, whoseTurn) {
            ctx.beginPath();
            ctx.arc((posX+0.5)*ratio, (posY+0.5)*ratio, 0.5*ratio, 0, 2*Math.PI);
            ctx.fillStyle = whoseTurn%2==0?"black":"white";
            ctx.fill();
            ctx.stroke();

        }
    };



    //获取点击位置
    function getMousePos(canvas, evt) {
        var rect = canvas.getBoundingClientRect();
        return {
            x: evt.clientX - rect.left * (canvas.width / rect.width),
            y: evt.clientY - rect.top * (canvas.height / rect.height)
        }
    }

    function getNode(pos) {
        return ((pos / ratio).toFixed()) * ratio;
    }

    var canvas = document.getElementById("myCanvas");
    if (canvas.getContext) {
        console.log('your browser supports Canvas!');
    } else {
        console.log('your browser supports Canvas!');
    }
    // get a CanvasRenderingContext2D
    var ctx = canvas.getContext("2d");
    var lineNums = 12;
    var ratio = 40;
    var width = (lineNums - 1) * ratio;

    controller.init();

    // canvas.addEventListener("dblclick", function (evt) {
    //     var mousePos = getMousePos(canvas, evt);
    //     mousePos.x = getNode(mousePos.x);
    //     mousePos.y = getNode(mousePos.y);
    //     alert(" move from origin: x="+mousePos.x+",y="+mousePos.y);
    //     var exist = controller.exist(mousePos.x, mousePos.y);
    //     if (!exist && !controller.over) {
    //         controller.clearPiece(mousePos.x, mousePos.y);
    //         // controller.trans();
    //         alert(this.turn);
    //     }
    // }, false);

    canvas.addEventListener("click", function (evt) {
        var mousePos = getMousePos(canvas, evt);
        mousePos.x = getNode(mousePos.x);
        mousePos.y = getNode(mousePos.y);
        var exist = controller.exist(mousePos.x, mousePos.y);
        if (exist && !controller.over) {
            alert(" move from : x="+mousePos.x+",y="+mousePos.y);
            controller.clearPiece(mousePos.x, mousePos.y);
            alert(controller.turn);

        }
        if (!exist && !controller.over) {
            alert(" move to : x="+mousePos.x+",y="+mousePos.y);
            controller.placePiece(mousePos.x, mousePos.y,controller.turn);
            controller.trans();
            alert(controller.turn);

        }
    }, false);
</script>
</html>