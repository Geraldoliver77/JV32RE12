
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.makerDAO;
import dto.makerDTO;

/**
 * Servlet implementation class MakerRegistServlet
 */
@WebServlet("/MakerRegistServlet")
public class MakerRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MakerRegistServlet() {
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

		request.setCharacterEncoding("UTF-8");

		// 変数を設定する
		String strJspName = "";
		String button = request.getParameter("send");
		String finish = "登録が完了いたしました";
		String idError = "";
		int flag = 1;

		makerDAO makerDAO = new makerDAO();
		makerDTO input = new makerDTO();
		
		// SESSIONを立ち上げる
		HttpSession session = request.getSession(false);

		// データベースの変数を設定する
		Connection con = null;
		PreparedStatement prst = null;

		// SESSIONが空の場合
		if (session == null) {
			session = request.getSession(true);
		}

		String strId = request.getParameter("genreid");

		if (strId.length() > 20 || strId.length() < 1) {

			idError = "メーカ名を２０文字以内で入力してください";
			flag = -1;
		}

		// 問題が無いであれば
		if (flag == 1) {

			input.setMaker(strId);
			
			int num = makerDAO.register(input);

			strJspName = "./adminPage.jsp";

			// Set up session from here
			session.setAttribute("success", finish);
			request.setAttribute("success", finish);

		} else {
			strJspName = "./makerRegister.jsp";
		}

		// 前へ戻る
		if ("back".equals(button)) {
			strJspName = "./adminPage.jsp";
		}
		// セッションをセットする
		session.setAttribute("makerError", idError);
		request.setAttribute("makerError", idError);

		// 次のページへ
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
