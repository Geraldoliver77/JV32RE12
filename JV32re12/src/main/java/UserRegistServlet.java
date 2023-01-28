
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

import beans.UserInfoBean;
import dao.userDAO;
import form.userForm;

/**
 * Servlet implementation class UserRegistServlet
 */
@WebServlet("/UserRegistServlet")
public class UserRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserRegistServlet() {
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
		
//		UserInfoBeanクラスをインスタンス化する
		UserInfoBean UserInfo = new UserInfoBean();

		// 変数を設定する
		String strJspName = "";
		String button = request.getParameter("send");
		String finish = "登録が完了いたしました";
		String idError = "";
		String nameError = "";
		String passError = "";
		int flag = 3;

		// セッションを立ち上げる
		HttpSession session = request.getSession(false);

		// データベースのデータを設定する
		Connection con = null;
		PreparedStatement prst = null;

		// SESSIONが空の場合
		if (session == null) {
			session = request.getSession(true);
		}
		
		userDAO userDAO = new userDAO();

		String strId = "";
		String strName = "";
		String strPass = "";
		String strGender = "";
		String strBirth = "";

		if ("send".equals(button)) {

			// 問題がなければ次のページへ

			strId = request.getParameter("userid");

			strName = request.getParameter("username");

			strPass = request.getParameter("password");

			strGender = request.getParameter("gender");

			strBirth = request.getParameter("birth");

			// ゲットパラメーターを取得する
			
			UserInfo.setId(strId);
			UserInfo.setUsername(strName);
			UserInfo.setPassword(strPass);
			UserInfo.setGender(strGender);
			UserInfo.setBirth(strBirth);
			
			
			System.out.println(strId);
			
			session.setAttribute("userid", strId);
			request.setAttribute("userid", strId);

			session.setAttribute("username", strName);
			request.setAttribute("username", strName);

			session.setAttribute("password", strPass);
			request.setAttribute("password", strPass);

			session.setAttribute("gender", strGender);
			request.setAttribute("gender", strGender);

			session.setAttribute("birth", strBirth);
			request.setAttribute("birth", strBirth);

			// ユーザIDのエラー
			if (strId.length() > 10 || strId.length() < 1) {
				System.out.println("iderror");
				idError = "ユーザIDを１０文字以内で入力してください";
				flag = -1;

			}

			// ユーザ名のエラー
			if (strName.length() > 20 || strName.length() < 1) {
				System.out.println("nameerror");
				nameError = "名前を２０文字以内で入力してください";
				flag = -1;
			}

			// パスワードのエラー
			if (strPass.length() < 6 || strPass.length() > 10) {
				System.out.println("passerror");
				passError = "パスワードは6-10以内で入力してください";
				flag = -1;

			}

			if (flag == 3) {

				strJspName = "./check.jsp";
			} else {
				strJspName = "./userRegister.jsp";
			}
		}
		
		session.setAttribute("USERINFO", UserInfo);
		request.setAttribute("USERINFO", UserInfo);

		//ユーザ登録する場合
		if ("register".equals(button)) {
			strId = (String) session.getAttribute("userid");
			strName = (String) session.getAttribute("username");
			strPass = (String) session.getAttribute("password");
			strGender = (String) session.getAttribute("gender");
			strBirth = (String) session.getAttribute("birth");
			
			userForm input = new userForm();
			input.setId(strId);
			input.setUsername(strName);
			input.setPassword(strPass);
			input.setGender(strGender);
			input.setBirth(strBirth);
			
			int num = userDAO.register(input);
			
			// Set up session from here
			session.setAttribute("success", finish);
			request.setAttribute("success", finish);
											
			
			strJspName = "./adminPage.jsp";

			

		}

		// 前へ戻るボタン
		if ("back".equals(button)) {
			strJspName = "./adminPage.jsp";
		}
		if ("backregister".equals(button)) {
			strJspName = "./userRegister.jsp";

		}
		// セッションをセットする
		session.setAttribute("idError", idError);
		request.setAttribute("idError", idError);

		session.setAttribute("nameError", nameError);
		request.setAttribute("nameError", nameError);

		session.setAttribute("passError", passError);
		request.setAttribute("passError", passError);


						
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
