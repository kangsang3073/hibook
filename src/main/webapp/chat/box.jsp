<%@page import="com.itwillbs.chat.db.ChatDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
		String mem_id = null;
		if (session.getAttribute("id") != null) {
			mem_id = (String) session.getAttribute("id");
 		} //특정한 사람 ID값 가져오기 
 		if (mem_id == null){
//  			session.setAttribute("messageType", "오류 메시지");
//  			session.setAttribute("messageContent", "현재 로그인이 되어 있지 않습니다.");
//  			response.sendRedirect("index.jsp");
			%>
			<script type="text/javascript">
			alert("현재 로그인이 되어있지 않습니다");
			</script>
 			<% 
 			return;
 		}
 		
 		ChatDAO ncao = new ChatDAO();
 		String nickname = ncao.getNickname(mem_id);
	%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<link rel="stylesheet" href="../resource/css/box.css">
<title>채팅</title>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>  
<script type="text/javascript">
// 	function chat() {
// 		window.open("chat.jsp", "a", "width=500, height=700"); 
// 	} // window.open("켜지는 창 파일명", "별명" , "크기");

	function getUnread(){
		$.ajax({
			type: "POST",
			url: "./chatUnread",
			data: {
				mem_id: encodeURIComponent('<%= mem_id%>'),
			},
			success: function(result) {
				if(result >= 1){
					showUnread(result);
				} else {
					showUnread('');
				}
			}
		});
	}
	function getInfiniteUnread(){
		setInterval(function(){
			getUnread();
		}, 4000);
	}
	function showUnread(result){
		$('#unread').html(result);
	}
	function chatBoxFunction(){
		var mem_id = '<%= mem_id %>'
		$.ajax({
			type: "POST",
			url: "./chatBox",
			data: {
				id: encodeURIComponent(mem_id),
			},
			success: function(data) {
				if(data == "") return;
				$('#boxTable').html(''); //초기화
				var parsed = JSON.parse(data); 
				var result = parsed.result;
				for(var i = 0; i < result.length; i++){
					if(result[i][0].value == mem_id) {
						result[i][0].value = result[i][1].value;
					}else{
						result[i][1].value = result[i][0].value;
					}
					addBox(result[i][0].value, result[i][1].value, result[i][2].value, result[i][3].value,  result[i][4].value,  result[i][5].value, result[i][6].value)
				}
			}
		});
	}
	
	function addBox(lastID, to_id, chat_content, chat_time, unread, mem_img, from_nickname) {
// 		String nickname_from = getNickname(lastID);
// 		ChatDAO fcao = new ChatDAO();
// 		lastID = fcao.getNickname(lastID);

		$('#boxTable').append('<tr onclick="location.href=\'Chat.hi?to_id=' + encodeURIComponent(to_id)+ '\'">' + 
				'<td style="width: 150px;">' +
				'<img class="boxprofile" src="' + mem_img + '">' +
				'<br><span class="boxuserid">' + lastID + '</span></td>' +
				'<td class="boxtd">' +
				'<span class="boxcontent">' + chat_content +
				'</span>' +
				'<span class="boxunread">' + unread + '</span>' +
				'<div class="boxchattime">' + chat_time + '</div>' +
				'</td>' +
				'</tr>'
				);
		
// 		<tr onclick="location.href=\'chat.jsp?to_id=' + encodeURIComponent(to_id)+ '\'">
// 			<td style="width: 150px;">'
// 				<img class="boxprofile" src="' + mem_img + '">
// 				<h5>lastID</h5>
// 			</td> 
// 			<td class="boxtd"><h5>chat_content<span class="boxunread">unread</span></h5>
// 				<div class="boxchattime">chat_time</div>
// 			</td>
// 		</tr>
	}
	function getInfiniteBox(){
		setInterval(function(){
			chatBoxFunction();
		}, 1000)
	} // 1초에 한번씩 메시지함 갱신
</script>

</head>
<body>
	
	<div class="container">
		<div class="chatboxlogo">
				<img src="../resource/image/hibooklogoblack.png"><!--로고나 해당 웹사이트 제목을 넣어줄 수 있는 부분-->
				<span class="mylist"><%=nickname %>님의 채팅 리스트</span>
		</div>
			<div class="a" style="overflow-y:auto; width: 100%; max-height:540px;">
				<table class="messagelist" style="text-align:center;">
					<tbody id="boxTable">
					</tbody>
				</table>
			</div>	
	<div class="chatboxfoot">
		<div class="footlogo">
			<span class="hibook">Hibook</span>
			<img src="../resource/image/quill-pen.png">
		</div>
	</div>
	</div>
	<!-- ==========================================================Modeal 팝업창 -->

	<%
		String messageContent = null;
		if (session.getAttribute("messageContent") != null) {
			messageContent = (String) session.getAttribute("messageContent");
		}
		String messageType = null;
		if (session.getAttribute("messageType") != null) {
			messageType = (String) session.getAttribute("messageType");
		}
		if (messageContent != null) {
	%>
	<div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-hiddin="true">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-center">
				<div class="modal-content <% if(messageType.equals("오류 메시지")) out.println("panel-warning"); else out.println("panel-success"); %>"></div> 
					<div class="modal-header panel-heading">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times</span>
							<span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">
							<%= messageType %>
						</h4>
					</div>
					<div class="modal-body">
						<%= messageContent %>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
					</div>
				</div>
			</div>
		</div>

	<%
		session.removeAttribute("messageContent");
		session.removeAttribute("messageType");
		}
	%>
	
		<script>
// 		$('#messageModal').modal("show");
	</script>
	<%
		if(mem_id != null) {
	%>
	<script type="text/javascript">
		$(document).ready(function() {
			getUnread();
			getInfiniteUnread();
			chatBoxFunction();
			getInfiniteBox();
		});
	</script>	
	<%
		}
	%>
	<!-- ==========================================================modal 팝업창 -->
</body>
</html>