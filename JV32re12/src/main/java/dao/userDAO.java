package dao;
/*
 * User DAO クラス
 * */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.DBConnection;
import dto.userDTO;
import form.userForm;

public class userDAO {
	private Connection con = null;

	ResultSet rs = null;
	PreparedStatement prst = null;

	public userDAO() {
		DBConnection con = new DBConnection();
		this.con = con.getConnection();
	}

	// DAOの値を全部返す
	public ArrayList<userDTO> findAllUser() {
		ArrayList<userDTO> list = new ArrayList<userDTO>();

		String sql = "SELECT * FROM user12;";

		try {
			prst = con.prepareStatement(sql);
			rs = prst.executeQuery();

			while (rs.next()) {
				userDTO user = new userDTO();
				user.setId(rs.getString("User_Id"));
				user.setPassword(rs.getString("Password"));
				user.setUsername(rs.getString("User_Name"));

				list.add(user);
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
		System.out.println("findAll(User) OK");
		return list;
	}


	public int register(userForm input) {
			int num = 0;
			String sql = "INSERT INTO user12(User_Id,User_Name,password,Gender,Birth) VALUE(?,?,?,?,?)";

			try {
				prst = con.prepareStatement(sql);

				// インサート文の文字を登録する
				prst.setString(1, input.getId());
				prst.setString(2, input.getUsername());
				prst.setString(3, input.getPassword());
				prst.setString(4, input.getGender());
				prst.setString(5, input.getBirth());

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
