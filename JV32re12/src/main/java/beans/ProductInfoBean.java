package beans;

/*
 * Products Beans クラス
 * */

import java.io.Serializable;

public class ProductInfoBean implements Serializable {

	
	private String id;
	private String productName;
	private String makerName;
	private String genreName;
	private String price;
	private String quantity;
	

	public String getId() {
		return id;
	}

	public String getProductName() {
		return productName;
	}

	public String getMakerName() {
		return makerName;
	}

	public String getGenreName() {
		return genreName;
	}

	public String getPrice() {
		return price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setMakerName(String makerName) {
		this.makerName = makerName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	
}