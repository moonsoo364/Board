package Board_module;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/Homepage_dev/BoardPost")
public class BoardPostServlet extends HttpServlet{
	
	//post.jsp에 form의 method가 post이기 때문에 dopost, 
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");//UTF-8 방식으로 내용사용
		req.setCharacterEncoding("UTF-8");
		String board_title=req.getParameter("title");
		String board_content=req.getParameter("content");
		System.out.printf("게시판 제목:%s\n내용:%s\n",board_title,board_content);
		BoardMgr Bmgr = new BoardMgr();
		Bmgr.insertBoard(req);//매개변수로 insertboard 호출
		
		
		if((board_title==null)||(board_content==null))
		{
			System.out.print("게시판의 제목과 내용이 넘어오지 않았습니다.\n");
			resp.sendRedirect("post.jsp");
			
		}else {
			resp.sendRedirect("list.jsp");
			System.out.print("데이터가 정상적으로 저장됬습니다.\n");
		}
	}
	
	
}
