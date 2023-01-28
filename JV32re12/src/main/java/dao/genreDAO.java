package dao;

/*
 * Genre DAO クラス
 * */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.DBConnection;
import dto.genreDTO;

public class genreDAO {
	private Connection con = null;

	ResultSet rs = null;
	PreparedStatement prst = null;
	
		public genreDAO() {
			DBConnection con = new DBConnection();
			this.con = con.getConnection();
		}
		
		// DAOの値を全部返す
		public ArrayList<genreDTO> findAllGenre() {
			ArrayList<genreDTO> list = new ArrayList<genreDTO>();

			String sql = "SELECT * FROM genre12;";
			

			try {
				prst = con.prepareStatement(sql);
				rs = prst.executeQuery();

				while (rs.next()) {		
					genreDTO genre = new genreDTO();
					genre.setGenre(rs.getString("genre"));
					

					list.add(genre);
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
			System.out.println("findAll(genre) OK");
			return list;
		}
		
		public int register(genreDTO input) {
			int num = 0;
			String sql = "INSERT INTO genre12(genre) VALUE(?)";

			try {
				prst = con.prepareStatement(sql);

				// インサート文の文字を登録する
				prst.setString(1, input.getGenre());
			

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

}
