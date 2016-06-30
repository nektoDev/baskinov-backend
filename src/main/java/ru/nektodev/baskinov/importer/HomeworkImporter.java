package ru.nektodev.baskinov.importer;

import com.yandex.disk.rest.exceptions.ServerException;
import ru.nektodev.baskinov.model.ImportData;
import ru.nektodev.baskinov.model.ImportParams;

import java.io.IOException;

public interface HomeworkImporter {

	ImportData doImport(ImportParams params) throws IOException, ServerException;
}
