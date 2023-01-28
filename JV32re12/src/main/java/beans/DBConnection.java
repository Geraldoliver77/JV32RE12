package beans;
/**
 * データベースの接続の設定はこちらになります
 * @author Gerald Oliver
 *
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
	private static final String DATABASE_NAME = "jv32";
	private static final String PROPERTYS = "?charcterEncoding=utf-8";
	private static final String URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME + PROPERTYS;
	private static final String USER = "root";
	private static final String PASS = "";
	
	private Connection con = null;
	
	/** コンストラクタ */
	public DBConnection() {
	}
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASS);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public void close() {
		
	}
}
