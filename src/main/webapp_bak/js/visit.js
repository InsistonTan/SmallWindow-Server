var Username="";
var UID="";
//
var v_Username="";
var v_UID="";
var v_sex="未设置";
var v_age="未设置";
var v_introduce="这个人很懒，什么都没留下...";
var v_followNum=0;
var v_fanNUm=0;
var v_messageNum=0;
//
$(document).ready(function(){
	checkLogin();
	getVisitInfo();
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
				if(UID==null||UID=="null"||UID=="")
				{
					UID="";
				}
				else
				{	
					Username=res.Username;
				}
			},
			error:function(res)
			{
				alert("服务器错误");
			}
	});
}

//
//获取对方的信息
function getVisitInfo()
{
	$.ajax({
		type:"post",
		url:"/api/getVisitUserInfo",
		dataType:"JSON",
		success:function(res)
		{
			if(res!=null)
			{
				v_UID=res.UID;
				v_Username=res.Username;
				if(res.age!=""&&res.age!=null)
					v_age=res.age;
				if(res.sex!=""&&res.sex!=null)
					v_sex=res.sex;
				if(res.introduce!=""&&res.introduce!=null)
					v_introduce=res.introduce;
				v_followNum=res.followNum;
				v_fanNum=res.fanNum;
				v_messageNum=res.messageNum;
				$("#username").html(v_Username);
				$("#t_followNum").html(v_followNum);
				$("#t_fanNum").html(v_fanNum);
				$("#t_messageNum").html(v_messageNum);
				$("#sex").html(v_sex);
				$("#age").html(v_age);
				$("#introduce").html(v_introduce);
				if(res.isFollowed==1)
				{
					var btn="<button class='btn btn-outline-success btn-sm' style='width:60px;height:26px;font-size:11px;margin-top:6px;' disabled>已关注</button>";
					$("#followBtn").html(btn);
				}
				else
				{
					var btn="<button class='btn btn-outline-success btn-sm' style='width:50px;height:26px;font-size:11px;margin-top:6px;' onclick='follow(\""+UID+"\",\""+v_UID+"\")'>关注</button>";
					$("#followBtn").html(btn);
				}
				//获取对方帖子
				getVisitMessage();
			}
				
		},
		error:function(res)
		{
			alert("getVisitInfo连接服务器失败！");
		}
	});
}

//
//获取对方的所有帖子
function getVisitMessage()
{
	$.ajax({
		type:"post",
		url:"/api/getVisitMessage",
		dataType:"JSON",
		success:function(res)
		{
			if(res!=null)
				showMessage("visit","",res);
		},
		error:function(res)
		{
			alert("getVisitMessage连接服务器失败！");
		}
	});
}