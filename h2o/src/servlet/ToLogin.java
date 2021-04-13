package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.CartCounter;


@WebServlet("/ToLogin")
public class ToLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// エンコ設定、受信
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");

		// ユーザー情報を持つインスタンスを生成
		Account account = new Account(name, pass);

		// ユーザー情報をセッションへ
		HttpSession session = request.getSession();
		session.setAttribute("Account", account);

		// カートカウンターをセッションへ
		CartCounter cartCounter = new CartCounter();
		session.setAttribute("cartCounter", cartCounter);

		// Loginサーブレットへ
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("Login");
		dispatcher.forward(request, response);
	}
}