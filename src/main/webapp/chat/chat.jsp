
<%@page import="com.itwillbs.chat.db.ChatDAO"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.itwillbs.member.db.MemberDAO" %>
<!DOCTYPE html>
<html>
<head>
	<%
		
		
		String mem_id = null;
		if (session.getAttribute("id") != null) {
			mem_id = (String) session.getAttribute("id");
 		} //특정한 사람 ID값 가져오기 
		String to_id = null;
 		if (request.getParameter("to_id") != null) {
 			to_id = (String) request.getParameter("to_id");
 	
 		}
 		if (mem_id == null){
 			session.setAttribute("messageType", "오류 메시지");
 			session.setAttribute("messageContent", "현재 로그인이 되어 있지 않습니다.");
//  			response.sendRedirect("index.jsp");
 			return;
 		}
 		if (to_id == null) {
 			session.setAttribute("messageType", "오류 메시지");
 			session.setAttribute("messageContent", "대화 상대가 지정되지 않았습니다.");
//  			response.sendRedirect("index.jsp");
 			return;
 		}
 		if(mem_id.equals(URLDecoder.decode(to_id,"UTF-8"))){
 			session.setAttribute("messageType", "오류 메시지");
 			session.setAttribute("messageContent", "자기 자신에게는 쪽지를 보낼 수 없습니다.");
//  			response.sendRedirect("index.jsp");
 			return;
 		}
 		
 		String fromProfile = new MemberDAO().getProfile(mem_id);
 		String toProfile = new MemberDAO().getProfile(to_id);
 		ChatDAO ncao = new ChatDAO();
		String nickname = ncao.getNickname(to_id);
	%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<link rel="stylesheet" href="../resource/css/chat.css">
<title>채팅</title>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>  
<script type="text/javascript">
	function chatbox(){
			window.open("ChatList.hi", "vv", "width=500, height=700"); 
	}
	function autoClosingAlert(selector, delay){
		var alert = $(selector).alert();
		alert.show();
		window.setTimeout(function() { alert.hide()}, delay);
	}
	function submitFunction(){
		var from_id = '<%= mem_id %>';    
		var to_id = '<%= to_id %>';
		var chat_content = $('#chat_content').val(); // 보내는사람이 mem_id 받는사람은 to_id 자신이 입력한 메시지를 넣음
		$.ajax({ // 비동기적 통신
			type: "POST",
			url: "./chatSubmitServlet",
			data: {
				from_id: encodeURIComponent(from_id),
				to_id: encodeURIComponent(to_id),
				chat_content: encodeURIComponent(chat_content),
			},
			success: function(result) {
				if(result == 1){
					autoClosingAlert('#successMessage', 2000);
				} else if (result == 0){
					autoClosingAlert('#dangerMessage', 2000);
				} else {
					autoClosingAlert('#warningMessage', 2000);
				}
			}
		});
		$('#chat_content').val(''); //메시지를 보냈으므로 값을 비워줌
	}
	var lastID = 0;
	function chatListFunction(type) {
		var from_id = '<%= mem_id %>';
		var to_id = '<%= to_id %>';
		$.ajax({
			type: "POST",
			url: "./chatListServlet",
			data: {
				from_id: encodeURIComponent(from_id),
				to_id: encodeURIComponent(to_id),
				listType: type
			},
			success: function(data) {
				if(data == "") return;
				var parsed = JSON.parse(data);
				var result = parsed.result;
				for(var i = 0; i < result.length; i++) {
					if(result[i][0].value == from_id){
						result[i][0].value = '나';
					}
					addChat(result[i][0].value, result[i][2].value, result[i][3].value);
				}
				lastID = Number(parsed.last); //가장 마지막으로 전달 받은 chat_id가 전달

			}
		});
	}
	function addChat(nickname, chat_content, chat_time) {
		if(nickname == '나'){
		$('#chatList').append('<div class="mychat">'+
				'<div class="mychatroom">' +
				'<span class="mytime">' +
				chat_time +
				'</span>' +
				'<p>' +
				chat_content +
				'</p>'
				);
			
// 				<div class="mychat">
// 					<div class="mychatroom">
// 						<span class="mytime">chat_time</span><p>chat_content</p>
// 				</div>
					
		} else{
			$('#chatList').append(
					'<div class="yourchat">'+
					'<img src="<%= toProfile%>" alt="">' +
					'<div class="yourchatroom">' +
					'<span class="yourtime">' +
					chat_time +
					'</span>' +
					'<p>' +
					chat_content +
					'</p>'
					);
//				<div class="yourchat">
//				<img src="</%= toProfile%>" alt="">'' +
//				<div class="yourchatroom">
//					<p>chat_content</p><span class="yourtime">chat_time</span>
//			</div>
			
		}
		$('#chatList').scrollTop($('#chatList')[0].scrollHeight);
	}
	function getInfiniteChat(){
		setInterval(function(){
			chatListFunction(lastID);
		},1000);  //1초마다 실행
	}
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
	function onKeyUp(){
		if(window.event.keyCode == '13'){
			submitFunction();
		}
	}
</script>
</head>
<body>
<!-- ==========================================================채팅창 디자인 -->
	<div class="container">
		<div class="chatlogo">
				<span class="logoimg"><img src="../resource/image/hibookchat.PNG"></span><!--로고나 해당 웹사이트 제목을 넣어줄 수 있는 부분-->
				<span class="yourid"><%= nickname %> 님 와의 대화</span>
				<span class="gobox"><img src="../resource/image/bookmark.png" onclick="chatbox();"></span>
		</div>
		<div id="chatList"  style="overflow-y: auto; width: 100%; height : 520px;">
		</div>
				<div class="submitarea">
					<textarea id="chat_content" style="height: 80px;" class="chatarea" placeholder=" 메시지를 입력하세요." maxlength="100"  onkeyup="onKeyUp()"></textarea>
					<img src="../resource/image/quill-pen.png" onclick="submitFunction();">
<!-- 					<input class="chatsubmitbutton" type="button" onclick="submitFunction();" value="전송"> -->
				</div>
	</div>
<!-- 		<div class="alert alert-success" id="successMessage" style="display: none;"> -->
<!-- 			<strong>메시지 전송에 성공했습니다.</strong> -->
<!-- 		</div> -->
<!-- 		<div class="alert alert-danger" id="dangerMessage" style="display: none;"> -->
<!-- 			<strong>이름과 내용을 모두 입력해주세요.</strong> -->
<!-- 		</div> -->
<!-- 		<div class="alert alert-warning" id="warningMessage" style="display: none;"> -->
<!-- 			<strong>데이터베이스 오류가 발생했습니다.</strong> -->
<!-- 		</div> -->
	<!-- ==========================================================채팅창 디자인 -->
	<!-- ==========================================================modal 팝업창 -->
	
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
	<script>
		$('#messageModal').modal("show");
	</script>
	<%
		session.removeAttribute("messageContent");
		session.removeAttribute("messageType");
		}
	%>
	<script type="text/javascript">
		$(document).ready(function() {
			getUnread();
			chatListFunction('0');
			getInfiniteChat();
			getInfiniteUnread();
		});
	</script>
	
	<!-- ==========================================================modal 팝업창 -->
</body>
</html>