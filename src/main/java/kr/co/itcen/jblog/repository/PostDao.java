package kr.co.itcen.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.PostVo;

@Repository
public class PostDao {
	@Autowired
	private SqlSession sqlSession;
	
	public boolean default_insert(String blog_id) {
		int count = sqlSession.insert("category.default_insert", blog_id);
		
		return count==1;
		
	}

	public List<PostVo> getList(Long categoryno) {
		// TODO Auto-generated method stub
		return null;
	}

	public PostVo get(Long postno, Long categoryno) {
		return null;
	}

	
}
