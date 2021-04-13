package filter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import dao.CakeDAO;
import model.Account;
import model.Cake;
import model.Human;


@WebFilter("/AdminLogin") // AdminLoginを通過するときだけに動作する
public class PreLogin implements Filter {
	public void init(FilterConfig fConfig) throws ServletException {}
	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		// 必要なものを生成してリクエストスコープへ

		// フォームから送られてきた名前とパス

		request.setCharacterEncoding("UTF-8");
		String requestName = request.getParameter("id");				// 送信された名前
		String requestPass = request.getParameter("pass");				// 送信されたパス

		Human requestAccount = new Account(requestName, requestPass);	// 情報をアカウントに詰め込む
		request.setAttribute("requestAccount", requestAccount);

		// 管理者生成

		Human admin = new Account("admin", "admin");					// 管理者としてのアカウント
		request.setAttribute("admin", admin);

		// 商品データを準備

		CakeDAO dao = CakeDAO.getInstance();
		List<Cake> cakes = null;

		try {
			cakes = dao.findAll();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("cakes", cakes);


		chain.doFilter(request, response);
	}
}
