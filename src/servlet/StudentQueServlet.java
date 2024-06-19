package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




/**
 * Servlet implementation class StudentQueServlet
 */
@WebServlet("/StudentQueServlet")
public class StudentQueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String buttonClicked = request.getParameter("button");
        // ボタンごとに異なる処理を行う
        if ("button1".equals(buttonClicked)) {
            response.sendRedirect("/D1/StudentQueSubServlet");
        } else if ("button2".equals(buttonClicked)) {
            response.sendRedirect("/D1/StudentQueSubResultServlet");
        } else if ("button3".equals(buttonClicked)) {
            response.sendRedirect("/D1/StudentQueHisServlet");
        } else if ("button4".equals(buttonClicked)) {
            response.sendRedirect("/D1/QueResultServlet");
        } else {
            // 何も該当しない場合の処理いる？
        }
		doGet(request, response);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StudentQueServlet.jsp");
		dispatcher.forward(request, response);
	}



}
