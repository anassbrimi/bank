package com.whiteshark.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.whiteshark.bank.model.Account;
import com.whiteshark.bank.model.User;
import com.whiteshark.bank.service.AccountService;

@Controller
@RequestMapping(value = "/account")
public class AccountController extends CrudController<Account> {
	/**
	 * Définition du service de gestion des comptes bancaires
	 * 
	 * @param accountService
	 *            Service de gestion des comptes bancaires
	 */
	@Autowired
	public void setAccountService(AccountService accountService) {
		crudService = accountService;
	}

	/**
	 * Intercepteur pour la page affichant la page d'administration des comptes
	 * bancaires
	 *
	 * @param model
	 *            Modèle conteneur des données à passer à la vue
	 * @param account
	 *            Compte bancaire spécifique à traiter
	 * @param formAction
	 *            Action à passer au formulaire
	 * @return Vue à afficher
	 */
	@Override
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String administration(ModelMap model, Account account,
			String formAction) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Appel de la page /account/index");
		try {
			if (account == null)
				account = new Account();

			if (formAction == null)
				formAction = "create";

			model.addAttribute("entity", account);
			model.addAttribute("formAction", formAction);
			model.addAttribute("entities", ((AccountService) crudService)
					.readByUser((User) SecurityContextHolder.getContext()
							.getAuthentication().getPrincipal()));
		} catch (Exception e) {
			LOGGER.error(
					"Une erreur est survenue lors de l'affichage de la page d'adminsitration.",
					e);
		}
		return "/account/admin";
	}

	/**
	 * Intercepteur pour la page de création d'un compte bancaire
	 * 
	 * @param account
	 *            Compte bancaire à créer
	 * @param model
	 *            Modèle conteneur des données à passer à la vue
	 * @param redirectAttributes
	 *            Conteneur d'attributs utilisés après redirection (Message
	 *            Flash)
	 * @return Vue à afficher
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(Account account, ModelMap model,
			final RedirectAttributes redirectAttributes) {
		account.setUser((User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal());
		return super.create(account, model, redirectAttributes);
	}

	/**
	 * Intercepteur pour la page de mise à jour d'un compte bancaire
	 * 
	 * @param account
	 *            Compte bancaire à mettre à jour
	 * @param model
	 *            Modèle conteneur des données à passer à la vue
	 * @param redirectAttributes
	 *            Conteneur d'attributs utilisés après redirection (Message
	 *            Flash)
	 * @return Vue à afficher
	 */
	@RequestMapping(value = "/update.html", method = RequestMethod.POST)
	public String update(Account account, ModelMap model,
			final RedirectAttributes redirectAttributes) {
		account.setUser((User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal());
		return super.update(account, model, redirectAttributes);
	}
}
