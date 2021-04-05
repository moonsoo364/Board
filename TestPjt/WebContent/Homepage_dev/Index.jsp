
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main</title>
    <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" ></script>
    <script src="javascript/main.js" defer></script>
    <link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic:400" rel="stylesheet">
    <link rel="stylesheet" href="css/style_index.css">
    
</head>
<body>
<%!String Idkey=null; %>
<%
if(session.getAttribute("key")!=null){
	Idkey=(String) session.getAttribute("key");
	System.out.printf("Id=%s 세션이 Index.jsp에 로드되었습니다\n",Idkey);
	
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
                <li><a href="Static.jsp">Statistics</a></li>     
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
    
   <div class="div_Explan">
   		<p class="p_title">IT기기는 우리 일상 가까이 있죠.</p> 
   		<p class="p_main">주변을 둘러보세요, 스마트폰,워치,컴퓨터.. 우리는 IT와 친구랍니다! </p>
   		<img class="img_IT"alt="ITdvice" src="images/ITdevice.jpg">
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
</body>
</html>