<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="bMgr" class="Board_module.BoardMgr"/>
 
<%	
	request.setCharacterEncoding("UTF-8");
	String nowPage = request.getParameter("nowPage");
	int num = Integer.parseInt(request.getParameter("num"));
	System.out.printf("nowPage=%s num=%d \n",nowPage,num);
	String msg="게시글을 삭제합니다.";
	
%>
<script>

if(!confirm("메시지를 삭제하시겠습니까?")){
	history.go(-1);//no
}else{
	<%bMgr.deleteBoard(num);%>
	window.location.href="list.jsp";
}
</script>