<%@page import="java.util.concurrent.TimeUnit"%>
<%@page import="java.util.Timer"%>
<%@page import="java.sql.Time"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String msg="로그아웃 하셨습니다.";
	String value=null;
	value=(String)session.getAttribute("key");
	
	if(session.getAttribute("key")!=null){
		session.removeAttribute("key");
		System.out.printf("Id=%s 세션이 제거되었습니다.\n",value);
	}
	
	
	
%>
<script>

alert("<%=msg%>");
history.back();
</script>