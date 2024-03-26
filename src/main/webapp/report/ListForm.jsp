<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 헤더파일들어가는 곳 -->
<jsp:include page="/inc/header.jsp"/>
<!-- 헤더파일들어가는 곳 -->
<link href="resource/css/listcss.css" rel="stylesheet" type="text/css">

<h3 class="call">고객센터</h3>
				<br><br>
				<div class="left">
					<br> <br>
					<div>
						<div>
							<a href="ReportForm.re"> 신고하기 </a><br> <br> <a
								href="ReportList.re">신고목록</a><br> <br> <a
								href="List.re">자주하는 질문</a>

						</div>
					</div>
				</div>


<div class="boardContainer">
<!-- 내용 시작 -->
<h1 class="T">자주하는 질문 ☎</h1>

<table class="te">
	<div class="faq">
		<ul class="faqBody">
		<h2 class="t">🎊 회원가입↘</h2>
			<li class="article" id="a1">
				<p class="q">
				<a class="Q" href="#a1">꼭 회원가입을 해야만 사용가능 하나요?</a>				
				</p>
				<pre class="a">판매자는 꼭 회원가입을 하셔야만 이용 가능합니다 구매만 하실 경우에는 비회원으로도 구매가 가능합니다.</pre>
			</li>
			<li class="article" id=a2>
				<p class="q">
				<a class="Q" href="#a2">회원가입후 바로 판매가 가능한가요?</a>				
				</p>
				<pre class="a">가능합니다. 중고거래 판매 👉 글쓰기 하시면 됩니다. </pre>
			</li>
			<li class="article" id=a3>
				<p class="q">
				<a class="Q" href="#a3">회원 탈퇴하려면 어떻게 하나요?</a>
				</p>
			<pre class="a">마이페이지 👉 회원 탈퇴에서 신청하시면 됩니다.</pre>
			</li>
</ul>
</div>
</table>

<table class="te">
	<div class="faq">
		<ul class="faqBody">
		<h2 class="t">👨‍👩 판매자↘</h2>
			<li class="article" id="a1">
				<p class="q">
				<a class="Q" href="#a1" style="">도서등록은 어떻게 하나요?</a>				
				</p>
				<pre class="a">중고거래 판매 👉 글쓰기 하시면 됩니다. </pre>
			</li>
			<li class="article" id=a2>
				<p class="q">
				<a class="Q" href="#a2">도서등록 후 내용을 수정하려면 어떻게 하나요?</a>				
				</p>
				<pre class="a">마이페이지 👉 내가쓴글로 이동하셔서 글제목 클릭후 수정이 가능합니다. </pre>
			</li>
			<li class="article" id=a3>
				<p class="q">
				<a class="Q" href="#a3"> 판매 취소하고 싶은데 가능한가요?</a>
				</p>
				<pre class="a">마이페이지 👉 내가쓴글로 이동하셔서 글제목 클릭후 게시글 삭제가 가능합니다.</pre>
			</li>
</ul>
</div>
</table>


	<div class="faq">
		<ul class="faqBody">
		<h2 class="t">📚 구매자↘</h2>
			<li class="article" id="a1">
				<p class="q">
				<a class="Q" href="#a1">책 상태를 자세히 알고 싶어요?</a>				
				</p>
				<pre class="a">판매도서는 판매자가 보유하고 있는 도서입니다. 보다 자세한 판매 문의는 판매자에게 직접 하셔야 합니다.
구매 전 일 경우는 '도서 문의 답변'를 통해서 문의해주시면 됩니다.</pre>
			</li>
			<li class="article" id=a2>
				<p class="q">
				<a class="Q" href="#a2">사기를 당하면 어떻게 해야하나요? </a>				
				</p>
				<pre class="a">고객센터 👉 신고하기에 가셔서 신고를 하시면 됩니다.</pre>
				<pre class="a">해당신고 아이디는 정지가되며 자동으로 사이버수사대에 접수가 됩니다.</pre>
			</li>
			<li class="article" id=a3>
				<p class="q">
				<a class="Q" href="#a3">판매자 정보를 알고 싶어요?</a>
				</p>
				<pre class="a">해당글의 작성자 우클릭 하시면 해당판매자의 프로필을 볼수있습니다. </pre>
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
