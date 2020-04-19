var UID="";
var Username="";
var messageIndex=0;
var followNum=0;
var fanNUm=0;
var messageNum=0;
//
$(document).ready(function(){
	checkLogin();
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
					alert("你还未登录");
					location.replace("/index.html");
				}
				else
				{	
					followNum=res.followNum;
					fanNum=res.fanNum;
					messageNum=res.messageNum;
					$("#username").html(Username);
					$("#t_followNum").html(followNum);
					$("#t_fanNum").html(fanNum);
					$("#t_messageNum").html(messageNum);
	
					getFollowMessage();
				}
			},
			error:function(res)
			{
				alert("服务器错误");
			}
	});
}

/*//获取关注数，粉丝数，贴子数
function getMyInfo()
{
	$.ajax({
			type:"post",
			url:"/weibo/Follow/getFollowInfo",
			contentType:"application/json;charset=UTF-8",
			data:JSON.stringify({"myUID":UID}),
			dataType:"JSON",
			success:function(res)
			{
				
			},
			error:function(res)
			{
				alert("getMyInfo服务器错误");
			}
	});
}*/

//获取关注的用户发的帖子
function getFollowMessage()
{
	/*var start=messageIndex+1;
	var end=messageIndex+9;
	var ajaxData={"start":start,"end":end};*/
	$.ajax({
			type:"post",
			url:"/api/getFollowMessage",
			dataType:"JSON",
			success:function(res)
			{
				//alert(res);
				if(res!="")
					showMessage("home",UID,res);
				else if(res==""||res==null||res=="null")
					$("#MessageBody").append("<div style='text-align:center;'><b>暂无数据</b></div>");
			},
			error:function(res)
			{
				alert("连接服务器失败！");
			}
	});
}

//上传帖子
function submitMessage()
{
	var inputText=$("#inputMessage").val();
	if(inputText=="")
	{
		alert("内容不能为空");
		return;
	}
	//alert(UID);
	var ajaxData={"uid":UID,"user":Username,"content":inputText};
	//
	$.ajax({
			type:"post",
			url:"/api/insertMessage",
			contentType:"application/json;charset=UTF-8",
			data:JSON.stringify(ajaxData),
			dataType:"text",
			success:function(res)
			{
				if(res=="success")
				{
					alert("发表成功");
					$("#inputMessage").val("");
					getFollowMessage();
				}
				else 
					alert("发表失败");
			},
			error:function(res)
			{
				alert("连接服务器失败！");
			}
	});
}