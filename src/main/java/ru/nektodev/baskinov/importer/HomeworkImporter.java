package ru.nektodev.baskinov.importer;

import com.yandex.disk.rest.exceptions.ServerException;
import ru.nektodev.baskinov.model.ImportParams;

import java.io.IOException;
import java.util.Map;

public interface HomeworkImporter {

	Map<String, String> doImport(ImportParams params) throws IOException, ServerException;
}
