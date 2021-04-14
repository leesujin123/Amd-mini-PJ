package amd.domain;

import java.sql.Date;

public class BoardQ {
	private int bq_seq;
	private String m_name;
	private String m_email;	//ÂüÁ¶Å°
	private String bq_subject;
	private String bq_content;
	private Date bq_rdate;
	private int bq_count;
	private int bq_refer;
	private int bq_lev;
	private int bq_place;
	
	public BoardQ() {}
	public BoardQ(int bq_seq, String m_email, String bq_subject, String bq_content, Date bq_rdate, int bq_count,
			int bq_refer, int bq_lev, int bq_place) {
		this.bq_seq = bq_seq;
		this.m_email = m_email;
		this.bq_subject = bq_subject;
		this.bq_content = bq_content;
		this.bq_rdate = bq_rdate;
		this.bq_count = bq_count;
		this.bq_refer = bq_refer;
		this.bq_lev = bq_lev;
		this.bq_place = bq_place;
	}
	
	public int getBq_seq() {
		return bq_seq;
	}
	public void setBq_seq(int bq_seq) {
		this.bq_seq = bq_seq;
	}
	
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_email() {
		return m_email;
	}
	public void setM_email(String m_email) {
		this.m_email = m_email;
	}
	public String getBq_subject() {
		return bq_subject;
	}
	public void setBq_subject(String bq_subject) {
		this.bq_subject = bq_subject;
	}
	public String getBq_content() {
		return bq_content;
	}
	public void setBq_content(String bq_content) {
		this.bq_content = bq_content;
	}
	public Date getBq_rdate() {
		return bq_rdate;
	}
	public void setBq_rdate(Date bq_rdate) {
		this.bq_rdate = bq_rdate;
	}
	public int getBq_count() {
		return bq_count;
	}
	public void setBq_count(int bq_count) {
		this.bq_count = bq_count;
	}
	public int getBq_refer() {
		return bq_refer;
	}
	public void setBq_refer(int bq_refer) {
		this.bq_refer = bq_refer;
	}
	public int getBq_lev() {
		return bq_lev;
	}
	public void setBq_lev(int bq_lev) {
		this.bq_lev = bq_lev;
	}
	public int getBq_place() {
		return bq_place;
	}
	public void setBq_place(int bq_place) {
		this.bq_place = bq_place;
	}
	
}
