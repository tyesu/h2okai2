package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ToPurchase")
public class ToPurchase extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 購入画面にとぶ
		RequestDispatcher dispatcher =
				request.getRequestDispatcher(
						"WEB-INF/PurchaseScreen.jsp");
		dispatcher.forward(request, response);
	}

}
