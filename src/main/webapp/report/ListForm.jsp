<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 헤더파일들어가는 곳 -->
<jsp:include page="/inc/header.jsp"/>
<!-- 헤더파일들어가는 곳 -->
<link href="resource/css/listcss.css" rel="stylesheet" type="text/css">
<div class="boardContainer">
<!-- <script type="text/javascript" src="https://code.jquery.com/jquery-latest.js"></script> -->

<!-- 내용 시작 -->
<h1>자주하는 질문</h1>


		<div class=Left>
		<h3 style="color:blue;">고객센터</h3>
	<br>
		
		<div>
			<div>
				<a href="ReportForm.re"> 신고하기 </a><br>
				<br>
				<a href="ReportList.re">신고목록</a><br>
				<br>	
				<a href="List.re">자주하는 질문</a>					
		</div>
	</div>
	</div>

	<div class="faq">
		<div class="faqHader"></div>
		<ul class="faqBody">
		<h2>회원가입↘</h2>
			<li class="article" id="a1">
				<p class="q">
				<a href="#a1">꼭 회원가입을 해야만 사용가능 하나요</a>				
				</p>
				<pre class="a">판매자는 꼭 회원가입을 하셔야만 이용 가능하고요, 구매만 하실 경우에는 비회원으로도 구매가 가능합니다.</pre>
			</li>
			<li class="article" id=a2>
				<p class="q">
				<a href="#a2">중고 도서 사는것도 그런가요</a>				
				</p>
				<pre class="a">개인회원은 [판매관리]-[신규등록]으로 도서를 올리면 판매 가능합니다.</pre>
			</li>
			<li class="article" id=a3>
				<p class="q">
				<a href="#a3">회원 탈퇴하려면 어떻게 하나요?</a>
				</p>
				<pre class="a">마이페이지 > 회원 탈퇴에서 신청하시면 됩니다.</pre>
			</li>
</ul>
</div>
	<div class="faq">
		<div class="faqHader"></div>
		<ul class="faqBody">
		<h2>판매자↘</h2>
			<li class="article" id="a1">
				<p class="q">
				<a href="#a1" style="">질문1</a>				
				</p>
				<pre class="a">답변1</pre>
			</li>
			<li class="article" id=a2>
				<p class="q">
				<a href="#a2">질문2</a>				
				</p>
				<pre class="a">답변2</pre>
			</li>
			<li class="article" id=a3>
				<p class="q">
				<a href="#a3">질문3</a>
				</p>
				<pre class="a">답변3</pre>
			</li>
</ul>
</div>
	<div class="faq">
		<div class="faqHader"></div>
		<ul class="faqBody">
		<h2>구매자↘</h2>
			<li class="article" id="a1">
				<p class="q">
				<a href="#a1">책 상태를 자세히 알고 싶어요.</a>				
				</p>
				<pre class="a">판매도서는 판매자가 보유하고 있는 도서입니다. 보다 자세한 판매 문의는 판매자에게 직접 하셔야 합니다.
구매 전 일 경우는 '도서 문의 답변'를 통해서 문의해주시면 됩니다.</pre>
			</li>
			<li class="article" id=a2>
				<p class="q">
				<a href="#a2">질문2</a>				
				</p>
				<pre class="a">답변2</pre>
			</li>
			<li class="article" id=a3>
				<p class="q">
				<a href="#a3">질문3</a>
				</p>
				<pre class="a">답변3</pre>
			</li>
</ul>
</div>
<script>
// FQA 형식 
jQuery(function($){
    // Frequently Asked Question
    var article = $('.faq>.faqBody>.article');
    article.addClass('hide');
    article.find('.a').hide();
    article.eq(0).removeClass('hide');
    article.eq(0).find('.a').show();
    $('.faq>.faqBody>.article>.q>a').click(function(){
        var myArticle = $(this).parents('.article:first');
        if(myArticle.hasClass('hide')){
            article.addClass('hide').removeClass('show');
            article.find('.a').slideUp(100);
            myArticle.removeClass('hide').addClass('show');
            myArticle.find('.a').slideDown(100);
        } else {
            myArticle.removeClass('show').addClass('hide');
            myArticle.find('.a').slideUp(100);
        }
        return false;
    });
    $('.faq>.faqHeader>.showAll').click(function(){
        var hidden = $('.faq>.faqBody>.article.hide').length;
        if(hidden > 0){
            article.removeClass('hide').addClass('show');
            article.find('.a').slideDown(100);
        } else {
            article.removeClass('show').addClass('hide');
            article.find('.a').slideUp(100);
        }
    });
});


</script>
</div>
<!-- 푸터파일들어가는 곳 -->
<jsp:include page="/inc/footer.jsp"/>
<!-- 푸터파일들어가는 곳 -->
