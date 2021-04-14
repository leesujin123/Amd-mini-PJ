package amd.domain;

public class Cart {

	private int c_seq;
	private String m_email;	//참조키
	private int p_code;	//참조키
	private String p_name;
	private int p_price;
	private String p_img;
	private int c_amount;
	private String c_valid;
	
	public Cart() {}

	public Cart(int c_seq, String m_email, int p_code, String p_name, int p_price, String p_img, int c_amount,
			String c_valid) {
		super();
		this.c_seq = c_seq;
		this.m_email = m_email;
		this.p_code = p_code;
		this.p_name = p_name;
		this.p_price = p_price;
		this.p_img = p_img;
		this.c_amount = c_amount;
		this.c_valid = c_valid;
	}

	public int getC_seq() {
		return c_seq;
	}

	public void setC_seq(int c_seq) {
		this.c_seq = c_seq;
	}

	public String getM_email() {
		return m_email;
	}

	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

	public int getP_code() {
		return p_code;
	}

	public void setP_code(int p_code) {
		this.p_code = p_code;
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

	public int getC_amount() {
		return c_amount;
	}

	public void setC_amount(int c_amount) {
		this.c_amount = c_amount;
	}

	public String getC_valid() {
		return c_valid;
	}

	public void setC_valid(String c_valid) {
		this.c_valid = c_valid;
	}

	

}
