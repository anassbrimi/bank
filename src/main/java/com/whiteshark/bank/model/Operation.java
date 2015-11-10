package com.whiteshark.bank.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Operation {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false, length = 100)
	private String description;
	private Double amount;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date date;
	@ManyToOne
	@JoinColumn(name = "account_id", nullable = false)
	private Account account;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String toString() {
		return "Operation [id=" + id + ", description=" + description
				+ ", amount=" + amount + ", date=" + date + "]";
	}

}
