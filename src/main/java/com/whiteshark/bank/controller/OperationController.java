package com.whiteshark.bank.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.whiteshark.bank.editor.DateEditor;
import com.whiteshark.bank.model.Account;
import com.whiteshark.bank.model.Operation;
import com.whiteshark.bank.model.User;
import com.whiteshark.bank.service.AccountService;
import com.whiteshark.bank.service.OperationService;

@Controller
@RequestMapping(value = "/operation")
@SessionAttributes("account")
public class OperationController extends CrudController<Operation> {

	private AccountService accountService;

	/**
	 * Définition du service de gestion des opérations bancaires
	 * 
	 * @param operationService
	 *            Service de gestion opérations bancaires
	 */
	@Autowired
	public void setOperationService(OperationService operationService) {
		crudService = operationService;
	}

	/**
	 * Définition du service de gestion des comptes bancaires
	 * 
	 * @param accountService
	 *            Service de gestion des comptes bancaires
	 */
	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	/**
	 * Conversion des éléments pour prise en charge des formulaires
	 * 
	 * @param dataBinder
	 *            Gestionnaire de conversion
	 * @throws Exception
	 */
	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) throws Exception {
		dataBinder.registerCustomEditor(Date.class, new DateEditor());
	}

	/**
	 * Intercepteur pour la page affichant la page d'administration des
	 * opérations bancaires
	 *
	 * @param model
	 *            Modèle conteneur des données à passer à la vue
	 * @param operation
	 *            Opération bancaire spécifique à traiter
	 * @param formAction
	 *            Action à passer au formulaire
	 * @return Vue à afficher
	 */
	@Override
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String administration(ModelMap model, Operation operation,
			String formAction) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Appel de la page /operation/index.");

		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (operation == null)
			operation = new Operation();

		if (formAction == null)
			formAction = "create";

		Account account = (Account) model.get("account");

		if (account == null)
			account = accountService.readDefault();

		model.addAttribute("account", account);
		model.addAttribute("accounts", accountService.readByUser(user));
		model.addAttribute("entity", operation);
		model.addAttribute("formAction", formAction);
		model.addAttribute("entities",
				((OperationService) crudService).readByAccount(account));

		return "/operation/admin";
	}

	/**
	 * Intercepteur pour la page de création d'une entité
	 * 
	 * @param operation
	 *            Opération bancaire à créer
	 * @param model
	 *            Modèle conteneur des données à passer à la vue
	 * @param redirectAttributes
	 *            Conteneur d'attributs utilisés après redirection (Message
	 *            Flash)
	 * @return Vue à afficher
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(Operation operation, ModelMap model,
			final RedirectAttributes redirectAttributes) {
		operation.setAccount((Account) model.get("account"));
		return super.create(operation, model, redirectAttributes);
	}

	/**
	 * Intercepteur pour la page de mise à jour d'une entité
	 * 
	 * @param operation
	 *            Opération bancaire à mettre à jour
	 * @param model
	 *            Modèle conteneur des données à passer à la vue
	 * @param redirectAttributes
	 *            Conteneur d'attributs utilisés après redirection (Message
	 *            Flash)
	 * @return Vue à afficher
	 */
	@RequestMapping(value = "/update.html", method = RequestMethod.POST)
	public String update(Operation operation, ModelMap model,
			final RedirectAttributes redirectAttributes) {
		operation.setAccount((Account) model.get("account"));
		return super.update(operation, model, redirectAttributes);
	}

	/**
	 * Intercepteur pour le changement de compte bancaire
	 *
	 * @param model
	 *            Modèle conteneur des données à passer à la vue
	 * @param account
	 *            Compte bancaire à afficher
	 * @return Vue à afficher
	 */
	@RequestMapping(value = "/changeAccount", method = RequestMethod.GET)
	public String setAccount(ModelMap model, Account account) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Demande d'affichage du compte {}.", account);

		model.addAttribute("account", account);
		return administration(model, null, null);
	}

}
