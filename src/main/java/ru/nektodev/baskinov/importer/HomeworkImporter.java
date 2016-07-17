package ru.nektodev.baskinov.importer;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface HomeworkImporter {
	Map<String, String> doImport(File file) throws IOException;
}
