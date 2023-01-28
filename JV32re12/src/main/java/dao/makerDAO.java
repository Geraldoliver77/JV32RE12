package dao;

/*
 * Maker DAO クラス
 * */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.DBConnection;
import dto.makerDTO;

public class makerDAO {
	private Connection con = null;

	ResultSet rs = null;
	PreparedStatement prst = null;
	
		public makerDAO() {
			DBConnection con = new DBConnection();
			this.con = con.getConnection();
		}
		
		// DAOの値を全部返す
		public ArrayList<makerDTO> findAllMaker() {
			ArrayList<makerDTO> list = new ArrayList<makerDTO>();

			String sql = "SELECT * FROM maker12 ;";
			
			try {
				prst = con.prepareStatement(sql);
				rs = prst.executeQuery();

				while (rs.next()) {		
					makerDTO maker = new makerDTO();
					maker.setMaker(rs.getString("maker"));
					

					list.add(maker);
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
			System.out.println("findAll(maker) OK");
			return list;
		}
		
		public int register(makerDTO input) {
			int num = 0;
			String sql = "INSERT INTO maker12(maker) VALUE(?)";

			try {
				prst = con.prepareStatement(sql);

				// インサート文の文字を登録する
				prst.setString(1, input.getMaker());
			

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
