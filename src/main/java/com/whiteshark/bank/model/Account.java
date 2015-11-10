package com.whiteshark.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Account {
	@Id
	@GeneratedValue
	private Long id;
	@Column(length = 100, nullable = false)
	private String reference;
	@Column(nullable = false)
	private Double balance;
	@ManyToOne
	@JoinColumn(name = "usr_id", nullable = false)
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String toString() {
		return "Account [id=" + id + ", reference=" + reference + ", balance="
				+ balance + "]";
	}

}
