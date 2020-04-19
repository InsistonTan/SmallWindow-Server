var UID="";
var Username="";
var URL="/api/searchUser";
//
$(document).ready(function(){
	checkLogin();
	search();
});
//检查登陆状态
function checkLogin()
{
	$.ajax({
			type:"post",
			url:"/api/getInfo",
			dataType:"JSON",
			success:function(res)
			{
				UID=res.UID;
				Username=res.Username;
				if(UID==null||UID=="null"||UID=="")
				{
					UID="";
					Username="";	
				}
				//alert(UID+" "+Username);
			},
			error:function(res)
			{
				alert("服务器错误");
			}
	});
}

//
function search()
{
	$.ajax({
		url:URL,
		type:"post",
		dataType:"JSON",
		success:function(res)
		{
			if(res==null||res=="")
			{
				var result="<div style='text-align:center;margin-top:10px;'><b>暂无搜索结果</b></div>";
				if(URL=="/api/searchUser")
					$("#showUser").html(result);
				if(URL=="/api/searchMessage")
					$("#showMessage").html(result);
			}
			//
			if(res!=""&&res!=null)
				if(URL=="/api/searchUser")
					showSearchUser(res);
				else if(URL=="/api/searchMessage")
					showMessage("search",UID,res);
		},
		error:function(res)
		{
			alert("search_html连接服务器失败！");
		}
	});
}

//
function showSearchUser(data)
{
	var len=data.length;
	$("#showUser").html("");
	for(var i=0;i<len;i++)
	{
		var user1="<div style='margin-top:10px;background-color: rgba(255,255,255,0.8);' class='rounded shadow_div'>"
	            +"<img src='../images/user2.png' alt='user' style='margin:4px;'>"
	              +"<b><a href='#' style='text-decoration: none;' onclick='visit(\""+UID+"\",\""+data[i].UID+"\")'>"+data[i].Username+"</a></b>"
	                 +"<button type='button' class='btn btn-outline-success btn-sm' style='width:50px;height:26px;font-size:11px;float:right;margin:6px;' onclick='follow(\""+UID+"\",\""+data[i].UID+"\")'>"
	                   +"关注</button></div>";
	    var user2="<div style='margin-top:10px;background-color: rgba(255,255,255,0.8);' class='rounded shadow_div'>"
	            +"<img src='../images/user2.png' alt='user' style='margin:4px;'>"
	              +"<b><a href='#' style='text-decoration: none;' onclick='visit(\""+UID+"\",\""+data[i].UID+"\")'>"+data[i].Username+"</a></b>"
	                 +"<button type='button' class='btn btn-outline-success btn-sm' style='width:60px;height:26px;font-size:11px;float:right;margin:6px;' disabled>"
	                   +"已关注</button></div>";
  		if(UID==data[i].UID)
	    	$("#showUser").append(user2);
	    else if(data[i].isFollowed==0)
	    	$("#showUser").append(user1);
	    else if(data[i].isFollowed==1)
	    	$("#showUser").append(user2);

	    
	}
}

//
function searchMessage()
{
	if(URL=="/api/searchMessage")
		return;

	URL="/api/searchMessage";
	search();
}

/*//
function showSearchMessage(data)
{
	var len=data.length;
	//清空原来的内容
	$("#showMessage").html("");
	//循环展示
	for(var i=0;i<len;i++)
	{
		var temp="<div class='rounded' style='width:100%;background-color:rgba(255,255,255,0.7);margin-top:10px;'>"
		            +"<div id='Username' style='font-size:16px;margin-top:10px;margin-left:10px;'>"
		                +"<img src='../images/user2.png' alt='account' style='width: 18px;height: 18px;'>"
		                +"<a href='#' style='color:rgb(205,133,63);text-decoration:none;margin-left:5px;'>"
		                	+data[i].user
		            	+"</a></div>"
		            +"<div id='time' style='font-size:10px;margin-left:10px;'>"
		                +data[i].time
		            +"</div>"
		            +"<div id='content' style='margin:10px;font-size:14px;padding-bottom:10px;'>"
		                +data[i].content
		            +"</div></div>";
		$("#showMessage").append(temp);
	}
}*/