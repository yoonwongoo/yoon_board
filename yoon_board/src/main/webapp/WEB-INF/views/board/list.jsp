<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
</script>
  <style>
  a{
  	text-decoration : none;
  }
  table{
 	border-collapse: collapse;
 	width: 1000px;    
 	margin-top : 20px;
 	text-align: center;
  }
  td, th{
  	border : 1px solid black;
  	height: 50px;
  }
  th{
  	font-size : 17px;
  }
  thead{
  	font-weight: 700;
  }
  .table_wrap{
  	margin : 50px 0 0 50px;
  }
  .bno_width{
  	width: 12%;
  }
  .writer_width{
  	width: 20%;
  }
  .regdate_width{
  	width: 15%;
  }
  .updatedate_width{
  	width: 15%;
  }
  .top_btn{
  	font-size: 20px;
    padding: 6px 12px;
    background-color: #fff;
    border: 1px solid #ddd;
    font-weight: 600;
  }
  .pageInfo{
      list-style : none;
      display: inline-block;
    margin: 50px 0 0 100px;      
  }
  .pageInfo li{
      float: left;
    font-size: 20px;
    margin-left: 18px;
    padding: 7px;
    font-weight: 500;
  }
 a:link {color:black; text-decoration: none;}
 a:visited {color:black; text-decoration: none;}
 a:hover {color:black; text-decoration: underline;}
  .active{
      background-color: #cdd5ec;
  }
  .search_area{
    display: inline-block;
    margin-top: 30px;
    margin-left: 260px;
  }
  .search_area input{
      height: 30px;
    width: 250px;
  }
  .search_area button{
     width: 100px;
    height: 36px;
  }
  </style>
</head>
<body>
<h1>목록페이지입니다.</h1>

<div class="table_wrap">
	<a href="/board/enroll" class="top_btn">게시판 등록</a>
	<table>
		<thead>
			<tr>
				<th class="bno_width">번호</th>
				<th class="title_width">제목</th>
				<th class="writer_width">작성자</th>
				<th class="regdate_width">작성일</th>
				<th class="updatedate_width">수정일</th>
			</tr>
		</thead>
		<c:forEach items="${list}" var ="list">
			<tr>
				<td><c:out value="${list.bno}"/></td>
				<td>
				<a class="move" href='<c:out value="${list.bno}"/>'>
						<c:out value="${list.title}"></c:out>
				</a>
				</td>
				
				<td><c:out value="${list.content}"></c:out></td>
				<td><fmt:formatDate pattern="yyyy/MM/dd" value="${list.regdate}"/></td>
				<td><fmt:formatDate pattern="yyyy/MM/dd" value="${list.updateDate}"/></td>
			</tr>
		</c:forEach>
	</table>
	   <div class="search_wrap">
        <div class="search_area">
            <input type="text" name="keyword" value="${pagingMaker.paging.keyword }">
            <button>검색</button>
        </div>
    </div>    
	   <div class="pageInfo_wrap" >
        <div class="pageInfo_area">
 		 	<ul id = "pageInfo" class="pageInfo">
 		 	 <!-- 이전페이지 버튼 -->
                <c:if test="${pagingMaker.prev}">
                    <li class="pageInfo_btn previous"><a href="${pagingMaker.startPage-1}">이전</a></li>
                </c:if>
 		 	
 		 	 <c:forEach var="num" begin="${pagingMaker.startPage}" end="${pagingMaker.endPage}">
                    <li class="pageInfo_btn"${pagingMaker.paging.pageNum == num ? "active":"" }><a href="${num}">${num}</a></li>
                </c:forEach>
 
 			<!-- 다음페이지 버튼 -->
                <c:if test="${pagingMaker.next}">
                    <li class="pageInfo_btn next"><a href="${pagingMaker.endPage + 1 }">다음</a></li>
                </c:if>    
 
 		 	</ul>
        </div>
  	  </div>
	
	<form id="moveForm" method="get">
        <input type="hidden" name="pageNum" value="${pagingMaker.paging.pageNum }">
        <input type="hidden" name="amount" value="${pagingMaker.paging.amount}">    
        <input type="hidden" name="keyword" value="${pagingMaker.paging.keyword}">
	</form>
</div>


<script>
	$(document).ready(function(){
		let result ='<c:out value="${result}"/>';
		
		 checkAlert(result);
		 
		 function checkAlert(result){
			 if(result ===''){
				 return;
			 }
			 
			 if(result === "enrollCheck"){
				 alert("등록되었습니다.");
			 }
			 
			 if(result === "modifysuccess"){
				 alert("수정되었습니다.");
			 }
			 
			 if(result === "deletesucess"){
				 alert("삭제되었습니다.");
			 }
		 }
	});
	
	/*'클릭한 <a>태그 기능 정지' =>
	'<form> 태그 내부 bno값을 저장하는 <input>태그 생성' => 
	'<form>태그 action 속성 추가' => 
	'<form>태그 내부 데이터 서버 전송'*/
	let moveForm = $("#moveForm");
	
		$(".move").on("click",function(e){
			e.preventDefault();
			moveForm.append("<input type='hidden' name='bno' value='"+ $(this).attr("href")+ "'>");
		    moveForm.attr("action", "/board/getcontent");
		    moveForm.submit();
		    });
		
		

	    $(".pageInfo a").on("click", function(e){
	 
	        e.preventDefault();
	        moveForm.find("input[name='pageNum']").val($(this).attr("href"));
	        moveForm.attr("action", "/board/list");
	        moveForm.submit();
	        
	    });	
	    // <form> 태그에 action 속성을 통해 url을 지정해주지 않으면 전송하였을 때 현재의 url경로의 매핑 메서드를 호출하게 됨.
	    // 어차피 list.jsp에서 지금 동일하게 사용하기 때문에attr에 method방식과 url매핑 주소를 굳이 안 넣어줘도 됨.
	    $(".search_area button").on("click", function(e){
	        e.preventDefault();
	        let val = $("input[name='keyword']").val();
	        
	        moveForm.find("input[name='keyword']").val(val);
	        moveForm.find("input[name='pageNum']").val(1);
	        moveForm.submit();
	    });
		
</script>
</body>
</html>