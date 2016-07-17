package ru.nektodev.baskinov.parser;

import ru.nektodev.baskinov.model.Progress;
import ru.nektodev.baskinov.model.ProgressType;
import ru.nektodev.baskinov.parser.impl.ODSProgressParser;
import ru.nektodev.baskinov.parser.impl.PDFProgressParser;

/**
 * @author Slava Tsykin ts.slawa@gmail.com
 */
public class ProgressParserFactory {
	public ProgressParser getParser(Progress progress) {
		if (ProgressType.ODS.equals(progress.getType())) {
			return new ODSProgressParser();
		}

		if (ProgressType.PDF.equals(progress.getType())) {
			return new PDFProgressParser();
		}

		return null;
	}

}
