
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="mmgr" class="Homepage_module.MemberMng"></jsp:useBean>
<%
	request.setCharacterEncoding("UTF-8");
	String id =request.getParameter("id");
	String pwd =request.getParameter("pwd");
	String url = "Index.jsp";
	String msg= "로그인에 실패 하였습니다.";
	
	boolean result =mmgr.loginMember(id,pwd);
	String value =null;
	//Home_module 클래스안의 loginmember로 DB하고 일치하는 지 확인
	
	if(result){
		/* session.setAttribute("idKey", id); //(name,value) */
		session.setAttribute("key", id);
		session.setMaxInactiveInterval(60*120);
		msg= " 로그인에 성공 하였습니다.";
		value=(String)session.getAttribute("key");
		
	}
%>
<script>
	alert("<%=msg%>");
	location.href="<%=url%>";
</script>