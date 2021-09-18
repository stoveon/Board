package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDaoImple implements BoardDao{
	private static SqlSessionTemplate sqlSessionTemplate;
	
	private BoardDaoImple(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
		System.out.println("BoardDao(SqlSessionTemplate)");
	}

	@Override
	public int getArticleCount() {
		return sqlSessionTemplate.selectOne("count");
	}

	@Override
	public List<BoardDto> getArticles(int startNum, int endNum) {
		Map<String, Integer> tmp = new HashMap<String, Integer>();
		tmp.put("startNum", new Integer(startNum));
		tmp.put("endNum", new Integer(endNum));
		return sqlSessionTemplate.selectList("articles", tmp);
	}

	@Override
	public BoardDto getArticle(int num) {
		return sqlSessionTemplate.selectOne("detail", num);
	}

	@Override
	public BoardDto getNextArticle(int num) {
		int nextNum = sqlSessionTemplate.selectOne("nextNum", num);
//		System.out.println("nextNum"+nextNum);
		num = nextNum;
		return sqlSessionTemplate.selectOne("detail", num);
	}

	@Override
	public BoardDto getAgoArticle(int num) {
		int agoNum = (Integer)sqlSessionTemplate.selectOne("agoNum", num);
//		System.out.println("agoNum"+agoNum);
		num = agoNum;
		return sqlSessionTemplate.selectOne("detail", num);
	}

	@Override
	public void articleInsert(BoardDto article) {
		sqlSessionTemplate.insert("insert", article);
	}

	@Override
	public void artticleInsertRef(BoardDto article) {
		Map<String, Integer> tmp = new HashMap<String, Integer>();
		tmp.put("ref", article.getRef());
		tmp.put("step", article.getStep());
		sqlSessionTemplate.update("reply", tmp);
	}
	
	@Override
	public void articleUpdate(BoardDto article) {
		sqlSessionTemplate.update("update", article);
	}
	
	@Override
	public void articleDelete(int num) {
		sqlSessionTemplate.delete("delete", num);
	}

	@Override
	public void articeCounterPlus(int num) {
		sqlSessionTemplate.update("counterPlus", num);
	}
}