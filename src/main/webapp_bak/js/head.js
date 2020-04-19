var UID="";
var Username="";
//
$(document).ready(function(){
	getInfo();
	
});
//
function getInfo()
{
	$.ajax({
		url:"/api/getInfo",
		type:"post",
		dataType:"JSON",
		success:function(res)
		{
			UID=res.UID;
			Username=res.Username;
			if(UID!=null&&UID!="null"&&UID!="")
			{
				var temp="<a class='nav-link font_shadow' href='#' style='color:rgb(205,133,63);' onclick='seeMyHome()'>"+Username+"</a>";
				var temp2="<a class='nav-link font_shadow' href='#' onclick='changeAccount()' style='margin-left:20px;font-size:15px;'>"
						  +"安全退出</a>"
				$("#loginNav").html(temp);
				$("#changeNav").html(temp2);
				$("#registerNav").html("");
			}
			if(UID=="null"||UID==null)
				UID="";
			if(Username=="null"||UID==null)
				Username="";
		},
		error:function(res)
		{
			alert("获取信息失败head");
		}
	});
}
//
function seeMyHome()
{
	parent.location.href="/html/seeMyHome.html";
}
//
function goHome()
{
	if(UID=="")
		parent.location.href="index.html";
	else
		parent.location.href="/html/home.html";
}
//
function changeAccount()
{
    $.ajax({
        url:"/api/clearInfo",
        type:"post"
    });

    parent.location.replace("index.html");
}   
//
function searching()
{
	var temp=$("#searchInput").val();
	if(temp=="")
	{
		alert("输入内容为空");
		return;
	}
	$.ajax({
		url:"/api/search",
		type:"post",
		contentType:"application/json;charset=UTF-8",
		data:temp,
		dataType:"text",
		success:function(res)
		{
			if(res=="success")
				parent.location.href="/html/search.html";
			else if(res=="failed")
				alert("服务器接收失败");
		},
		error:function(res)
		{
			alert("search连接服务器失败！");
		}
	});
}    