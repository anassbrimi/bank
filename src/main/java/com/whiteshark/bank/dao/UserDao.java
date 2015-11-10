package com.whiteshark.bank.dao;

import com.whiteshark.bank.model.User;

public interface UserDao extends GenericDao<User> {
	public User findByUsername(String username);
}
