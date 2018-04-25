package cn.edu.hbue.test;

import org.junit.jupiter.api.Test;

import cn.edu.hbue.dao.UserDao;
import cn.edu.hbue.model.User;

class UserDaoTest {

	@Test
	void test() {
		UserDao.insertIntoUser(new User("1", "1", "1", "1", "1"));
	}

}
