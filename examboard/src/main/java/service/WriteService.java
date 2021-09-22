package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.BoardDao;
import model.BoardDto;

@Service
public class WriteService {
	@Autowired
	private BoardDao boardDao;
	
	public void write(BoardDto boardDto) {
		//새글 작성시 글번호 값으로 ref 설정 step은 무조건 1
		//댓글 작성시 부모의 ref를 자신의 ref로 설정, step은 부모+1
		//새글 작성될 때 depth=0, 댓글은 부모 depth+1
		//댓글 작성시 같은 그룹 내에서 부모의 step보다 큰 게시글의 step+1 update하고 그 자리로 들어감
		if(boardDto.getNum() != 0) {
			BoardDto tmp = boardDao.getArticle(boardDto.getNum());
			boardDto.setRef(tmp.getRef());
			boardDto.setStep(tmp.getStep()+1);
			boardDto.setDepth(tmp.getDepth()+1);
			System.out.println("write step: " + tmp.getStep());
			System.out.println("write depth: " + tmp.getDepth());
		}else {
			boardDto.setRef(boardDao.maxRef() + 1);
			boardDto.setStep(1);
			boardDto.setReadcount(0);
			boardDto.setDepth(0);			
		}
		boardDao.articleInsert(boardDto);
	}
	
	public void reply(BoardDto boardDto) {
		BoardDto tmp = boardDao.getArticle(boardDto.getNum());
		int ref = tmp.getRef();
		int step = tmp.getStep();
		System.out.println("reply ref: " + ref);
		System.out.println("reply step: " + step);
		boardDao.articleInsertRef(ref, step);
	}

}
