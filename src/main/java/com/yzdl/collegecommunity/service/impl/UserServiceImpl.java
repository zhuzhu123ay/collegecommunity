package com.yzdl.collegecommunity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzdl.collegecommunity.bean.User;
import com.yzdl.collegecommunity.dao.BaseDao;
import com.yzdl.collegecommunity.dao.UserDao;
import com.yzdl.collegecommunity.service.IUserService;

/**
 * 用户 相关业务逻辑实现类
 * */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {
	@Autowired
	private UserDao userDao;
	
	@Override
	public BaseDao<User> getDao() {
		return userDao;
	}

	@Override
	public void register(User user) throws Exception {
		User db_user=userDao.findByUserName(user.getUsername());
		if(db_user!=null){
			throw new Exception("该用户已存在");
		}else{
			userDao.save(user);
		}
	}

	@Override
	public User login(String username, String password) throws Exception {
		User user =userDao.findByUserName(username);
		if(user!=null){
			if(user.getPassword().equals(password)){
				return user;
			}else{
				throw new Exception("密码错误！");
			}
		}else{
			throw new Exception("用户不存在！");
		}
	}

	@Override
	public List<User> findAllByOrder() throws Exception {
		List<User> rankList=userDao.findAllByOrder();
		return rankList;
	}


}
