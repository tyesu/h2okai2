package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.LoginLogic;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// アカウント情報の呼び出し
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("Account");

		// ログイン処理

		LoginLogic loginLogic = new LoginLogic();
		boolean isLogin = false;

		try {

			isLogin = loginLogic.execute(account);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// ログイン成功時の処理
		if(isLogin) {

			// ユーザー情報をセッションスコープに保存
			session.setAttribute("Account", account);

			// ログインOK結果画面にフォワード
			RequestDispatcher dispatcher =
					request.getRequestDispatcher(
							"/WEB-INF/HomeScreen.jsp");
			dispatcher.forward(request, response);

		// ログイン失敗時の処理
		} else {

			// ログインNG画面にフォワード
			RequestDispatcher dispatcher =
					request.getRequestDispatcher(
							"/WEB-INF/failedScreen.jsp"
			);
			dispatcher.forward(request, response);
		}
	}
}