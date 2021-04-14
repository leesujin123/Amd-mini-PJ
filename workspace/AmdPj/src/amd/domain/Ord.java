package amd.domain;

import java.sql.Date;

public class Ord {
	private long o_seq;
	private long c_seq; //ÂüÁ¶Å°
	private Date o_oDate;
	private String o_oName;
	private String o_oPhone;
	private String o_oAddr;
	private String o_msg;
	private String o_oValid;
	private int o_total;
	
	public Ord(long o_seq, long c_seq, Date o_oDate, String o_oName, String o_oAddr, String o_msg, String o_oValid, int o_total) {
		super();
		this.o_seq = o_seq;
		this.c_seq = c_seq;
		this.o_oDate = o_oDate;
		this.o_oName = o_oName;
		this.o_oAddr = o_oAddr;
		this.o_msg = o_msg;
		this.o_oValid = o_oValid;
		this.o_total = o_total;
	}
	
	public int getO_total() {
		return o_total;
	}

	public void setO_total(int o_total) {
		this.o_total = o_total;
	}

	public long getO_seq() {
		return o_seq;
	}
	public void setO_seq(long o_seq) {
		this.o_seq = o_seq;
	}
	public long getC_seq() {
		return c_seq;
	}
	public void setC_seq(long c_seq) {
		this.c_seq = c_seq;
	}
	public Date getO_oDate() {
		return o_oDate;
	}
	public void setO_oDate(Date o_oDate) {
		this.o_oDate = o_oDate;
	}
	public String getO_oName() {
		return o_oName;
	}
	public void setO_oName(String o_oName) {
		this.o_oName = o_oName;
	}
	public String getO_oAddr() {
		return o_oAddr;
	}
	public void setO_oAddr(String o_oAddr) {
		this.o_oAddr = o_oAddr;
	}
	public String getO_msg() {
		return o_msg;
	}
	public void setO_msg(String o_msg) {
		this.o_msg = o_msg;
	}
	public String getO_oValid() {
		return o_oValid;
	}
	public void setO_oValid(String o_oValid) {
		this.o_oValid = o_oValid;
	}
	
	
}
