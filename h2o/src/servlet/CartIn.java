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

import model.Account;
import model.Cake;
import model.CartCounter;
import model.InventoryCheck;



@WebServlet("/CartIn")
public class CartIn extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// エンコ設定
		request.setCharacterEncoding("UTF-8");
		String cakeId = request.getParameter("cake");

		// セッションから呼ぶ
		HttpSession session = request.getSession();
		List<Cake> cakes = (List<Cake>) session.getAttribute("cakes");
		Account account = (Account) session.getAttribute("Account");
		CartCounter cartCounter = (CartCounter) session.getAttribute("cartCounter");

		// 在庫数の確認をする。カート点数が在庫数を超えていたらtrue
		InventoryCheck check = new InventoryCheck(cartCounter, cakes);
		boolean breakLimit = false;

		try {
			// リストを走査
			for (Cake c: cakes) {

				// リクエストバラメータと同じ
				if ((cakeId.equals(c.getShohin_id()))) {

					// 在庫数 < カートの商品数でであるか
					breakLimit = check.isBreakLimit(c.getShohin_mei());

					// カートにいれる
					account.setCake(c);
					session.setAttribute("Account", account);

					// カートの商品、点数を把握
					cartCounter.countUp(c.getShohin_mei());
					session.setAttribute("cartCounter", cartCounter);

					// 在庫数以上にカートへ詰め込んでいるか
					if (breakLimit == true) {
						account.toggleBreakLimit(); 				// 状態変化
						account.setCakeUndo();						// カートへ追加の取り消し
						cartCounter.countDown(c.getShohin_mei());	// 同様に取り消し
						session.setAttribute("Accoun", account);
					}
				}
			}

		} catch (NullPointerException e) {
			// 何もしない
		}

		// フォワード

		// デフォの行先
		String to = "/WEB-INF/CartIn.jsp";

		// 在庫数を超えるなら
		if (breakLimit == true) {
			to = "/Login";					// 元のページへもどる
		} else {
			to = "/WEB-INF/CartIn.jsp";		// カートページへとぶ
		}

		RequestDispatcher dispatcher =
				request.getRequestDispatcher(to);
		dispatcher.forward(request, response);
	}

}