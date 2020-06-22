package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dto.Fran;
import web.dto.Menu;
import web.util.Paging;

public interface MenuService {

	
	/**
	 * 페이징 객체 생성
	 * @param req - 요청 정보 객체
	 * @return Paging - 페이징 계산이 완료된 객체
	 */
	public Paging getPaging(HttpServletRequest req);

	
	/**
	 *  페이징 처리하여 보여질 게시글 목록만 조회
	 * @param paging - 페이징 정보 객체
	 * @return List<Menu> - 게시글 전체 조회 결과 리스트
	 */
	public List<Menu> getList(Paging paging);

	
	

}
