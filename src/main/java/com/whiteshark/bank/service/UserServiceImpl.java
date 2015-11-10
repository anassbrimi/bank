package com.whiteshark.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whiteshark.bank.dao.UserDao;
import com.whiteshark.bank.model.User;

@Service("userService")
public class UserServiceImpl extends CrudServiceImpl<User> implements
		UserService {
	/**
	 * Définition du DAO de traitement des utilisateurs
	 * 
	 * @param userDAO
	 *            DAO de traitement des utilisateurs
	 */
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.dao = userDao;
	}

	@Transactional
	@Override
	public void create(User user) {
		StandardPasswordEncoder encoder = new StandardPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));

		super.create(user);
	}

	/**
	 * Mise à jour d'un utilisateur
	 * 
	 * @param user
	 *            Utilisateur à mettre à jour
	 */
	@Transactional
	@Override
	public void update(User user) {
		StandardPasswordEncoder encoder = new StandardPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));

		super.update(user);
	}

	/**
	 * Chargement de l'utilisateur par son identifiant (Utilisé par spring
	 * framework pour l'identification)
	 * 
	 * @return Utilisateur correspondant à l'identifiant
	 */
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Lecture de l'utilisateur avec l'identifiant {}.",
					username);

		return (UserDetails) ((UserDao) dao).findByUsername(username);
	}
}
