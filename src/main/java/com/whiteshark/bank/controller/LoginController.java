package com.whiteshark.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Contrôleur chargé de la gestion de l'identification au système
 */
@Controller
public class LoginController {
	/**
	 * Page d'accès à l'identification
	 * 
	 * @return Nom de la vue à afficher
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	/**
	 * Page d'erreur d'identification
	 * 
	 * @param redirectAttributes
	 *            Conteneur d'attributs utilisé après redirection (Message
	 *            Flash)
	 * @return Vue à afficher
	 */
	@RequestMapping(value = "/login_error", method = RequestMethod.GET)
	public String error(final RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message",
				"Votre identifiant ou mot de passe est incorrect.");
		return "redirect:login.html";
	}

	/**
	 * Page de confirmation de déconnexion
	 * 
	 * @param redirectAttributes
	 *            Conteneur d'attributs utilisé après redirection (Message
	 *            Flash)
	 * @return Vue à afficher
	 */
	@RequestMapping(value = "/logout_confirm", method = RequestMethod.GET)
	public String logout(final RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message",
				"Vous être à présent déconnecté.");
		return "redirect:login.html";
	}
}