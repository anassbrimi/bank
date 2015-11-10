package com.whiteshark.bank.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity(name = "usr")
public abstract class User implements UserDetails {
	@Id
	@GeneratedValue
	private Long id;
	@Column(length = 100, nullable = false)
	private String firstName;
	@Column(length = 100, nullable = false)
	private String lastName;
	@Column(length = 100, nullable = false)
	private String userName;
	@Column(length = 100, nullable = false)
	private String password;

	private boolean administrator;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdministrator() {
		return administrator;
	}

	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String firstName, String lastName, String userName,
			String password, boolean administrator) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.administrator = administrator;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstName + ", lastname="
				+ lastName + ", username=" + userName + ", administrator="
				+ administrator + "]";
	}

	@Override
	@Transient
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection authorities = new ArrayList();

		if (isAdministrator())
			authorities.add(new SimpleGrantedAuthority("ADMINISTRATOR"));

		return authorities;
	}

	/**
	 * Vérification que le compte n'est pas expiré
	 * 
	 * @return Vrai
	 */
	@Override
	@Transient
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * Vérification que le compte n'est pas bloqué
	 * 
	 * @return Vrai
	 */
	@Override
	@Transient
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * Vérification que les identifiants n'ont pas expiré
	 * 
	 * @return Vrai
	 */
	@Override
	@Transient
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * Vérification que le compte est activé
	 * 
	 * @return Vrai
	 */
	@Override
	@Transient
	public boolean isEnabled() {
		return true;
	}

}
