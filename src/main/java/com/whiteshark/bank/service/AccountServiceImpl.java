package com.whiteshark.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whiteshark.bank.dao.AccountDao;
import com.whiteshark.bank.model.Account;
import com.whiteshark.bank.model.User;

@Service("accountService")
public class AccountServiceImpl extends CrudServiceImpl<Account> implements
		AccountService {
	/**
	 * Définition du DAO de traitement des comptes bancaires
	 * 
	 * @param accountDAO
	 *            DAO de traitement des comptes bancaires
	 */
	@Autowired
	public void setAccountDao(AccountDao accountDao) {
		this.dao = accountDao;
	}

	/**
	 * Lecture du compte par défaut (1er compte dans la liste)
	 * 
	 * @return Compte bancaire
	 */
	@Override
	public Account readDefault() {
		return ((AccountDao) dao).findFirst();
	}

	@Override
	public List<User> readByUser(User user) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Lecture des comptes de l'utilisateur {}.", user);

		return ((AccountDao) dao).findByUser(user);
	}
}
