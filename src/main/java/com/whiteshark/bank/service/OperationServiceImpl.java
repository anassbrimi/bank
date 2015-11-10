package com.whiteshark.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whiteshark.bank.dao.OperationDao;
import com.whiteshark.bank.model.Account;
import com.whiteshark.bank.model.Operation;

@Service("operationService")
public class OperationServiceImpl extends CrudServiceImpl<Operation> implements
		OperationService {
	/**
	 * Définition du DAO de traitement des opérations bancaires
	 * 
	 * @param operationDao
	 *            DAO de traitement des opérations bancaires
	 */
	@Autowired
	public void setOperationDao(OperationDao operationDao) {
		this.dao = operationDao;
	}

	/**
	 * Lecture des opérations liés à un compte
	 * 
	 * @param account
	 *            Compte associé aux opérations
	 * @return Liste d'opérations
	 */
	@Override
	public List<Operation> readByAccount(Account account) {
		return ((OperationDao) dao).findByAccount(account);
	}
}
