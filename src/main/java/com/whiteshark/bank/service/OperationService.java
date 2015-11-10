package com.whiteshark.bank.service;

import java.util.List;

import com.whiteshark.bank.model.Account;
import com.whiteshark.bank.model.Operation;

public interface OperationService extends CrudService<Operation> {

	public List<Operation> readByAccount(Account account);

}
