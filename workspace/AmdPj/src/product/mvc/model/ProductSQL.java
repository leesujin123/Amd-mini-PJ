package product.mvc.model;

class ProductSQL {
	final static String LIST_PAGE="select * from (select ROWNUM rnum, aa.* from (select * from PRODUCT order by P_CODE asc) aa)"
			+ "	where rnum>? and rnum<=?";
	final static String COUNT ="select max(ROWNUM) from PRODUCT";
	final static String COUNTC ="select max(ROWNUM) from PRODUCT where P_TYPE=?";
	final static String CONTENT="select * from PRODUCT where P_CODE=?";
	final static String LISTC_PAGE="select * from (select ROWNUM rnum, aa.* from (select * from PRODUCT where P_TYPE=? order by P_CODE asc) aa) where rnum>? and rnum<=?";
	

}
