var openModalFlag=0;
var open_source;
//var UID="";
//展示帖子
function showMessage(source,uid,data)
{
	open_source=source;
	//UID=uid;
	var len=data.length;
	//messageIndex+=len;
	//清空原来的
	$("#MessageBody").html("");
	//循环展示
	for(var i=0;i<len;i++)
	{
		var otherMessage="<div class='rounded shadow_div' style='width:100%;background-color:rgba(255,255,255,0.5);margin-top:10px;'>"
		            		+"<div id='Username' style='font-size:16px;margin-top:10px;margin-left:10px;'>"
		                	+"<img src='/images/user2.png' alt='account' style='width: 18px;height: 18px;'>"
		                	+"<a href='#' class='font_shadow' style='color:rgb(205,133,63);text-decoration:none;margin-left:5px;' onclick='visit(\""+uid+"\",\""+data[i].uid+"\")'>"
		                		+data[i].user
		            		+"</a></div>"
		            		+"<div id='time' style='font-size:10px;margin-left:10px;'>"
		                		+data[i].time
		            		+"</div>"
		            		+"<div id='content' style='margin:10px;font-size:14px;padding-bottom:10px;'>"
		                		+data[i].content
		            		+"</div></div>";
		///////////////////////////////////////
		var myMessage="<div class='rounded shadow_div' style='width:100%;background-color:rgba(255,255,255,0.5);margin-top:10px;'>"
		            	+"<div id='Username' style='font-size:16px;margin-top:10px;margin-left:10px;'>"
		                +"<img src='/images/user2.png' alt='account' style='width: 18px;height: 18px;'>"
		                +"<a href='#' class='font_shadow' style='color:rgb(205,133,63);text-decoration:none;margin-left:5px;' onclick='visit(\""+uid+"\",\""+data[i].uid+"\")'>"
		                	+data[i].user
		            	+"</a>"
		            	+"<button class='close' type='button' style='margin-right:4px;margin-top:4px;'" 
		            		+"onclick='openDeleteModal("+data[i].index+")'>&times</button>"
		            	+"</div>"
		            	+"<div id='time' style='font-size:10px;margin-left:10px;'>"
		                	+data[i].time
		           	 	//+"<a href='#' style='text-decoration:none;margin-left:5px;font-size:8px;' onclick='deleteMessage("+data[i].index+")'>删除</a></div>"
		            	+"<div id='content' style='margin:10px;font-size:14px;padding-bottom:10px;'>"
		                	+data[i].content
		            	+"</div></div>";
		         
		if(data[i].uid!=uid)
			$("#MessageBody").append(otherMessage);
		else
			$("#MessageBody").append(myMessage);
	}
}
///
function openDeleteModal(index)
{
	var modal="<div class='modal fade' id='deleteModal' style='margin-top:10%;'>"
	            +"<div class='modal-dialog modal-sm'>"
	                +"<div class='modal-content'>"
	                    +"<div class='modal-header'>"
	                        +"<span class='modal-title' style='color:#8B5A2B;'>删除帖子</span>"
	                        +"<button class='close' type='button' data-dismiss='modal'>&times</button>"
	                    +"</div>"
	                    +"<div class='modal-body' id='modalBody' style='text-align:center;'>"
	                    	+"<div style='color:rgb(255,0,0);'>确定要删除此帖子？</div>"
	                        +"<div id='result' style='color:rgb(220,0,0);font-size:12px;margin-top:4px;'></div>"
	                    +"</div>"
	                    +"<div class='modal-footer'>"
	                        +"<button class='btn btn-success' type='button' onclick='deleteMessage("+index+")'>确认</button>"
	                        +"<button class='btn btn-warning' type='button' data-dismiss='modal'>取消</button>"
	                     +"</div></div></div></div></div>";
	if(openModalFlag==0)
	{
		$("#MessageBody").append(modal);
		$("#deleteModal").modal("show");
		openModalFlag=1;
	}
	else
	{
		$("#result").html("");
		$("#deleteModal").modal("show");
	}

}
///
function deleteMessage(index)
{
	var ajaxData={"index":index};
	$.ajax({
		type:"post",
		url:"/api/deleteMessage",
		contentType:"application/json;charset=UTF-8",
		data:JSON.stringify(ajaxData),
		dataType:"text",
		success:function(res)
		{
			if(res=="success")
			{
				
				$("#deleteModal").modal("hide");
				location.reload(true);
				//reShow();
			}
			else
				$("#result").html(res);
		},
		error:function(res)
		{
			$("#result").html("连接服务器失败!");
		}
	});
}
///
function reShow()
{
	if(open_source=="home")
		getFollowMessage();
	else if(open_source=="seeMyHome")
		getMyMessage();
	else if(open_source=="search")
		search();
}