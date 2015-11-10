package com.whiteshark.bank.dao;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.whiteshark.bank.model.User;

@Repository("userDao")
public class UserDaoJpa extends GenericDaoJpa<User> implements UserDao {
	@Override
	public User findByUsername(String username) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Recherche de l'utilisateur avec l'identifiant {}.",
					username);

		final String SQL = "SELECT e FROM com.whiteshark.bank.model.User e WHERE username = :username";

		Query query = entityManager.createQuery(SQL);
		query.setParameter("username", username);

		return (User) query.getSingleResult();
	}

}
