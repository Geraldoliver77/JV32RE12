
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
import dto.genreDTO;
import dto.makerDTO;
import dto.productDTO;

/**
 * Servlet implementation class UserPurchaseServlet
 */
@WebServlet("/UserPurchaseServlet")
public class UserPurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserPurchaseServlet() {
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
		// データベースの設定
		
		Connection con = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		ResultSet rsProduct = null;

		PreparedStatement ps = null;
		java.sql.Statement statement = null;

		// SESSIONを立ち上げる
		HttpSession session = request.getSession(false);

		// 変数を設定する
		String strJspName = "";
		String strButton = request.getParameter("next");

		String searchName = "";

		searchName = request.getParameter("searchName");

		String makerName = "";

		makerName = request.getParameter("searchMaker");

		String genreName = "";

		genreName = request.getParameter("searchGenre");

		String finish = "カートに追加いたしました";

		String productId = request.getParameter("id");
		String productName = request.getParameter("name");
		String productPrice = request.getParameter("price");
		String newQuantity = request.getParameter("newQuantity");


		ArrayList<productDTO> productListAll = new ArrayList<productDTO>();
		ArrayList<productDTO> productListId = new ArrayList<productDTO>();

		productDAO productDAOAll = new productDAO();
		productDAO productDAO = new productDAO();
		productDAO purchaseDAO = new productDAO();
		productDTO input = new productDTO();
		
		ArrayList<productDTO> searchList = new ArrayList<productDTO>();
		ArrayList<genreDTO> genreList = new ArrayList<genreDTO>();
		genreDAO genreDAO = new genreDAO();

		// MakerList arrayを作成する
		ArrayList<makerDTO> makerList = new ArrayList<makerDTO>();
		makerDAO makerDAO = new makerDAO();

		// ログアウトボタンが押されたら
		if ("logout".equals(strButton)) {

			strJspName = "./index.jsp";
		}

		// ユーザページへもどる
		if ("backAdmin".equals(strButton)) {

			strJspName = "./userPage.jsp";
		}
		
		// ユーザページへもどる
		if ("back".equals(strButton)) {

			strJspName = "./userPage.jsp";
		}

		// カートへ追加

		if ("追加".equals(strButton)) {

			String addValue = request.getParameter("add");
			String addId = request.getParameter("HiddenId");

			System.out.println(addValue + "values");

			System.out.println(addId + "hiddenvalues");

			strJspName = "./searchUserProduct.jsp";
		}

		// 検索しましたら
		if ("search".equals(strButton)) {
			strJspName = "./searchUserProduct.jsp";

			String stringLow = request.getParameter("searchPriceLow");

			// 下限は空の場合
			if ("".equals(stringLow)) {
				stringLow = "0";
			}

			String stringHigh = request.getParameter("searchPriceHigh");

			// 上限は空の場合
			if ("".equals(stringHigh)) {
				stringHigh = "0";
			}
			int priceLow = Integer.parseInt(stringLow);
			int priceHigh = Integer.parseInt(stringHigh);

			input.setSearchName(searchName);
			input.setSearchMaker(makerName);
			input.setSearchGenre(genreName);
			input.setSearchPriceHigh(stringHigh);
			input.setSearchPriceLow(stringLow);

			searchList = productDAO.searchProductUser(input);

			request.setAttribute("searchList", searchList);
		}

		
		
		// 仮登録する、データベースへ
		if ("addCart".equals(strButton)) {
			strJspName = "./userPage.jsp";

			productId = request.getParameter("id");
			productName = request.getParameter("name");
			productPrice = request.getParameter("price");
			newQuantity = request.getParameter("newQuantity");

			int totalPrice = 0;

			totalPrice = Integer.parseInt(productPrice) * Integer.parseInt(newQuantity);

			String userName = (String) session.getAttribute("userName");
			
			input.setId(productId);
			input.setProductName(productName);
			input.setTotalPrice(totalPrice);
			input.setQuantity(newQuantity);
			input.setUserName(userName);

			int num = productDAO.registerCart(input);
			
			strJspName = "./userPage.jsp";

		}
		
		String deleteCartId = null;
		deleteCartId = request.getParameter("delCart");
		// カートにあるデータを削除する
		if(deleteCartId != null) {
						
			strJspName = "userPage.jsp";
			System.out.println(deleteCartId + "deleteCart");
												
			input.setCartId(deleteCartId);
									
			int num = productDAO.deleteCart(input);

			// Set up session from here
			session.setAttribute("success", "商品削除いたしました");
			request.setAttribute("success", "商品削除いたしました");

		}
		
		String getCartInfo = null;
		getCartInfo = request.getParameter("cartinfo");
		
		//カートに追加する
		if(getCartInfo != null) {
		String getCartId = request.getParameter("hiddenCartId");
			
			input.setId(getCartId);
			
			productListId = productDAO.findIdProduct(input);
			
			System.out.println("getCartId :" + getCartId);
						
			strJspName = "./shoppingCart.jsp";
		}

		String userName = (String) session.getAttribute("userName");

		System.out.println(userName);
		
		input.setUserName(userName);
		
		ArrayList<productDTO> cartList = new ArrayList<productDTO>();
		cartList = productDAO.findCartUser(input);

		int checkCart = cartList.size();

		System.out.println("cartList :" + cartList);


		// マイカーとの一覧ページへ
		if ("myCart".equals(strButton)) {
			strJspName = "./checkOut.jsp";

		}

		// 購入確定の場合
		if ("Purchase".equals(strButton)) {

			input.setUserName(userName);
			purchaseDAO.purchase(input);

			strJspName = "userPage.jsp";
			
			session.setAttribute("success", "ご購入いただきありがとうございます");
			request.setAttribute("success", "ご購入いただきありがとうございます");
		}
				
	
		//前に戻る
		if ("Back".equals(strButton)) {
			strJspName = "userPage.jsp";
		}

		productListAll = productDAOAll.findAllProduct();
		genreList = genreDAO.findAllGenre();
		makerList = makerDAO.findAllMaker();
		
		session.setAttribute("cartList", cartList);
		request.setAttribute("cartList", cartList);
		
		session.setAttribute("productListId", productListId);
		request.setAttribute("productListId", productListId);
		
		session.setAttribute("productList", productListAll);
		request.setAttribute("productList", productListAll);

		session.setAttribute("makerList", makerList);
		request.setAttribute("makerList", makerList);

		session.setAttribute("genreList", genreList);
		request.setAttribute("genreList", genreList);


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
