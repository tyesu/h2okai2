package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 管理者コマンド一覧へ飛ぶ.................//

@WebServlet("/GoHome")
public class GoHome extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 選択画面へ
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/admin/command.jsp");
		dispatcher.forward(request, response);
	}

}
