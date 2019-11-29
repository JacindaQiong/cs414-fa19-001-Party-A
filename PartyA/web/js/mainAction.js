/**
 * 动态添加菜单的功能
 */
//页面加载完，就执行的代码
$(document).ready(function(){
	 //1.利用ajax获得主菜单的json串
	    $.ajax({
	    	type:"post",
	    	dataType:"json",
	    	url:"menu",
	    	success:function(data){
	    		//遍历json数组
	    		$.each(data,function(i,dt){
	    			//给菜单添加分类
	    			$("#leftMenu").accordion("add",{
	    				title:dt.name,
	    				selected:false,
	    				content:'<div style="padding:10px"><ul name="'+dt.name+'"></ul></div>'
	    			});	    			
	    		});
	    	}
	    });
	
	 //2.菜单项设置选择时的动作--》点击主菜单项时，动态加载子菜单，json串
	    $('#leftMenu').accordion({
	    	onSelect:function(title,index){
	    		//展开，需要加载子菜单
	    		//1.找到ul元素
	    		$("ul[name='"+title+"']").tree({
	    			url:'menu',
	    			queryParams:{
	    				name:title
	    			},
	    			animate:true,
	    			lines:false,
	    			onClick:function(node){
	    				//在center添加相应的选项卡
	    				//找到对应title选项卡
	    				var temp =$("#myTabs").tabs("getTab",node.text);
	    				if(temp==null){
		    				$("#myTabs").tabs("add",{
		    					title:node.text,
		    					closable:true,
		    					content:"<iframe frameborder=0 id='centerContent' height='100%' width='100%' src='"+node.path+"'></iframe>"
		    				});
	    				}else{
	    					$("#myTabs").tabs("select",node.text);
	    				}	    				
	    			}
	    		});
	    	}
	    });	
});