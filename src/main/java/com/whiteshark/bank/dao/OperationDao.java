package com.whiteshark.bank.dao;

import java.util.List;

import com.whiteshark.bank.model.Account;
import com.whiteshark.bank.model.Operation;

public interface OperationDao extends GenericDao<Operation> {

	public List<Operation> findByAccount(Account account);
}
