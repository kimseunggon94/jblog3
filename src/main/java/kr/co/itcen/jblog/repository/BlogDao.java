package kr.co.itcen.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.BlogVo;

@Repository
public class BlogDao {
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(String id) {
		Map<String, String > map = new HashMap<String, String>();
		map.put("id", id);
		map.put("title", id+"의 블로그입니다.");
		map.put("logo", "/assets/images/spring-logo.jpg");
		int count = sqlSession.insert("blog.insert", map);
		return count==1;
	}

}
