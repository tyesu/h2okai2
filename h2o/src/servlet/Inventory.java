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

// 入力値を元にSQLのupdate文を実行する	.................//

@WebServlet("/Inventory")
public class Inventory extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// パラメータの受信
		// 空文字送信 → 商品名：空白文字、個数：null

		request.setCharacterEncoding("UTF-8");
		String itemName = request.getParameter("item");		// 商品名取得
		Integer itemQuantity = null;						// 個数取得

		try {
			// 空文字（null??）をパースすると基本的に止まる？
			itemQuantity = Integer.parseInt(request.getParameter("stock"));

		} catch (NumberFormatException e) {
			// 空送信なら何もしない
		}

		// 在庫数を更新する
		// 商品名が０文字超 かつ 個数がＮＵＬＬでない かつ 個数が０個以上
		if (itemName.length() > 0 && itemQuantity != null && itemQuantity >= 0) {

			CakeDAO dao = CakeDAO.getInstance(); // インスタンス取得

			try {

				dao.updateInventory(itemName, itemQuantity);	// update実行
				CakeDAO.success();								// 処理成功状態

			} catch (ClassNotFoundException | SQLException e) {
				// 何もしない
			}

		// 正しくない入力値の送信であれば
		} else {
			CakeDAO.error();										// 処理失敗状態
		}

		// フォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher(
						"WEB-INF/admin/inventory.jsp");
		dispatcher.forward(request, response);
	}

}
