package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CakeDAO;

// 商品の登録処理

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// エンコ設定、値の受け取り
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String stock = request.getParameter("stock");
		String text = request.getParameter("text");

		// 数値に変換、空文字等の対策
		Integer price2 = 0;
		Integer stock2 = 0;
		CakeDAO.error();				// 失敗状態で初期化


		// 適切な入力値なら実行
		if ((id.length() >= 2) && (name.length() >= 2) &&
				(price2 >= 0) && (stock2 >= 0) && (text.length() >= 2)) {

			// 変換ミスに備えて
			try {
				price2 = Integer.parseInt(price);	// ここで変換
				stock2 = Integer.parseInt(stock);
				CakeDAO.success();
			} catch (NumberFormatException e) {
				CakeDAO.error();					// 状態変化：失敗
			}
		}


		// すでに失敗状態ならスルー
		if (CakeDAO.isDone() != 2) {

			// 登録メソッド
			CakeDAO dao = CakeDAO.getInstance();

			try {
				dao.register(id, name, price2, stock2, text);
				CakeDAO.success();			// 状態変化：成功
			} catch (SQLException e) {
				CakeDAO.error();			// 状態変化：失敗で上書き
			}
		}


		// フォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher(
						"WEB-INF/admin/purchasing.jsp");
		dispatcher.forward(request, response);
	}

}
