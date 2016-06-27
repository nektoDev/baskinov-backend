package ru.nektodev.baskinov.importer;

import com.yandex.disk.rest.exceptions.ServerException;
import ru.nektodev.baskinov.model.ImportData;

import java.io.IOException;

public interface WordImporter {

	void doImport(ImportData data) throws IOException, ServerException;
}
