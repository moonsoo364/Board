
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="Board_module.BoardBean" %>
<jsp:useBean id="bMgr" class="Board_module.BoardMgr"/>
<%
	request.setCharacterEncoding("UTF-8");
	int num =Integer.parseInt(request.getParameter("num"));
	String nowPage =request.getParameter("nowPage");
	System.out.printf("nowPage=%s\n",nowPage);
	System.out.printf("num=%d\n",num);
	String keyField =request.getParameter("keyField");
	String keyWord =request.getParameter("keyWored");
	bMgr.upCount(num);//조회수 증가
	BoardBean bean =bMgr.getBoard(num);//게시물 가져오기
	System.out.println("bean"+bean+'\n');
	String id =bean.getId();
	String content = bean.getContent();
	String title = bean.getTitle();
	String regdate =bean.getLogdate();
	String ip =bean.getIp();
	int count =bean.getCount();
	//System.out.println("id="+id+'\n');
	System.out.println("bean.getId()="+bean.getId()+'\n');
	session.setAttribute("bean",bean);//게시물을 세션에 저장
%>
<!DOCTYPE html>
<html lang="en">
<head>
    
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>글읽기</title>
    <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" ></script>
    <script src="javascript/main.js" defer></script>
    <link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic:400" rel="stylesheet">
    <link rel="stylesheet" href="css/style_index.css">
    <link rel="stylesheet" href="css/style_read.css">
	<script type="text/javascript">
	
	function list(){
		
		document.listFrm.submit();
	}
	
	</script>
    
</head>
<body>
<%!String Idkey=null; %>
<%
if(session.getAttribute("key")!=null){
	Idkey=(String) session.getAttribute("key");
	System.out.printf("Id=%s 세션이 read.jsp에 로드되었습니다\n",Idkey);
	
}else{
	Idkey=null;
}

%>
<!-- navbar는 모든 페이지에 적용 됩니다.-->
    <nav class="navbar">
            <div class="navbar_title">
                <i class="fas fa-chalkboard-teacher"></i>
                <a href="Index.jsp">Hello IT World!</a>
            </div>

            <ul class="navbar_menu">
                <li><a href="Introduce.jsp">Introduce</a></li>
                <li><a href="list.jsp">Board</a></li>
                   
            </ul>
  	<%if(Idkey == null) { %>
  			
  			<ul class="navbar_reg">
                <li><i class="fas fa-sign-in-alt"></i><a href="login.jsp">&nbsp;login</a></li> 
                <li><i class="fas fa-registered"></i><a href="member.jsp" target="_sub">&nbsp;register</a></li>
            </ul>
       
        <%} else { %> 
        <div id="login_box">
  			
  			<div><i class="far fa-user-circle fa-3x" id="user_icon"></i></div>
  			<div id="log_ment">&nbsp;&nbsp;안녕하세요! <%=Idkey %>님<br>&nbsp;&nbsp;오늘도 즐거운 하루 되세요.
  			<input type="button" value="로그아웃" onclick="javascript:location.href='logout.jsp'">
  			</div>
  			
        </div>
        	
        
       <%}%>  
   <a href="#" class="navbar_togle">
                <i class="fas fa-sliders-h"></i>
            </a>         
    </nav>
    <div class="read_container"align="center">
	  <div class="read_head">글읽기</div>
		 <div class="writer&date">
		 	<div class="read_left1">작성자</div> <div class="read_right"><%=id %></div>
		 	 <div class="read_left2">등록일자</div> <div class="read_right"><%=regdate %></div>
	     </div>
	     
	     <div class="title">
		 	<div class="read_left1">제목</div> <div class="read_title"><%=title %></div>
	     </div>
	     <div class="read_content"><%=content %><hr>
	     <a href="javascript:list()" style="color:black">[리스트 |</a>
	     <%
	     if(id.equals(Idkey)){
	    System.out.println("게시물과 일치하는 Id입니다\n");%>
	     	
	     	<a href="update.jsp?nowPage=<%=nowPage %>&num=<%=num %>" style="color:black">수 정 |</a>
	     	<a href="delete.jsp?nowPage=<%=nowPage %>&num=<%=num %>" style="color:black">삭 제]</a>
	     
	     <%}else if(id!=Idkey) {
	     System.out.printf("id==Idkey:%s\n",id==Idkey);
	     }%>
	     
	     </div>
	     
	</div>
    

	
   <!-- footer는 모든 페이지에 적용 됩니다.-->
	<footer class="footer_style">
		<div class="footer_alarm">
			<div class="div_left"><i class="fas fa-exclamation-triangle"></i></div>
			<div class="div_right">
				<div class="footer_ment1">해당 게시판은 컴퓨터,모바일,하드웨어,소프트웨어 등의 뉴스와 정보를 공유하는 곳입니다
				<br>게시판에서 생기는 개인 및 단체의 권리 침해 문의는 aptx15hibr@naver.com로 해주세요.
				</div>
	 			
	 		</div>			
		</div>
	</footer>
<form name="listFrm" method="post" action="list.jsp">
	<input type="hidden" name="nowPage" value="<%=nowPage %>">
	<%if(!(keyWord==null || keyWord.equals(""))) { %>
		<input type="hidden" name="keyField" value="<%=keyField%>">
		<input type="hidden" name="keyWord" value="<%=keyWord%>">
	<%}%>
</form>
</body>
</html>