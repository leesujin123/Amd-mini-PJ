package order.mvc.vo;

import java.util.List;

import amd.domain.Cart;
import amd.domain.Member;
import amd.domain.Product;

public class OrderVO {
	
	private List<Cart> listC;
	private Member member;
	
	public OrderVO(List<Cart> listC, Member member) {
		
		this.listC = listC;
		this.member = member;
	}

	public List<Cart> getListC() {
		return listC;
	}

	public void setListC(List<Cart> listC) {
		this.listC = listC;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	
}
