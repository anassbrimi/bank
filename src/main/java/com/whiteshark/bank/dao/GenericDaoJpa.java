package com.whiteshark.bank.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericDaoJpa<E> implements GenericDao<E> {

	public static Logger LOGGER = LoggerFactory.getLogger(GenericDaoJpa.class);

	protected EntityManager entityManager;
	private Class<E> entityBeanType;

	@SuppressWarnings("unchecked")
	public GenericDaoJpa() {
		this.entityBeanType = (Class<E>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public E findById(Long id) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Recherche de l'entité "
					+ entityBeanType.getSimpleName() + " avec l'identifiant "
					+ id);

		return entityManager.find(entityBeanType, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Recherche de toutes les entités "
					+ entityBeanType.getSimpleName());

		final String SQL = "SELECT e FROM " + this.entityBeanType.getName()
				+ " e ORDER by e.id";
		Query query = entityManager.createQuery(SQL);

		return (List<E>) query.getResultList();
	}

	@Override
	public void insert(E entity) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Insertion de l'entité " + entity);

		entityManager.persist(entity);
	}

	@Override
	public void update(E entity) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Mise à jour de l'entité " + entity);

		entityManager.merge(entity);
	}

	@Override
	public void delete(E entity) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Suppression de l'entité " + entity);

		entityManager.remove(entity);
	}

}
