package amd.domain;

import java.sql.Date;

public class BoardN {

		private long bn_seq;
		private String m_email; //ÂüÁ¶Å°
		private String bn_subject;
		private String bn_content;
		private Date bn_rdate;
		private int bn_count;
				
		public BoardN() {}

		public BoardN(long bn_seq, String m_email, String bn_subject, String bn_content, Date bn_rdate, int bn_count) {
			this.bn_seq = bn_seq;
			this.m_email = m_email;
			this.bn_subject = bn_subject;
			this.bn_content = bn_content;
			this.bn_rdate = bn_rdate;
			this.bn_count = bn_count;
		}

		public long getBn_seq() {
			return bn_seq;
		}

		public void setBn_seq(long bn_seq) {
			this.bn_seq = bn_seq;
		}

		public String getM_email() {
			return m_email;
		}

		public void setM_email(String m_email) {
			this.m_email = m_email;
		}

		public String getBn_subject() {
			return bn_subject;
		}

		public void setBn_subject(String bn_subject) {
			this.bn_subject = bn_subject;
		}

		public String getBn_content() {
			return bn_content;
		}

		public void setBn_content(String bn_content) {
			this.bn_content = bn_content;
		}

		public Date getBn_rdate() {
			return bn_rdate;
		}

		public void setBn_rdate(Date bn_rdate) {
			this.bn_rdate = bn_rdate;
		}

		public int getBn_count() {
			return bn_count;
		}

		public void setBn_count(int bn_count) {
			this.bn_count = bn_count;
		}

		
}