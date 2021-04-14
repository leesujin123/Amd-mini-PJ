package product.mvc.model;

import java.util.ArrayList;
import java.util.List;

import product.mvc.vo.ListResult;
import amd.domain.Product;

public class ProductService {
	private ProductDAO dao;
	
	private static final ProductService instance = new ProductService();
	private ProductService(){
		dao = new ProductDAO();
	} 
	public static ProductService getInstance() {
		return instance;
	}
	
	public ListResult getListResult(int cp, int ps) {
		List<Product> list = dao.list(cp, ps); //use1
		long totalCount = dao.getTotalCount(); //use2
		ListResult r = new ListResult(cp, totalCount, ps, list);
		
		return r;
		
	}
	
	public ListResult getListResult(int cp, int ps,String p_type) {
		List<Product> list = dao.list(cp, ps, p_type); //use1
		long totalCount = dao.getTotalCount(p_type); //use2
		ListResult r = new ListResult(cp, totalCount, ps, list);
		
		return r;
		
	}
	
	public Product getProductS(int p_code) {
		return dao.getProduct(p_code);
	}
}
