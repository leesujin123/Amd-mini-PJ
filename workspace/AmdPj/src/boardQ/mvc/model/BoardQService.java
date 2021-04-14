package boardQ.mvc.model;

import java.util.List;

import amd.domain.BoardQ;
import boardQ.mvc.vo.BoardQVO;

public class BoardQService {
	private BoardQDAO dao;
	private static final BoardQService instance = new BoardQService();
	private BoardQService(){
		dao = new BoardQDAO();
	}
	public static BoardQService getInstance(){
		return instance; 
	}
	
	public BoardQVO list(int page, int pageSize) {
		long totalCount = dao.countRow();
		List<BoardQ> list = dao.list(page, pageSize);
		return new BoardQVO(page, totalCount, pageSize, list);
	}
	
	public void insertS(BoardQ boardq) {
		int maxRef = dao.getMaxRef();
		boardq.setBq_refer(maxRef+1);
		dao.insert(boardq);
	}

	public BoardQ showContentS(int bq_seq){
		dao.updateCnt(bq_seq);
		return dao.getContent(bq_seq);
	}
	public BoardQ showContentS(int bq_seq, String justFor){
		return dao.getContent(bq_seq);
	}
	
	public void deleteS(int bq_seq){
		dao.delete(bq_seq);
	}

	public void updateS(BoardQ boardQ){
		dao.update(boardQ);
	}
	public void rewriteS(String refStr, String levStr, String placeStr, String email, String subject, String content) {
		//부모글(족,레벨,순번)
		int refer = Integer.parseInt(refStr);
		int lev = Integer.parseInt(levStr);
		int place = Integer.parseInt(placeStr);
		//자식글(족,레벨,순번)
		int bq_refer = refer;
		int bq_lev = lev + 1;
		int bq_place = dao.getNewPLACE(bq_lev, place);
		dao.updatePlace(refer, place);
		
		BoardQ boardQ = new BoardQ(-1, email, subject, content, null, 0, bq_refer, bq_lev, bq_place);
		dao.insert(boardQ);
	}
}
