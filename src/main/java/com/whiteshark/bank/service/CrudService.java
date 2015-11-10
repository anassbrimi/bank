package com.whiteshark.bank.service;

import java.util.List;

public interface CrudService<E> {

	public E readById(Long id);

	public List<E> readAll();

	public void create(E entity);

	public void update(E entity);

	public void delete(Long id);

}
