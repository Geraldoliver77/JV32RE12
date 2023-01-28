package dao;
/*
 * Product DAO クラス
 * */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.DBConnection;
import dto.productDTO;

public class productDAO {
	private Connection con = null;

	ResultSet rs = null;
	PreparedStatement prst = null;
	
		public productDAO() {
			DBConnection con = new DBConnection();
			this.con = con.getConnection();
		}
		
		// DAOの値を全部返す
		public ArrayList<productDTO> findAllProduct() {
			ArrayList<productDTO> list = new ArrayList<productDTO>();

			String sql = "SELECT * FROM product12;";
			

			try {
				prst = con.prepareStatement(sql);
				rs = prst.executeQuery();
							
				while (rs.next()) {		
					productDTO product = new productDTO();
					product.setId(rs.getString("id"));
					product.setProductName(rs.getString("Product_Name"));
					product.setMakerName(rs.getString("Maker_Name"));
					product.setGenreName(rs.getString("Genre_Name"));
					product.setPrice(rs.getString("Price"));
					product.setQuantity(rs.getString("Quantity"));

					list.add(product);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (null != rs) {
						rs.close();
					}
					if (null != con) {
						con.close();
					}
					if (null != prst) {
						prst.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			System.out.println("findAll(Product) OK");
			return list;
		}
		
		// DAOの値を全部返す
				public ArrayList<productDTO> findIdProduct(productDTO input) {
					ArrayList<productDTO> list = new ArrayList<productDTO>();

					String sql = "SELECT * FROM product12 WHERE Id LIKE ? ;";
					

					try {
						prst = con.prepareStatement(sql);												
						prst.setString(1, input.getId());
						
						rs = prst.executeQuery();

						while (rs.next()) {		
							productDTO product = new productDTO();
							product.setId(rs.getString("id"));
							product.setProductName(rs.getString("Product_Name"));
							product.setMakerName(rs.getString("Maker_Name"));
							product.setGenreName(rs.getString("Genre_Name"));
							product.setPrice(rs.getString("Price"));
							product.setQuantity(rs.getString("Quantity"));

							list.add(product);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						try {
							if (null != rs) {
								rs.close();
							}
							if (null != con) {
								con.close();
							}
							if (null != prst) {
								prst.close();
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					System.out.println("findAll(ProductList) OK");
					return list;
				}
		
		public int register(productDTO input) {
			int num = 0;
			String sql = "INSERT INTO product12(Product_Name,Maker_Name,Genre_Name,Price,Quantity) VALUE(?,?,?,?,?)";

			try {
				prst = con.prepareStatement(sql);

				prst.setString(1, input.getProductName());
				prst.setString(2, input.getMakerName());
				prst.setString(3, input.getGenreName());
				prst.setString(4, input.getPrice());
				prst.setInt(5, 0);
			

				System.out.println(prst.toString());

				// 更新系SQL実行メソッド。
				prst.executeUpdate();

			}catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (null != rs) {
						rs.close();
					}
					if (null != prst) {
						prst.close();
					}
					if (null != con) {
						con.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
			return num;
		}

		public ArrayList<productDTO> searchProduct(productDTO input)  {
			ArrayList<productDTO> list = new ArrayList<productDTO>();

			System.out.println("keyword = >"+input.getSearch());
			
			String sql = "SELECT * FROM product12 WHERE Id LIKE ?";
				
			try {
				prst = con.prepareStatement(sql);
				
				prst.setString(1, "%" + input.getSearch() + "%");
				
				rs = prst.executeQuery();

				while (rs.next()) {		
					productDTO product = new productDTO();
										
					product.setId(rs.getString("id"));
					product.setProductName(rs.getString("Product_Name"));
					product.setMakerName(rs.getString("Maker_Name"));
					product.setGenreName(rs.getString("Genre_Name"));
					product.setPrice(rs.getString("Price"));
					product.setQuantity(rs.getString("Quantity"));

					list.add(product);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (null != rs) {
						rs.close();
					}
					if (null != con) {
						con.close();
					}
					if (null != prst) {
						prst.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			System.out.println("findAll(Product) OK");
			System.out.println(list);
			return list;
		}
		


		public ArrayList<productDTO> searchProductUser(productDTO input)  {
			ArrayList<productDTO> list = new ArrayList<productDTO>();
		
			int priceLow = Integer.parseInt(input.getSearchPriceLow());
			int priceHigh = Integer.parseInt(input.getSearchPriceHigh());
			System.out.println(priceLow);
			System.out.println(priceHigh);
		try {
			
			String sql;

			// セレクト分限度がないバージョン
			if (priceLow == 0 && priceHigh == 0) {
				sql = "SELECT * FROM product12 WHERE Product_Name LIKE ?" + " AND Maker_Name LIKE ?  "
						+ " AND Genre_Name LIKE ? ";

				// セレクト分限度がありバージョン
			} else {
				sql = "SELECT * FROM product12 WHERE Product_Name LIKE ?" + " AND Maker_Name LIKE ?  "
						+ " AND Genre_Name LIKE ? AND price BETWEEN '" + input.getSearchPriceLow()+ "'AND'" + input.getSearchPriceHigh() + "'";
			
			}
			prst = con.prepareStatement(sql);

			prst.setString(1, "%" + input.getSearchName() + "%");
			prst.setString(2, "%" + input.getSearchMaker() + "%");
			prst.setString(3, "%" + input.getSearchGenre() + "%");
			rs = prst.executeQuery();
			
			while (rs.next()) {		
				productDTO product = new productDTO();

				product.setId(rs.getString("id"));
				product.setProductName(rs.getString("Product_Name"));
				product.setMakerName(rs.getString("Maker_Name"));
				product.setGenreName(rs.getString("Genre_Name"));
				product.setPrice(rs.getString("Price"));
				product.setQuantity(rs.getString("Quantity"));

				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != rs) {
					rs.close();
				}
				if (null != con) {
					con.close();
				}
				if (null != prst) {
					prst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		System.out.println("findAll(ProductLis) OK");
		System.out.println(list);
		return list;
	}
		
		
		public int update(productDTO input) {
			int num = 0;
			String sql = "Update product12 set Product_Name=? ,  Price =? where id=?" ;
			
			try {
				prst = con.prepareStatement(sql);

				prst.setString(1, input.getProductName());
				prst.setString(2, input.getPrice());
				prst.setString(3, input.getId());

				num = prst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return num;
		}
		
		public int updateQuantity(productDTO input) {
			int num = 0;
			String sql = "Update product12 set Quantity=? where id=?" ;
			
			try {
				prst = con.prepareStatement(sql);

				prst.setString(1, input.getQuantity());				
				prst.setString(2, input.getId());

				num = prst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return num;
		}
		

		public int delete(productDTO input) {
			int num = 0;
			
			String sql = "DELETE FROM product12 where id=?" ;
			
			try {
				prst = con.prepareStatement(sql);
							
				prst.setString(1, input.getId());

				num = prst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return num;
		}
				
		public ArrayList<productDTO> findCartUser(productDTO input) {
			ArrayList<productDTO> list = new ArrayList<productDTO>();

			String sql = "SELECT * FROM cart12 WHERE buyer =  ? ;";
			

			try {
				prst = con.prepareStatement(sql);												
				prst.setString(1, input.getUserName());			
				rs = prst.executeQuery();
						
				while (rs.next()) {		
					productDTO product = new productDTO();
					product.setId(rs.getString("id"));
					product.setProductName(rs.getString("productName"));
					product.setPrice(rs.getString("price"));
					product.setQuantity(rs.getString("quantity"));
					
					list.add(product);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (null != rs) {
						rs.close();
					}
					if (null != con) {
						con.close();
					}
					if (null != prst) {
						prst.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			System.out.println("findAll(cartList) OK");
			return list;
		}
		
		public int registerCart(productDTO input) {
			int num = 0;
			String sql = "INSERT INTO cart12(productId,productName,price,quantity,buyer) VALUE(?,?,?,?,?)";

			try {
				prst = con.prepareStatement(sql);
				
				prst.setString(1, input.getId());
				prst.setString(2, input.getProductName());
				prst.setInt(3, input.getTotalPrice());
				prst.setString(4, input.getQuantity());
				prst.setString(5, input.getUserName());
			

				System.out.println(prst.toString());

				// 更新系SQL実行メソッド。
				prst.executeUpdate();

			}catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (null != rs) {
						rs.close();
					}
					if (null != prst) {
						prst.close();
					}
					if (null != con) {
						con.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
			System.out.println("num"+ num);
			return num;
		}
		

		public int deleteCart(productDTO input) {
			
			int num = 0;
			System.out.println("cartDAO"+  input.getId());
											
			try {
				String sql = "Delete FROM cart12 where id='" + input.getCartId() + "'";
								
				prst = con.prepareStatement(sql);
											
				num = prst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("num"+ num);
			return num;
		}
		
		
		public int purchase(productDTO input) {
			ArrayList<String> getQuantity = new ArrayList<String>();
			ArrayList<Integer> getProductId = new ArrayList<Integer>();
			ArrayList<String> getProductListQuantity = new ArrayList<String>();
			ArrayList<String> getProductArray = new ArrayList<String>();

			ArrayList<Integer> newIdArray = new ArrayList<Integer>();
			ArrayList<Integer> newQuantityArray = new ArrayList<Integer>();
			ArrayList<Integer> salesQuantityArray = new ArrayList<Integer>();

			
					
			try {
				 
				System.out.println("データベース接続");

				String sql = "SELECT * FROM cart12 WHERE buyer = '" + input.getUserName() + "'";

				prst = con.prepareStatement(sql);

				rs = prst.executeQuery();
				// 売り上げに登録する
				while (rs.next()) {
					ArrayList<String> record = new ArrayList<String>();

					record.add(rs.getString("productId"));
					record.add(rs.getString("productName"));
					record.add(rs.getString("quantity"));
					record.add(rs.getString("price"));

					String mysql = "INSERT INTO sales12(ProductId,ProductName,BuyerName,Quantity,Total)"
							+ " VALUE(?,?,?,?,?)";

					prst = con.prepareStatement(mysql);

					int i = 0;

					for (i = 1; i < record.size(); i++) {

						prst.setString(1, record.get(0));
						prst.setString(2, record.get(1));
						prst.setString(3, input.getUserName());
						prst.setString(4, record.get(2));
						prst.setString(5, record.get(3));

					}
					getProductId.add(Integer.parseInt(record.get(0)));
					getQuantity.add(record.get(2));

					// バッチを通じて一気に登録する
					prst.addBatch();

					if (i % 1000 == 0 || i == record.size()) {

						prst.executeBatch(); // Execute every 1000 items.

					}
				}
				System.out.println(prst.toString());

			} catch (SQLException e) {
				e.printStackTrace();
			}
					
			// プロダクトをセレクト文で取得
			for (int i = 0; i < getProductId.size(); i++) {

				try {
					
					System.out.println("データベース接続");

					Statement statement = con.createStatement();
					rs = null;

					String sql = "SELECT * FROM product12 WHERE Id = '" + getProductId.get(i) + "'";

					rs = statement.executeQuery(sql);

					while (rs.next()) {
						ArrayList<String> record = new ArrayList<String>();

						record.add(rs.getString("Id"));
						record.add(rs.getString("Product_Name"));
						record.add(rs.getString("Quantity"));

						getProductArray.addAll(record);

					}

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

			// CHECK
			for (int i = 0; i < getQuantity.size(); i++) {

				System.out.println(getQuantity.get(i) + "quantity");

			}

			// プロダクトテーブルの数量をゲット
			for (int i = 2; i < getProductArray.size(); i += 3) {
				newQuantityArray.add(Integer.parseInt(getProductArray.get(i)));

			}

			// プロダクトテーブルのIDをゲット
			for (int i = 0; i < getProductArray.size(); i += 3) {
				newIdArray.add(Integer.parseInt(getProductArray.get(i)));

			}

			// CHECK
			for (int i = 0; i < newIdArray.size(); i++) {

				System.out.println(newIdArray.get(i) + "get idz");
			}

			// CHECK
			for (int i = 0; i < newQuantityArray.size(); i++) {

				System.out.println(newQuantityArray.get(i) + "get quantityz");
			}

			// プロダクトテーブルの数量を減点し更新
			for (int i = 0; i < newQuantityArray.size(); i++) {

				int newQuantityProduct = newQuantityArray.get(i) - Integer.parseInt(getQuantity.get(i));

				try {

					Class.forName("com.mysql.cj.jdbc.Driver");



					String sql = "Update product12 set Quantity=? where Id = " + newIdArray.get(i);
					prst = con.prepareStatement(sql);

					prst.setInt(1, newQuantityProduct);

					int update = prst.executeUpdate();

				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}

			}

			// 仮カートテーブルを削除する

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				String sql = "Delete FROM cart12 where buyer='" + input.getUserName() + "'";

				PreparedStatement ps = con.prepareStatement(sql);

				int i = ps.executeUpdate();

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			return 0;
		}
		
	
		
}
