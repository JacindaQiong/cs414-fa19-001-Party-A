<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Basic -->

		<title>Register</title>

	</head>

	<body>
		<!-- Start: Content -->
		<div class="container-fluid content">
			<div class="row">
				<!-- Main Page -->
				<div class="body-register">
					<div class="center-register">
						<a href="#" class="logo pull-left hidden-xs">
							<img src="" height="45" alt="Hnenfatafl" />
						</a>

						<div class="panel panel-register">
							<div class="panel-title-register text-right">
								<h2 class="title text-uppercase" ><i class="fa fa-user"></i> Register</h2>
							</div>
							<div class="panel-body">
								<form action="addUser" id="fm" method="post">
									<div class="form-group">
										<label>username</label>
										<input name="uname" type="text" class="form-control" title="username"/>
									</div>
							       <div class="form-group">
										<div class="row">
											<div class="col-sm-6">
												<label>password</label>
												<input name="upass" type="password" class="form-control"title="password"/>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label>email</label>
										<input name="uemail" type="email" class="form-control" title="email_address"/>
									</div>
									<div class="row">
										<div class="col-sm-8">
											<div class="checkbox-custom checkbox-default">
												<input id="AgreeTerms" name="agreeterms" checked="checked"  type="checkbox" title="agreement"/>
												<label for="AgreeTerms">I agree <a href="#"><small>user agreement</small></a></label>
											</div>
										</div>
										<div class="col-sm-4 text-right">
											<a href="javascript:commit()" type="submit" class=""><button>register </button></a>
											<!--<button href="index.html" type="submit" class="btn btn-primary btn-block btn-lg visible-xs bk-margin-top-10">注</button>-->
										</div>
									</div>
									<p class="text-center">I have a account!<a href="login.jsp"><button>login</button></a>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- End Main Page -->
		
				<!-- Usage -->
				
				<!-- End Usage -->					
		
			</div>
		</div><!--/container-->
		 <script >
  
  function commit(){
	  var myform = document.getElementById("fm");   //获得form表单对象
	    for(var i=0;i<myform.length;i++){               //循环form表单
	          if(myform.elements[i].value==""){          //判断每一个元素是否为空
	                alert(myform.elements[i].title+"cannot null");
	                myform.elements[i].focus();             //元素获得焦点
	                return ;
	          }
	    }
	    if ($('#AgreeTerms').attr('checked')) {
	    	alert("agreement cannot null!");
	    	return;
	    }
	  $('#fm').form('submit',{
		  url:'addUser',
		  success:function(){
			 window.location.href="login.jsp";
		  }
	  });
  }  
  </script>
</body>
</html>