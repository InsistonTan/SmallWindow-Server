var Username="";
var UID="";
var sex="未设置";
var age="未设置";
var introduce="这个人很懒，什么都没留下...";
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
				if(UID==null||UID=="null"||UID=="")
				{
					UID="";
					alert("你还未登录");
					location.replace("/index.html");
				}
				else
				{	
					Username=res.Username;
					if(res.age!=""&&res.age!=null)
						age=res.age;
					if(res.sex!=""&&res.sex!=null)
						sex=res.sex;
					if(res.introduce!=""&&res.introduce!=null)
						introduce=res.introduce;
					followNum=res.followNum;
					fanNum=res.fanNum;
					messageNum=res.messageNum;
					$("#username").html(Username);
					$("#t_followNum").html(followNum);
					$("#t_fanNum").html(fanNum);
					$("#t_messageNum").html(messageNum);
					$("#sex").html(sex);
					$("#age").html(age);
					$("#introduce").html(introduce);

					getMyMessage();
				}
			},
			error:function(res)
			{
				alert("服务器错误");
			}
	});
}

//打开编辑资料模态框
function openUpdateModal()
{
	$("#updateInfoModal").modal("show");

	if(sex=="男")
        $("#radio_male").prop("checked",true);
    else if(sex=="女")
        $("#radio_female").prop("checked",true);
    if(age!="未设置")
    	$("#age_input").val(age);
    if(introduce!="这个人很懒，什么都没留下...")
    	$("#introduce_input").val(introduce);
}
//上传信息
function updateInfo()
{
	var newAge=$("#age_input").val();
	var newIntroduce=$("#introduce_input").val();
	var newSex=$("input[name='optradio']:checked").val();
	if(newAge<0||newAge>150)
	{
		$("#updateResult").html("年龄是不是输错了？合理的范围是0-150");
		return;
	}
	if(newIntroduce.length>20)
	{
		$("#updateResult").html("个人介绍太长啦，应该在20个字以内");
		return;
	}

	//
	var ajaxData={"sex":newSex,"age":newAge,"introduce":newIntroduce};
	$.ajax({
		type:"post",
		url:"/api/updateUser",
		contentType:"application/json;charset=UTF-8",
		data:JSON.stringify(ajaxData),
		dataType:"text",
		success:function(res)
		{
			if(res=="success")
			{
				//alert("更新成功");
				$("#updateInfoModal").modal("hide");
				checkLogin();
			}
			if(res=="failed")
			{
				//alert("更新失败");
				$("#updateResult").html("更新失败");
			}
		},
		error:function(res)
		{
			//alert("updateInfo连接服务器失败！");
			$("#updateResult").html("连接服务器失败！");
		}
	});
}

//
//获取自己的所有帖子
function getMyMessage()
{
	$.ajax({
		type:"post",
		url:"/api/getMyMessage",
		dataType:"JSON",
		success:function(res)
		{
			if(res!=null)
				showMessage("seeMyHome",UID,res);
		},
		error:function(res)
		{
			alert("getMyMessage连接服务器失败！");
		}
	});
}