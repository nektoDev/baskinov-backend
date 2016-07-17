package ru.nektodev.baskinov.importer;

import com.yandex.disk.rest.exceptions.ServerException;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public interface HomeworkImporter {
	Map<String, String> doImport(File file) throws IOException, ServerException, NoSuchAlgorithmException;
}
