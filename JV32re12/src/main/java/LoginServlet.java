
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.genreDAO;
import dao.makerDAO;
import dao.productDAO;
import dao.userDAO;
import dto.genreDTO;
import dto.makerDTO;
import dto.productDTO;
import dto.userDTO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		String strName = request.getParameter("userid");
		String strPassword = request.getParameter("password");
		String strJspName = "./index.jsp";
		String strButton = request.getParameter("next");
		String idError = "";
		String passError = "　";
		String userName = "　";

		int flag = 2;

		// SESSIONを立ち上げる
		HttpSession session = request.getSession(false);

		// SESSIONが空の場合
		if (null == session) {
			session = request.getSession(false);
		}

		//DAOとDTOをインスタンス化する
		ArrayList<userDTO> userList = new ArrayList<userDTO>();
		userDAO userDAO = new userDAO();
		
		ArrayList<productDTO> productList = new ArrayList<productDTO>();
		productDAO productDAO = new productDAO();
		
		ArrayList<genreDTO> genreList = new ArrayList<genreDTO>();
		genreDAO genreDAO = new genreDAO();
		
		ArrayList<makerDTO> makerList = new ArrayList<makerDTO>();
		makerDAO makerDAO = new makerDAO();

		ArrayList<productDTO> cartList = new ArrayList<productDTO>();
		productDTO input = new productDTO();
		
		// 次のページへ

		if ("next".equals(strButton)) {
		
			Connection con = null;
			PreparedStatement prst = null;
			ResultSet rs = null;
		
			userList = userDAO.findAllUser();
			productList = productDAO.findAllProduct();
			genreList = genreDAO.findAllGenre();
			makerList = makerDAO.findAllMaker();

	
			session.setAttribute("productList", productList);
			request.setAttribute("productList", productList);


			session.setAttribute("genreList", genreList);
			request.setAttribute("genreList", genreList);

	
			session.setAttribute("makerList", makerList);
			request.setAttribute("makerList", makerList);

			// アドミンがログインする場合
			if ("admin".equals(strName) && "admin".equals(strPassword)) {

				strJspName = "./adminPage.jsp";
			}

			// エラーを処理する
			if (strName.length() < 1) {

				idError = "文字入力してください";

			}

			if (strName.length() > 10) {
				idError = "文字は10以内で入力してください";
				flag -= 1;

			}
			if (strPassword.length() < 6 || strPassword.length() > 10) {

				passError = "パスワードは6-10以内で入力してください";
				flag -= 1;
			}
			
			//存在する場合エンドユーザに
			for (userDTO user : userList) {

				if (strName.equals(user.getId()) && strPassword.equals(user.getPassword())) {
					
					
					strJspName = "./userPage.jsp";
					userName = user.getUsername();
															
					input.setUserName(userName);
										
					cartList = productDAO.findCartUser(input);
					
					System.out.println("login"+ cartList);
					

				} else {
					idError = "IDまたはパスワードが違います";
					passError = "IDまたはパスワードが違います";
				}

			}

			if (strName.length() < 1) {

				idError = "文字入力してください";

			}

			if (strPassword.length() < 1) {

				passError = "文字入力してください";

			}

			session.setAttribute("userName", userName);
			request.setAttribute("userName", userName);

		}
		// ログアウト
		if ("logout".equals(strButton)) {

		}

		// 商品管理のページへ
		if ("productmanagement".equals(strButton)) {

			Connection con = null;
			PreparedStatement prst = null;
			ResultSet rs = null;

			//DAOをセットする
			makerList = makerDAO.findAllMaker();
			
			genreList = genreDAO.findAllGenre();

			productList = productDAO.findAllProduct();
			

			session.setAttribute("productList", productList);
			request.setAttribute("productList", productList);

			session.setAttribute("genreList", genreList);
			request.setAttribute("genreList", genreList);

			session.setAttribute("makerList", makerList);
			request.setAttribute("makerList", makerList);

			int a = productList.size();

			strJspName = "./productManagement.jsp";

			session.setAttribute("ListTable", "list");
			request.setAttribute("ListTable", "list");
		}

		// 在庫管理のページへ
		if ("quantitymanagement".equals(strButton)) {

			Connection con = null;
			PreparedStatement prst = null;
			ResultSet rs = null;

			productList = productDAO.findAllProduct();

			session.setAttribute("productList", productList);
			request.setAttribute("productList", productList);

			int a = productList.size();

			strJspName = "./quantity.jsp";

			session.setAttribute("ListTable", "list");
			request.setAttribute("ListTable", "list");
		}

		if ("salesManagement".equals(strButton)) {

			strJspName = "./salesManagement.jsp";

		}

		session.setAttribute("cartList", cartList);
		request.setAttribute("cartList", cartList);
		// Set up session from here
		session.setAttribute("sessionName", strName);
		request.setAttribute("sessionName", strName);

		session.setAttribute("idErrors", idError);
		request.setAttribute("idErrors", idError);

		session.setAttribute("passErrors", passError);
		request.setAttribute("passErrors", passError);

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
