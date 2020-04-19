
//关注
function follow(myUID,targetUID)
{
	//alert(myUID+" "+targetUID);
	if(myUID==""||myUID==null)
	{
		alert("你还未登录，请先登录");
		location.href="/weibo/index.html";
		return;
	}
	//
	var ajaxData={"myUID":myUID,"targetUID":targetUID};
	$.ajax({
		url:"/api/addFollow",
		type:"post",
		contentType:"application/json;charset=UTF-8",
		data:JSON.stringify(ajaxData),
		dataType:"text",
		success:function(res)
		{
			if(res=="success")
				location.reload(true);
			else if(res=="failed")
				alert("关注失败");
			else 
				alert("未知错误");
		},
		error:function(res)
		{
			alert("follow连接服务器失败！");
		}
	});
}

//访问他人主页
function visit(myUID,visitUID)
{
	if(myUID==visitUID)
	{
		location.href="/html/seeMyHome.html";
		return ;
	}
	//
	var ajaxData={"visitUID":visitUID};
	$.ajax({
		type:"post",
		url:"/api/visitOtherHome",
		data:ajaxData,
		//contentType:"application/json;charset=UTF-8",
		dataType:"text",
		success:function(res)
		{
			if(res=="success")
				location.href="/html/visit.html";
			else 
				alert("访问失败");
		},
		error:function(res)
		{
			alert("visit连接服务器失败！");
		}
	});
}
