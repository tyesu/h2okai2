package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CakeDAO;
import model.Cake;

@WebServlet("/ToItem")
public class ToItem extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// エンコ設定
		request.setCharacterEncoding("UTF-8");
		String to = request.getParameter("select");

		// 全ケーキを取得していく
		CakeDAO dao = CakeDAO.getInstance();
		List<Cake> cakes = null;

		try {

			cakes = dao.findAll();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// リストから画面で選択したケーキを取得
		for (Cake c: cakes) {

			// 商品IDが一致するものを取得
			if (c.getShohin_id().equals(to)) {
				Cake cake = new Cake(
						c.getShohin_id(),
						c.getShohin_mei(),
						c.getHanbai_tanka(),
						c.getZaikosu(),
						c.getBun()
				);

				// 単体の商品画面で使えるようにする
				request.setAttribute("cake", cake);
			}
		}

		// フォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/product.jsp");
		dispatcher.forward(request, response);
	}

}