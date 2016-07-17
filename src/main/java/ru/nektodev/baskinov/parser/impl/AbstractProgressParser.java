package ru.nektodev.baskinov.parser.impl;

import ru.nektodev.baskinov.model.ProgressData;
import ru.nektodev.baskinov.parser.ProgressParser;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Slava Tsykin ts.slawa@gmail.com
 */
public abstract class AbstractProgressParser implements ProgressParser {

	protected static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

	protected ProgressData getProgressData(Date date, String value) {
		if (!value.matches("\\d.*%")) {
			return null;
		}

		ProgressData result = new ProgressData();
		result.setDate(date);
		result.setValue(Integer.valueOf(value.replace("%", "")));

		return result;
	}
}
