package board.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;

// 게시글 페이지별 조회 처리용 컨트롤러 
@WebServlet("/boardlist")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardListServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");

		int currentPage = 1;
		int limit = 2;

		if (request.getParameter("page") != null)
			currentPage = Integer.parseInt(request.getParameter("page"));

		BoardService bservice = new BoardService();

		int listCount = bservice.getListCount();
		ArrayList<Board> list = bservice.selectList(currentPage, limit);
		int maxPage = (int) ((double) listCount / limit + 0.9);
		int startPage = (((int) ((double) currentPage / limit + 0.9)) - 1) * limit + 1;
		int endPage = startPage + limit - 1;
		if (maxPage < endPage)
			endPage = maxPage;
		RequestDispatcher view = null;
		if (list != null && list.size() > 0) {
			System.out.println("list보기" + list);
			view = request.getRequestDispatcher("WEB-INF/views/board/boardListView.jsp");
			request.setAttribute("list", list);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("listCount", listCount);

			view.forward(request, response);
		} else {
			view = request.getRequestDispatcher("WEB-INF/views/board/boardError.jsp");
			request.setAttribute("message", "게시글 페이지별 조회 실패");
			view.forward(request, response);
		}
	}
}