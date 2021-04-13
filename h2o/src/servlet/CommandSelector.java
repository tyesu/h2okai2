package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CommandSelector")
public class CommandSelector extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// エンコ設定、送信値受け取り
		request.setCharacterEncoding("UTF-8");
		String go = request.getParameter("go");

		// 行先を判定
		if (go.equals("purchasing")) {
			go = "WEB-INF/admin/purchasing.jsp";	// 商品登録

		} else if (go.equals("inventory")) {
			go = "WEB-INF/admin/inventory.jsp";		// 在庫調整

		} else if (go.equals("account")) {
			go = "WEB-INF/admin/account.jsp";		// アカウント操作

		} else if (go.equals("logout")) {
			go = "admin/administrator.jsp";			// ログアウト
		}

		// フォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher(go);
		dispatcher.forward(request, response);
	}

}
