<%@page import="Homepage_module.MemberBean"%>
<%@ page  contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="mMgr" class="Homepage_module.MemberMng"></jsp:useBean>
<%
	request.setCharacterEncoding("UTF-8");
	String id=request.getParameter("id");
	boolean result =mMgr.checkId(id);
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID 중복 체크</title>
</head>
<body>
<div align="center">
		<br/><b><%=id%></b>
		<%
			if (result) {
				out.println("는 이미 존재하는 ID입니다.<p/>");
			} else {
				out.println("는 사용 가능 합니다.<p/>");
			}
		%>
		<a href="#" onClick="self.close()">닫기</a>
	</div>

</body>
</html>