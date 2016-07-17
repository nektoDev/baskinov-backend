package ru.nektodev.baskinov.parser;

import ru.nektodev.baskinov.model.ProgressDataWrapper;

import java.io.File;

/**
 * @author Slava Tsykin ts.slawa@gmail.com
 */
public interface ProgressParser {
	ProgressDataWrapper[] doParse(File file, ProgressDataWrapper[] progressDataWrappers);
}
