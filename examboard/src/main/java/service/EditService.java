package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.BoardDao;
import model.BoardDto;

@Service
public class EditService {
	@Autowired
	private BoardDao boardDao;
	
	public boolean edit(BoardDto boardDto) {
		BoardDto tmp = boardDao.getArticle(boardDto.getNum());
		if(tmp.getPass().equals(boardDto.getCheckPass())) {
			boardDao.articleUpdate(boardDto);
			return true;
		}else {
			return false;
		}
	}
}
