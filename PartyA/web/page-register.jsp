<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style type="text/css">
        #ant {
            background-color: #b5c9cc;
            text-align: center;
            width: 500px;
            height: 400px;
            margin: auto;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
        }
        #title{
            text-align: center;
        }
        #body{
            text-align: center;
        }
    </style>
		<title >Register</title>
	</head>
	<body>
						<table>
							<div id="title"><h2 >Register</h2></div>
							<div id="ant">
								<form action="addUser" id="fm" method="post">
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<div >
										<label style="font-size: 20px;">Username:&nbsp;&nbsp;&nbsp;&nbsp;</label>
										<input style="font-size: 10px;" name="uname" type="text"  title="username"/>
									</div>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
											<div >
												<label style="font-size: 20px;">Password:&nbsp;&nbsp;&nbsp;&nbsp;</label>
												<input style="font-size: 10px;" name="upass" type="password" title="password"/>
											</div>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
									<div >
										<label style="font-size: 20px;">Email:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
										<input style="font-size: 10px;" name="uemail" type="email" title="email_address"/>
									</div>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
										<div >
											<a href="javascript:commit()" type="submit" style="font-size: 30px;"><button>register</button></a>
										</div>
								</form>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                I have an account!
								<a href="login.jsp"><small >login</small></a>
							</div>
                        </table>
		<script >
            function commit(){
      // var myform = document.getElementById("fm");
	   //  for(var i=0;i<myform.length;i++){
	   //        if(myform.elements[i].value==""){
	   //              alert(myform.elements[i].title+"cannot null");
	   //              myform.elements[i].focus();
	   //              return ;
	   //        }
	   //  }
	   // console.log($("#fm").serialize())
      // $.ajax({
      //     url:'addUser',
      //     type:'post',
      //     dataType:'json',
      //     data:$("#fm").serialize(),
      //     complete:function(){
      //         console.log(1)
		//   },
      //     error:function(){
      //         console.log(2);
      //     },
      //     success:function(){
      //         console.log(3);
      //     }
      // });
      $('#fm').form('submit',{
		  url:'addUser',
		  success:function(res){
		      console.log(res);
			 window.location.href="login.jsp";
		  }
      });
  }
  </script>
</body>
</html>