
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

import beans.ProductInfoBean;
import dao.genreDAO;
import dao.makerDAO;
import dao.productDAO;
import dto.genreDTO;
import dto.makerDTO;
import dto.productDTO;

/**
 * Servlet implementation class ProductManageServlet
 */
@WebServlet("/ProductManageServlet")
public class ProductManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductManageServlet() {
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

		ProductInfoBean ProductInfo = new ProductInfoBean();
		
		// 変数を設定する
		String strJspName = "./productManagement.jsp";
		String button = request.getParameter("send");
		String finish = "登録が完了いたしました";
		int flag = 4;

		String nameError = "";
		String priceError = "";
		String makerError = "";
		String genreError = "";
		String sessionId = "";
		String getId = "";

		String productName = "";
		String price = "";
		String makerName = "";
		String genreName = "";

		String searchKeyword = request.getParameter("search");

		// SESSIONを立ち上げる
		HttpSession session = request.getSession(false);

		sessionId = request.getParameter("id");
		getId = request.getParameter("id");
		
		
		
		String getDel = null;
		getDel = request.getParameter("del");
		
		String getUpd = null;
		getUpd = request.getParameter("upd");
		
		String getQnt = null;
		getQnt = request.getParameter("qnt");
		
	
	
		// データベースの変数を設定する
		Connection con = null;
		PreparedStatement prst = null;
		ResultSet rs = null;

		productDAO productDAO = new productDAO();
		productDTO input = new productDTO();
		
		ArrayList<productDTO> productList = new ArrayList<productDTO>();
		ArrayList<productDTO> productListId = new ArrayList<productDTO>();
					
		ArrayList<genreDTO> genreList = new ArrayList<genreDTO>();
		genreDAO genreDAO = new genreDAO();
		
		ArrayList<makerDTO> makerList = new ArrayList<makerDTO>();
		makerDAO makerDAO = new makerDAO();

		ArrayList<productDTO> searchList = new ArrayList<productDTO>();
		

		session.setAttribute("theId", getId);
		request.setAttribute("theId", getId);

		// 次のページへ
		if ("send".equals(button)) {

			// ゲットパラメーターを取得する
			productName = request.getParameter("productname");
			price = request.getParameter("price");
			makerName = request.getParameter("maker");
			genreName = request.getParameter("genre");

			session.setAttribute("productname", productName);
			request.setAttribute("productname", productName);

			session.setAttribute("price", price);
			request.setAttribute("price", price);

			session.setAttribute("maker", makerName);
			request.setAttribute("maker", makerName);

			session.setAttribute("genre", genreName);
			request.setAttribute("genre", genreName);
			
			
			ProductInfo.setProductName(productName);
			ProductInfo.setPrice(price);
			ProductInfo.setMakerName(makerName);
			ProductInfo.setGenreName(genreName);

			if (productName.length() > 20 || productName.length() < 1) {

				nameError = "商品名を２０文字以内で入力してください";
				flag = -1;
				request.setAttribute("nameError", nameError);
			}
			if (price.length() < 1) {

				priceError = "価格を入力してください";
				flag = -1;
				request.setAttribute("priceError", priceError);
			}

			// Maker空の場合
			if ("blank".equals(makerName)) {
				System.out.println("maker is blank");
				makerError = "メーカ名を選択してください";
				flag = -1;
				request.setAttribute("makerError", makerError);
			}

			// Genre空の場合
			if ("blank".equals(genreName)) {
				System.out.println("genre is blank");
				genreError = "ジャンル名を選択してください";
				flag = -1;
				request.setAttribute("genreError", genreError);
			}

			// エラーがなければ登録する
			if (flag == 4) {
				strJspName = "./check.jsp";

			}

		}

		//登録する場合
		if ("register".equals(button)) {
			
			productName = (String) session.getAttribute("productname");
			price = (String) session.getAttribute("price");
			makerName = (String) session.getAttribute("maker");
			genreName = (String) session.getAttribute("genre");

			input.setProductName(productName);
			input.setPrice(price);
			input.setMakerName(makerName);
			input.setGenreName(genreName);

			int num = productDAO.register(input);

			// Set up session from here
			session.setAttribute("success", finish);
			request.setAttribute("success", finish);

			strJspName = "./adminPage.jsp";
		}

		// 前へ戻る
		if ("back".equals(button)) {
			strJspName = "./adminPage.jsp";
		}

		if ("backAdmin".equals(button)) {
			strJspName = "./adminPage.jsp";
		}

		if ("backregister".equals(button)) {
			strJspName = "./adminPage.jsp";

		}

		// 検索ボタンが押されたら
		if ("search".equals(button)) {
			strJspName = "./search.jsp";
			input.setSearch(searchKeyword);
			
			searchList = productDAO.searchProduct(input);
		
			request.setAttribute("searchList", searchList);
			RequestDispatcher rd = request.getRequestDispatcher(strJspName);
			rd.forward(request, response);
		}

		// 削除するページへ
		if ("delete".equals(button)) {
					
			strJspName = "./deleteFile.jsp?id=" + sessionId;
		}
		
		//削除する場合
		if(getDel != null) {
			
			input.setId(getDel);
			System.out.println("get DEL" + getDel);
			
			
			strJspName = "./deleteFile.jsp?id="+getDel;
		}
		
		//更新する場合
		if(getUpd != null) {
			
			input.setId(getUpd);
			System.out.println("get UPD" + getDel);
						
			strJspName = "./updateFile.jsp?id="+getUpd;
		}
		
		//在庫変更する場合
		if(getQnt != null) {
			
			input.setId(getQnt);
			System.out.println("get Qnt" + getQnt);
						
			strJspName = "./addQuantity.jsp?id="+getQnt;
		}

		//DAOクラスにセットする
		productListId = productDAO.findIdProduct(input);
		productList = productDAO.findAllProduct();
		
		genreList = genreDAO.findAllGenre();
		makerList = makerDAO.findAllMaker();
		
		session.setAttribute("productListId", productListId);
		request.setAttribute("productListId", productListId);

		session.setAttribute("PRODUCTINFO", ProductInfo);
		request.setAttribute("PRODUCTINFO", ProductInfo);
				
		session.setAttribute("productList", productList);
		request.setAttribute("productList", productList);

		session.setAttribute("genreList", genreList);
		request.setAttribute("genreList", genreList);

		session.setAttribute("makerList", makerList);
		request.setAttribute("makerList", makerList);		

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
