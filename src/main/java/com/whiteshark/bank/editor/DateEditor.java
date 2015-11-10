package com.whiteshark.bank.editor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateEditor extends PropertyEditorSupport {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DateEditor.class);

	/**
	 * Conversion d'une valeur de date fournit par un formulaire en objet Date
	 * 
	 * @param value
	 *            Date fournit
	 * 
	 * @return Date
	 */
	@Override
	public void setAsText(String value) throws IllegalArgumentException {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Conversion de la date {} en objet date.", value);

		Date result = null;

		try {
			result = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(value);
		} catch (ParseException e) {
			LOGGER.error(
					"Erreur durant la conversion de la date {} en objet date.",
					value, e);
		}
		setValue(result);
	}

	/**
	 * Conversion d'un object Date en champ texte pour formulaire
	 * 
	 * @return Valeur pour champ de formulaire
	 */
	@Override
	public String getAsText() {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Conversion de l'objet date en champ texte.");

		Date date = (Date) getValue();

		if (date != null)
			return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date);

		return null;
	}
}