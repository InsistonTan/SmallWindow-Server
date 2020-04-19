var showCode="";
var loginFlag=0;
var UID="";
var Username="";
var messageIndex=0;

$(document).ready(function(){
	getInfo();
	creatCode();
	getNewMessage();
});
//获取帖子
function getNewMessage()
{
	/*var start=messageIndex+1;
	var end=messageIndex+9;
	var ajaxData={"start":start,"end":end};*/
	$.ajax({
			type:"post",
			url:"/api/getNewMessage",
			dataType:"JSON",
			success:function(res)
			{
				//alert(res);
				if(res!="")
					showMessage("index",UID,res);
			},
			error:function(res)
			{
				alert("连接服务器失败！");
			}
	});
}
/*//展示帖子
function showMessage(data)
{
	var len=data.length;
	messageIndex+=len;
	//清空原来的
	$("#MessageBody").html("");
	//循环展示
	for(var i=0;i<len;i++)
	{
		var temp="<div class='rounded' style='width:100%;background-color:rgba(255,255,255,0.7);margin-top:10px;'>"
		            +"<div id='Username' style='font-size:16px;margin-top:10px;margin-left:10px;'>"
		                +"<img src='images/user2.png' alt='account' style='width: 18px;height: 18px;'>"
		                +"<a href='#' style='color:rgb(205,133,63);text-decoration:none;margin-left:5px;'>"
		                	+data[i].user
		            	+"</a></div>"
		            +"<div id='time' style='font-size:10px;margin-left:10px;'>"
		                +data[i].time
		            +"</div>"
		            +"<div id='content' style='margin:10px;font-size:14px;padding-bottom:10px;'>"
		                +data[i].content
		            +"</div></div>";
		$("#MessageBody").append(temp);
	}
}*/
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
				location.replace("/html/home.html");
			}
			
		},
		error:function(res)
		{
			alert("获取信息失败index");
		}
	});
}
//创建验证码
function creatCode()
{
	showCode="";
	//document.getElementById('codecanvas')
	var canvas=document.getElementById('codecanvas');
	if(canvas!=null)
	{
		var width=document.getElementById('codecanvas').clientWidth;
		var height=document.getElementById('codecanvas').clientHeight;
		var context=canvas.getContext("2d");
		canvas.width=width;
		canvas.height=height;
		var sCode="q,w,e,r,t,y,u,i,o,p,a,s,d,f,g,h,j,k,l,z,x,c,v,b,n,m,1,2,3,4,5,6,7,8,9,0";
		var aCode=sCode.split(",");
		var codeLength=aCode.length;

		for(var i=0;i<=3;i++)
		{
			var index=Math.floor(Math.random()*codeLength);
			showCode+=aCode[index];
		}
		context.translate(10,25);
		context.font="bold 23px 微软雅黑";
		context.fillStyle=randomColor();
		context.fillText(showCode,0,0);

		for(var i=0;i<=10;i++)
		{
			context.translate(-5,-5);
			context.strokeStyle=randomColor();
			context.beginPath();
			context.moveTo(Math.random()*width,Math.random()*height);
			context.lineTo(Math.random()*width,Math.random()*height);
			context.stroke();
		}
	}
}
function randomColor()
{
	var r=Math.floor(Math.random()*256);
	var g=Math.floor(Math.random()*256);
	var b=Math.floor(Math.random()*256);
	return "rgb("+r+","+g+","+b+")";
}
//登录
function login()
{
	var inputCode=$("#code").val();
	if(inputCode=="")
	{
		$("#result").html("验证码为空！");
		return;
	}
	else if(inputCode!=showCode)
	{
		$("#result").html("验证码错误！");
		creatCode();
		return;
	}
	//
	var user=$("#username").val();
	var password=$("#password").val();

	if(user==""||password=="")
		$("#result").html("用户名和密码都不能为空！")
	else
	{
		//
		var ajaxData={"Password":password,"Username":user};
		$.ajax({
			type:"post",
			//data:ajaxData,
			data:JSON.stringify(ajaxData),
			contentType:"application/json;charset=UTF-8",
			url:"/api/login",
			dataType:"text",
			success:function(res)
			{
				if(res=="success")
				{
					loginFlag=1;
					location.replace("html/home.html");
				}
				else if(res=="password incorrect")
				{
					$("#result").html("密码错误！");
					creatCode();
				}
				else if(res=="user not exist")
				{
					$("#result").html("用户不存在！");
					creatCode();
				}
				else 
				{
					$("#result").html("服务器错误！");
					creatCode();
				}
			},
			error:function(res)
			{
				$("#result").html("连接服务器失败！");
				creatCode();
			}
		});
	}
 }
	//注册
	var flag;
    function turnToLogin()
    {
        if(flag==1)
        	window.close();
        //window.location.href="index.html";
    }
    //
    function register()
    {
     	var username=$("#r_username").val();
        var password1=$("#r_password1").val();
        var password2=$("#r_password2").val();
        var code=$("#r_code").val();
        var nameLength=$("#r_username").val().length;
        //alert(nameLength);
        if(nameLength>=8)
        {
        	$("#r_result").html("名字太长了,应该小于8个字符");
        	return;
        }
        if(code=="")
        {
        	$("#r_result").html("验证码为空！");
        	return;
        }
        else if(code!=showCode)
        {
        	$("#r_result").html("验证码错误！");
        	creatCode();
        	return;
        }
        if(username==""||password1==""||password2=="")
        {
        	$("#r_result").html("用户名和密码不能为空！");
        	creatCode();
        	return;
        }
        if(password2!=password1)
        {
        	$("#r_result").html("两次密码输入不匹配！");
        	creatCode();
        	return;
        }
        //显示模态框
        var temp_data={"Username":username,"Password":password1};
        $("#myModal").modal("show");
        $.ajax({
                type:"post",
                data:JSON.stringify(temp_data),
                contentType:"application/json;charset=UTF-8",
                url:"/api/register",
                dataType:"text",
                success:function(res)
                {
                    if(res=="success")
                    {
                        flag=1;
                        var result="<p class='text-success'>注册成功！</p>";
                        $("#modalBody").html(result);
                     }
                    else if(res=="user exist")
                    {
                        flag=0;
                        var result="<p class='text-danger'>用户名已存在，注册失败！</p>";
                        $("#modalBody").html(result);
                        creatCode();
                    }
                    else if(res=="failed")
                    {
                        flag=0;
                        var result="<p class='text-danger'>服务器错误，注册失败！</p>";
                        $("#modalBody").html(result);
                        creatCode();
                    }
                    else
                    {
                    	flag=0;
                        var result="<p class='text-danger'>未知错误，注册失败！</p>";
                        $("#modalBody").html(result);
                        creatCode();
                    }
                },
                error:function(res)
                {
                    flag=0;
                    var result="<p class='text-danger'>连接服务器失败，注册失败！</p>";
                    $("#modalBody").html(result);
                    creatCode();
                }
            });
        }
        