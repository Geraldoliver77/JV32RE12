package dto;

/*
 * Product DTO クラス
 * */

public class productDTO {

	private String id;
	private String productName;
	private String makerName;
	private String genreName;
	private String price;
	private String quantity;
	private String search ;
	

	
	private String searchPriceLow ;
	private String searchPriceHigh ;
	private String searchName;
	private String searchGenre;
	private String searchMaker;
	
	private String cartId;
	private String userName ;
	private int totalPrice;
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSearchPriceLow() {
		return searchPriceLow;
	}

	public String getSearchPriceHigh() {
		return searchPriceHigh;
	}

	public String getSearchName() {
		return searchName;
	}

	public String getSearchGenre() {
		return searchGenre;
	}

	public String getSearchMaker() {
		return searchMaker;
	}

	public void setSearchPriceLow(String searchPriceLow) {
		this.searchPriceLow = searchPriceLow;
	}

	public void setSearchPriceHigh(String searchPriceHigh) {
		this.searchPriceHigh = searchPriceHigh;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public void setSearchGenre(String searchGenre) {
		this.searchGenre = searchGenre;
	}

	public void setSearchMaker(String searchMaker) {
		this.searchMaker = searchMaker;
	}



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

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

}
