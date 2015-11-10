package com.whiteshark.bank.dao;

import java.util.List;

import com.whiteshark.bank.model.Account;
import com.whiteshark.bank.model.User;

public interface AccountDao extends GenericDao<Account> {

	public Account findFirst();

	public List<User> findByUser(User user);

	/**
	 * Recherche du premier compte bancaire dans la liste d'un utilisateur
	 * 
	 * @param user
	 *            Utilisateur associé au compte
	 * @return Compte bancaire
	 */
	public Account findFirstByUser(User user);
}
