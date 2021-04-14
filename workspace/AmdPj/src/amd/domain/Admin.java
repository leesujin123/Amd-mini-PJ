package amd.domain;

public class Admin {
	private static String admin = "admin";

	
	public Admin() {
	}

	public Admin(String admin) {
		this.admin = admin;
	}

	public static String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}
}
