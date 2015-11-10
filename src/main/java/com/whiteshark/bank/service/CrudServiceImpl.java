package com.whiteshark.bank.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.whiteshark.bank.dao.GenericDao;

public class CrudServiceImpl<E> implements CrudService<E> {

	public static final Logger LOGGER = LoggerFactory
			.getLogger(UserServiceImpl.class);
	protected GenericDao<E> dao;

	public E readById(Long id) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Lecture de l'entité avec l'identifiant {}.", id);

		return dao.findById(id);
	}

	/**
	 * Lecture de l'ensemble des entités
	 * 
	 * @return Liste d'entités
	 */
	public List<E> readAll() {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Lecture de l'ensemble des entités.");

		return dao.findAll();
	}

	/**
	 * Creation d'une nouvelle entité
	 * 
	 * @param entity
	 *            Entité à ajouter
	 */
	@Transactional
	@Override
	public void create(E entity) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Création de l'entité {}.", entity);

		dao.insert(entity);
	}

	/**
	 * Mise à jour d'une entité
	 * 
	 * @param entity
	 *            Entité à mettre à jour
	 */
	@Transactional
	@Override
	public void update(E entity) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Mise à jour de l'entité {}.", entity);

		dao.update(entity);
	}

	/**
	 * Suppression d'une entité
	 * 
	 * @param id
	 *            Identifiant de l'entité à supprimer
	 */
	@Transactional
	@Override
	public void delete(Long id) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Suppression de l'utilisateur avec l'identifiant {}.",
					id);

		E entity = dao.findById(id);
		dao.delete(entity);
	}
}
