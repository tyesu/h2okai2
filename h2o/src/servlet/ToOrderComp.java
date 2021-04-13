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
import model.Purchase;

@WebServlet("/ToOrderComp")
public class ToOrderComp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/* ・注文完了画面へ移行
	 * ・商品の購入処理
	 * 	 *  */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 必要情報の取得
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("Account");
		CartCounter cartCounter = (CartCounter) session.getAttribute("cartCounter");

		// 購入処理
		Purchase purchase =
				new Purchase(account, cartCounter);
		boolean getCake = purchase.isGetCake();			// 購入できる状態か
		boolean isCartEnpty = purchase.isCartEnpty();	// カートは空か
		String path = null;

		// 残高が足りてカートの中身があれば
		if (getCake == true && isCartEnpty == false) {
			account.payment(cartCounter);			// 決済処理
			path = "WEB-INF/OrderCompleted.jsp";	// 行先：決済終了画面
			session.setAttribute("Account", account);

		// 残高が足りないなら
		} else {
			account.toggleFailed();				// 状態変化：購入不可
			session.setAttribute(				// 保存
					"Account", account);
			path = "WEB-INF/HomeScreen.jsp";	// 行先：ホーム画面

		}



		// 注文完了画面へフォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher(
						path);
		dispatcher.forward(request, response);
	}

}
