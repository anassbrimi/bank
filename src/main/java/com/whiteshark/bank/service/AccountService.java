package com.whiteshark.bank.service;

import java.util.List;

import com.whiteshark.bank.model.Account;
import com.whiteshark.bank.model.User;

public interface AccountService extends CrudService<Account> {
	public Account readDefault();

	public List<User> readByUser(User user);

}
