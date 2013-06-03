package com.datacart.webservice.resources;

import com.datacart.model.dao.spi.common.UserDao;
import com.datacart.model.objects.common.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitriy Sinenkiy - Team International
 * @since 2.0
 */
@Service
public class UserService {
	@Resource
	private UserDao userDao;

	public List<String> getUserNames() {
		List<User> users = userDao.findAll();
		List<String> result = new ArrayList<String>();
		for (User user : users) {
			result.add(user.getDisplayName());
		}

		return result;
	}
}
