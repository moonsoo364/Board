package Board_module;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/Homepage_dev/BoardUpdate")
public class BoardUpdateServlet extends HttpServlet{
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		HttpSession session =req.getSession();
		PrintWriter out =resp.getWriter();
		
		BoardMgr bMgr =new BoardMgr();
		BoardBean bean =(BoardBean) session.getAttribute("bean");
		String nowPage=req.getParameter("nowPage");
		System.out.printf("nowPage=%s \n",nowPage);
		String title=req.getParameter("title");
		String Content=req.getParameter("content");
		String ip=req.getParameter("ip");
		System.out.printf("title=%s Content=%s ip=%s \n",title,Content,ip);
		
		
		bean.setTitle(req.getParameter("title"));//글 제목을 수정함
		bean.setContent(req.getParameter("content"));//글 내용을 수정함
		bean.setIp(req.getParameter("ip"));//작성자의 IP를 수정함
		bean.setSort(req.getParameter("sort"));
		bMgr.updateBoard(bean);
		String url="read.jsp?num="+bean.getNum()+"&nowPage="+nowPage;
		resp.sendRedirect(url);

		
		
		
	}

}
