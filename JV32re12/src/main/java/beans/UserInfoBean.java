package beans;

/*
 * User Beans クラス
 * */

import java.io.Serializable;

/**
 * ユーザの情報をセットゲットする
 * 
 * @author Gerald Oliver
 */

public class UserInfoBean implements Serializable {

	/** 名前ロマジ */
	private String id;
	/** 名前カナ */
	private String username;
	/** 年齢 **/
	private String password;
	/** 連絡先 **/
	private String gender;
	/** メール **/
	private String birth;



	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getGender() {
		return gender;
	}

	public String getBirth() {
		return birth;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * コンストラクタ
	 */
	public UserInfoBean() {

	}



}
