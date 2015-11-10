package com.whiteshark.bank.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.whiteshark.bank.model.Account;
import com.whiteshark.bank.model.Operation;

@Repository("operationDao")
public class OperationDaoJpa extends GenericDaoJpa<Operation> implements
		OperationDao {
	@Override
	@SuppressWarnings("unchecked")
	public List<Operation> findByAccount(Account account) {
		final String SQL = "SELECT e FROM  com.whiteshark.bank.model.Operation e WHERE e.account = :account ORDER BY e.date";

		Query query = entityManager.createQuery(SQL);
		query.setParameter("account", account);

		return (List<Operation>) query.getResultList();
	}
}
