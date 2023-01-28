
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SalesManagementServlet
 */
@WebServlet("/SalesManagementServlet")
public class SalesManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SalesManagementServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 変数を設定する
		String strButton = request.getParameter("next");

		String narrow = request.getParameter("narrow");

		String order = request.getParameter("order");

		String strJspName = "";
		
		//検索ボタンが押されたら
		if ("search".equals(strButton)) {
			strJspName = "searchSales.jsp";
		}

		//前へ戻る
		if ("back".equals(strButton)) {
			strJspName = "adminPage.jsp";
		}

		if ("backSearch".equals(strButton)) {
			strJspName = "adminPage.jsp";
		}

		RequestDispatcher rd = request.getRequestDispatcher(strJspName);
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
