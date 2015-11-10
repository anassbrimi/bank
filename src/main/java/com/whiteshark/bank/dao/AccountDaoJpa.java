package com.whiteshark.bank.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.whiteshark.bank.model.Account;
import com.whiteshark.bank.model.User;

@Repository("accountDao")
public class AccountDaoJpa extends GenericDaoJpa<Account> implements AccountDao {

	public Account findFirst() {
		final String SQL = "SELECT e from com.whiteshark.bank.model.Account e ORDER BY e.id";
		Query query = entityManager.createQuery(SQL).setMaxResults(1);

		return (Account) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByUser(User user) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug(
					"Recherche des comptes bancaire appartenant à l'utilisateur {}.",
					user);

		final String SQL = "SELECT e from com.whiteshark.bank.model.Account e WHERE e.user = :user ORDER BY id";
		Query query = entityManager.createQuery(SQL);
		query.setParameter("user", user);

		return (List<User>) query.getResultList();
	}

	/**
	 * Recherche du premier compte bancaire dans la liste d'un utilisateur
	 * 
	 * @param user
	 *            Utilisateur associé au compte
	 * @return Compte bancaire
	 */
	@Override
	public Account findFirstByUser(User user) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug(
					"Recherche du premier compte bancaire dans la liste appartenant à l'utilisateur {}.",
					user);

		final String SQL = "SELECT e from com.whiteshark.bank.model.Account e WHERE e.user = :user ORDER BY id";
		Query query = entityManager.createQuery(SQL);
		query.setParameter("user", user);
		query.setMaxResults(1);

		return (Account) query.getSingleResult();
	}
}
