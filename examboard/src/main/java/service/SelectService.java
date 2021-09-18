package service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.BoardDaoImple;
import model.BoardDto;

@Service
public class SelectService {
	@Autowired
	private BoardDaoImple boardDao;
	
	public Map<String, Object> list(int pageNum){
		int totalCount = boardDao.getArticleCount();
		int pageBlock = 5;
		int startNum = (pageNum -1) * pageBlock + 1;
		int endNum = startNum + pageBlock-1;
		int startPaging = 1;
		int endPaging = (totalCount / 5) + 1;
		
		List<BoardDto> articleList = null;
		if(totalCount > 0) {
			articleList = boardDao.getArticles(startNum, endNum);
		}else {
			articleList = Collections.emptyList();
		}
		
		Map<String, Object> list = new HashMap<String, Object>();
		list.put("articleList", articleList);
		list.put("startNum", startNum);
		list.put("endNum", endNum);
		list.put("startPaging", startPaging);
		list.put("endPaging", endPaging);
		list.put("pageBlock", pageBlock);
		list.put("totalCount", totalCount);

		return list;
	}
	
	public BoardDto read(int num) {
		boardDao.articeCounterPlus(num);
		return boardDao.getArticle(num);
	}
	
	public BoardDto move(int num, String type) {
		BoardDto tmp = null;
		boardDao.articeCounterPlus(num);
		tmp = boardDao.getArticle(num);
		if(type.equals("before")) {
			tmp = boardDao.getAgoArticle(num);
			boardDao.articeCounterPlus(tmp.getNum());
		}else if(type.equals("after")) {
			tmp = boardDao.getNextArticle(num);
			boardDao.articeCounterPlus(tmp.getNum());
		}
		return tmp;
	}
	
}
