package com.whiteshark.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whiteshark.bank.model.User;
import com.whiteshark.bank.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController extends CrudController<User> {
	/**
	 * Définition du service de gestion des utilisateurs
	 * 
	 * @param userService
	 *            Service de gestion des utilisateurs
	 */
	@Autowired
	public void setUserService(UserService userService) {
		crudService = userService;
	}
}