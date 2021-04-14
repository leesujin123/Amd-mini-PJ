package amd.domain;

public class Product {

	private int p_code;
	private String p_type;
	private String p_name;
	private int p_price;
	private String p_img;
	private String p_info;
	
	public Product() {}

	public Product(int p_code, String p_type, String p_name, int p_price, String p_img, String p_info) {
		this.p_code = p_code;
		this.p_type = p_type;
		this.p_name = p_name;
		this.p_price = p_price;
		this.p_img = p_img;
		this.p_info = p_info;
	}

	public int getP_code() {
		return p_code;
	}

	public void setP_code(int p_code) {
		this.p_code = p_code;
	}

	public String getP_type() {
		return p_type;
	}

	public void setP_type(String p_type) {
		this.p_type = p_type;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public int getP_price() {
		return p_price;
	}

	public void setP_price(int p_price) {
		this.p_price = p_price;
	}

	public String getP_img() {
		return p_img;
	}

	public void setP_img(String p_img) {
		this.p_img = p_img;
	}

	public String getP_info() {
		return p_info;
	}

	public void setP_info(String p_info) {
		this.p_info = p_info;
	}
	

}
