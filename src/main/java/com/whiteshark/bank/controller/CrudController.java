package com.whiteshark.bank.controller;

import java.lang.reflect.ParameterizedType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.whiteshark.bank.service.CrudService;

public abstract class CrudController<E> {
	protected static final Logger LOGGER = LoggerFactory
			.getLogger(CrudController.class);

	private Class<E> entityBeanType;

	protected CrudService<E> crudService;

	/**
	 * Constructeur par défaut Défini le type de l'entité
	 */
	@SuppressWarnings("unchecked")
	public CrudController() {
		this.entityBeanType = (Class<E>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * Intercepteur pour la page affichant la page d'administration des entités
	 *
	 * @param model
	 *            Modèle conteneur des données à passer à la vue
	 * @param entity
	 *            entité spécifique à traiter
	 * @param formAction
	 *            Action à passer au formulaire
	 * @return Vue à afficher
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String administration(ModelMap model, E entity, String formAction) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Appel de la page /{}/index.", entityBeanType
					.getSimpleName().toLowerCase());
		try {
			if (entity == null)
				entity = entityBeanType.newInstance();

			if (formAction == null)
				formAction = "create";

			model.addAttribute("entity", entity);
			model.addAttribute("formAction", formAction);
			model.addAttribute("entities", crudService.readAll());
		} catch (Exception e) {
			LOGGER.error(
					"Une erreur est survenue lors de l'affichage de la page d'adminsitration.",
					e);
		}
		return "/" + entityBeanType.getSimpleName().toLowerCase() + "/admin";
	}

	/**
	 * Intercepteur pour la page de création d'une entité
	 * 
	 * @param entity
	 *            Entité à créer
	 * @param model
	 *            Modèle conteneur des données à passer à la vue
	 * @param redirectAttributes
	 *            Conteneur d'attributs utilisés après redirection (Message
	 *            Flash)
	 * @return Vue à afficher
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(E entity, ModelMap model,
			final RedirectAttributes redirectAttributes) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug(
					"Appel de la page /{}/create pour création de l'entité {}.",
					entityBeanType.getSimpleName().toLowerCase(), entity);

		String page = null;

		try {
			crudService.create(entity);
			redirectAttributes.addFlashAttribute("message",
					"L'entité a été créé avec succès");
			page = "redirect:/" + entityBeanType.getSimpleName().toLowerCase()
					+ "/index.html";
		} catch (Exception e) {
			LOGGER.error(
					"Une erreur est survenue lors de l'ajout de l'entité {}.",
					entity, e);
			page = administration(model, entity, "create");
		}
		return page;
	}

	/**
	 * Intercepteur pour la page de lecture d'une entité
	 * 
	 * @param id
	 *            Identifiant de l'entité
	 * @param model
	 *            Modèle conteneur des données à passer à la vue
	 * @return Vue à afficher
	 */
	@RequestMapping(value = "/read/{id}.html", method = RequestMethod.GET)
	public String read(@PathVariable Long id, ModelMap model) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Appel de la page /{}/read/{}.html", entityBeanType
					.getSimpleName().toLowerCase(), id);

		E entity = crudService.readById(id);
		return administration(model, entity, "update");
	}

	/**
	 * Intercepteur pour la page de mise à jour d'une entité
	 * 
	 * @param entity
	 *            Entité à mettre à jour
	 * @param model
	 *            Modèle conteneur des données à passer à la vue
	 * @param redirectAttributes
	 *            Conteneur d'attributs utilisés après redirection (Message
	 *            Flash)
	 * @return Vue à afficher
	 */
	@RequestMapping(value = "/update.html", method = RequestMethod.POST)
	public String update(E entity, ModelMap model,
			final RedirectAttributes redirectAttributes) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug(
					"Appel de la page /{}/update pour modification de l'entité {}.",
					entityBeanType.getSimpleName().toLowerCase(), entity);

		String page = null;

		try {
			crudService.update(entity);
			redirectAttributes.addFlashAttribute("message",
					"L'entité a été mis à jour avec succès");
			page = "redirect:/" + entityBeanType.getSimpleName().toLowerCase()
					+ "/index.html";
		} catch (Exception e) {
			LOGGER.error(
					"Une erreur est survenue lors de la mise à jour de l'élément {}.",
					entity, e);
			page = administration(model, entity, "update");
		}
		return page;
	}

	/**
	 * Intercepteur pour la suppression d'une entité
	 * 
	 * @param id
	 *            Identifiant de l'entité à supprimer
	 * @param model
	 *            Modèle conteneur des données à passer à la vue
	 * @param redirectAttributes
	 *            Conteneur d'attributs utilisés après redirection (Message
	 *            Flash)
	 * @return Vue à afficher
	 */
	@RequestMapping(value = "/delete/{id}.html", method = RequestMethod.GET)
	public String delete(@PathVariable Long id, ModelMap model,
			final RedirectAttributes redirectAttributes) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug(
					"Appel de la page /{}/update pour suppression de l'entité avec l'identifiant {}.",
					entityBeanType.getSimpleName().toLowerCase(), id);

		try {
			crudService.delete(id);
			redirectAttributes.addFlashAttribute("message",
					"L'entité a été supprimé avec succès");
		} catch (Exception e) {
			LOGGER.error(
					"Une erreur est survenue lors de la suppression l'entité avec l'identifiant {}.",
					id, e);
		}
		return "redirect:/" + entityBeanType.getSimpleName().toLowerCase()
				+ "/index.html";
	}
}