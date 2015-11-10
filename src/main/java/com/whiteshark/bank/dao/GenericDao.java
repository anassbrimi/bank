package com.whiteshark.bank.dao;

import java.util.List;

public interface GenericDao<E> {

	public E findById(Long id);

	public List<E> findAll();

	public void insert(E entity);

	public void update(E entity);

	public void delete(E entity);

}
