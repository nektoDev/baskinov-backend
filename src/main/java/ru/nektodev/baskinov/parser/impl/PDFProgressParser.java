package ru.nektodev.baskinov.parser.impl;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import ru.nektodev.baskinov.model.ProgressData;
import ru.nektodev.baskinov.model.ProgressDataWrapper;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * @author Slava Tsykin ts.slawa@gmail.com
 */
public class PDFProgressParser extends AbstractProgressParser {

	@Override
	public ProgressDataWrapper[] doParse(File file, ProgressDataWrapper[] progressDataWrappers) {
		PDFTextStripper pdfStripper;
		PDDocument pdDoc;
		COSDocument cosDoc;
		PDFParser parser = null;
		try {
			parser = new PDFParser(new RandomAccessFile(file, "r"));
			parser.parse();
			cosDoc = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdDoc = new PDDocument(cosDoc);
			String parsedText = pdfStripper.getText(pdDoc);

			for (String row : parsedText.split("\\n")) {
				String[] elements = row.split(" ");
				if (elements.length > 0) {
					try {
						Date date = sdf.parse(elements[0]);

						for (int i = 1; i < elements.length; i++) {
							String element = elements[i];
							ProgressData progressData = getProgressData(date, element);
							if (progressData != null) progressDataWrappers[i].getValues().add(progressData);
						}
					} catch (ParseException ignored) {
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (parser != null) {
					parser.getPDDocument().close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return progressDataWrappers;
	}
}
