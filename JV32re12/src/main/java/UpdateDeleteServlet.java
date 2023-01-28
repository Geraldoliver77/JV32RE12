
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
 * Servlet implementation class UpdateDeleteServlet
 */
@WebServlet("/UpdateDeleteServlet")
public class UpdateDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateDeleteServlet() {
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
		String button = request.getParameter("send");

		String finish = "";
		String strJspName = "";
		String nameError = "";
		String getId = "";

		String productName = request.getParameter("productName");
		String productPrice = request.getParameter("productPrice");
		String productQuantity = request.getParameter("newquantity");
		
		//DTOとDAOクラスをインスタンス化する
		ArrayList<productDTO> productList = new ArrayList<productDTO>();
		ArrayList<productDTO> productListId = new ArrayList<productDTO>();
		productDAO productDAO = new productDAO();
		productDTO input = new productDTO();
					
		ArrayList<genreDTO> genreList = new ArrayList<genreDTO>();
		genreDAO genreDAO = new genreDAO();
		
		ArrayList<makerDTO> makerList = new ArrayList<makerDTO>();
		makerDAO makerDAO = new makerDAO();

		getId = request.getParameter("send");
				
		
		//SESSIONを立ち上げる
		HttpSession session = request.getSession(false);

		//データベースの変数を設定する
		Connection con = null;
		PreparedStatement prst = null;
		ResultSet rs = null;

		String getHiddenId = request.getParameter("HiddenId");
		
		System.out.println(getHiddenId);

		int intId = Integer.parseInt(getHiddenId);
		
		
						
		//削除を処理するページへ
		if ("delete".equals(button)) {

			input.setId(getHiddenId);		
			
			int numDel = productDAO.delete(input);

			finish = "商品を削除いたしました";

			// Set up session from here
			session.setAttribute("success", finish);
			request.setAttribute("success", finish);

			strJspName = "./adminPage.jsp";

		}

		//更新を処理するページへ Product Name , Price
		if ("update".equals(button)) {
			int price = Integer.parseInt(productPrice);
			
			if (productName.length() > 1 && productName.length() < 20 || price > 0) {
			input.setId(getHiddenId);
			input.setPrice(productPrice);
			input.setProductName(productName);
			
			int numUpdate = productDAO.update(input);
			
			}
			
			
			finish = "商品をアップデートいたしました";

			// Set up session from here
			session.setAttribute("success", finish);
			request.setAttribute("success", finish);

			strJspName = "./adminPage.jsp";

		}

		//更新を処理するページへ 在庫管理
		if ("変更".equals(button)) {
			
			int productQuantityInteger = Integer.parseInt(productQuantity);
		
				input.setId(getHiddenId);
				input.setQuantity(productQuantity);
				
				int numQuantity = productDAO.updateQuantity(input);

				finish = "商品をアップデートいたしました";

				// Set up session from here
				session.setAttribute("success", finish);
				request.setAttribute("success", finish);
			
			strJspName = "./adminPage.jsp";
		}

		//　前へ戻る
		if ("back".equals(button)) {
			strJspName = "./adminPage.jsp";
		}

		if ("backQuantity".equals(button)) {
			strJspName = "./adminPage.jsp";
		}
		
		//DAOクラスにセットする
		productList = productDAO.findAllProduct();
		genreList = genreDAO.findAllGenre();
		makerList = makerDAO.findAllMaker();
		productListId = productDAO.findIdProduct(input);
						
		session.setAttribute("productListId", productListId);
		request.setAttribute("productListId", productListId);
		
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
