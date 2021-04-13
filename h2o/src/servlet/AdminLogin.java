package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cake;
import model.Human;


@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ログイン送信値受け取り
		Human account =	(Human) request.getAttribute("requestAccount");
		String name = account.getUser_mei();					// 入力された名前
		String pass = account.getPassword();					// 入力されたパス

		// 管理者インスタンスを受け取る
		Human admin = (Human) request.getAttribute("admin");	// "admin"の中身はアカウント
		String adminName = admin.getUser_mei();					// 管理者の名前
		String adminPass = admin.getPassword();					// 管理者のパス

		// 管理者ならログイン
		String go = null;
		HttpSession session = request.getSession();				// スコープ用意
		List<Cake> cakes =										// 商品データ準備
				(List<Cake>) request.getAttribute("cakes");



		// adminNameさえ合っていれば通過できてしまう


		if (adminName.equals(name) && adminPass.equals(pass)) {	// 入力値と管理者は一致するか
			go = "WEB-INF/admin/command.jsp";					// 管理者コマンド画面へ
			admin.login();										// ログイン状態に
			session.setAttribute("admin", admin);
			session.setAttribute("cakes", cakes);
		} else {
			go = "admin/administrator.jsp";						// ログイン画面へ送り返す
		}

		// フォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher(go);
		dispatcher.forward(request, response);
	}

}
