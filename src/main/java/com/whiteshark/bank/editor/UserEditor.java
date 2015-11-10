package com.whiteshark.bank.editor;

import java.beans.PropertyEditorSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whiteshark.bank.service.UserService;

/**
 * Gestionnaire de conversion d'utilsateurs pour l'édition des formulaires
 */
public class UserEditor extends PropertyEditorSupport {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserEditor.class);
	private final UserService userService;

	/**
	 * Constructeur
	 * 
	 * @param userService
	 *            Gestionnaire des services métiers pour l'entité utilisateur
	 */
	public UserEditor(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Conversion d'un identifiant fournit par un formulaire en objet
	 * Utilisateur
	 * 
	 * @param id
	 *            Identifiant fournit par le formulaire
	 * @return Utilisateur
	 */
	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Conversion de l'identifiant {} en utilisateur", id);

		if (id != null && !id.equals(""))
			setValue(userService.readById(Long.parseLong(id)));
	}
}