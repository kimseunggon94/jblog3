package kr.co.itcen.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.BlogDao;
import kr.co.itcen.jblog.repository.CategoryDao;
import kr.co.itcen.jblog.repository.UserDao;
import kr.co.itcen.jblog.vo.UserVo;
@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	
	
	public void join(UserVo vo) {
		userDao.insert(vo);
		blogDao.insert(vo.getId());
		categoryDao.default_insert(vo.getId());
	}
	public Boolean existUser(String id) {
		return userDao.get(id) !=null;
	}
	public UserVo getUser(UserVo vo) {
		return userDao.get(vo);
	}
	
	
}	
