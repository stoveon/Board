package service;

import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import model.BoardDao;
import model.BoardDto;

@Service
public class WriteService {
	@Autowired
	private BoardDao boardDao;
	
	public void write(BoardDto boardDto) {
		boardDto.setRef(0);
		boardDto.setStep(0);
		boardDto.setReadcount(0);
		boardDto.setDepth(0);
		boardDao.articleInsert(boardDto);
	}
	
	public void reple(int num) {
		BoardDto tmp = boardDao.getArticle(num);
		boardDao.artticleInsertRef(tmp);
	}
	
}
