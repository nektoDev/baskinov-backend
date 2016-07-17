package ru.nektodev.baskinov.parser.impl;

import org.jopendocument.model.OpenDocument;
import org.jopendocument.model.table.TableTableRow;
import ru.nektodev.baskinov.model.ProgressData;
import ru.nektodev.baskinov.model.ProgressDataWrapper;

import java.io.File;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Slava Tsykin ts.slawa@gmail.com
 */
public class ODSProgressParser  extends AbstractProgressParser{

	@Override
	public ProgressDataWrapper[] doParse(File file, ProgressDataWrapper[] progressDataWrappers) {
		System.out.println(Arrays.toString(progressDataWrappers));
		final OpenDocument doc = new OpenDocument();
		doc.loadFrom(file);
		List<TableTableRow> rows = doc.getBody().getOfficeSpreadsheets().get(0).getTables().get(0).getRows();
		for (TableTableRow row : rows) {
			try {
				Date date = sdf.parse(row.getCellsInRange(0, 0)[0].getFullText());

				for (int i = 1; i < progressDataWrappers.length; i++) {
					String element = row.getCellsInRange(i, i)[0].getFullText();
					ProgressData progressData = getProgressData(date, element);
					if (progressData != null) progressDataWrappers[i].getValues().add(progressData);
				}
			} catch (ParseException ignored) {
			}

		}

		return progressDataWrappers;
	}
}
