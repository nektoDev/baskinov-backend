package ru.nektodev.baskinov.parser.impl;

import ru.nektodev.baskinov.model.ProgressData;
import ru.nektodev.baskinov.parser.ProgressParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Slava Tsykin ts.slawa@gmail.com
 */
public abstract class AbstractProgressParser implements ProgressParser {

	protected static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	protected static SimpleDateFormat sdf_for_unformatted = new SimpleDateFormat("dd.MM.yy");

	protected ProgressData getProgressData(Date date, String value) {
		if (!value.matches("\\d.*%")) {
			return null;
		}

		ProgressData result = new ProgressData();
		result.setDate(date);
		result.setValue(Integer.valueOf(value.replace("%", "")));

		return result;
	}

	protected Date parseDate(String dateString) throws ParseException {
		if (dateString.length() == 10) {
			return sdf.parse(dateString);
		}
		if (dateString.length() == 8) {
			return sdf_for_unformatted.parse(dateString);
		}

		System.err.println("Unknown date format in progress");
		return new Date(0);
	}
}
