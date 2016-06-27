package ru.nektodev.baskinov.importer.impl;


import com.yandex.disk.rest.Credentials;
import com.yandex.disk.rest.RestClient;
import com.yandex.disk.rest.exceptions.ServerException;
import org.springframework.stereotype.Service;
import ru.nektodev.baskinov.importer.WordImporter;
import ru.nektodev.baskinov.model.ImportData;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@Service
public class WordImporterImpl implements WordImporter {

	public static final Credentials CREDENTIALS = new Credentials("", "");
	private static File TEMP_DIR = new File("/Users/nektodev/Downloads/temp");
	private RestClient client;

	@PostConstruct
	public void init() {
		if (!TEMP_DIR.exists()) {
			TEMP_DIR.mkdirs();
		}

		if (!TEMP_DIR.isDirectory() || !TEMP_DIR.canWrite()) {
			//throw exception
		}

		client = new RestClient(CREDENTIALS);
	}

	@Override
	public void doImport(ImportData data) throws IOException, ServerException {
		File file = downloadFile(data);

	}

	private File downloadFile(ImportData data) throws IOException, ServerException {
		File saveTo = new File(TEMP_DIR, "temp"+ new Date().getTime());

		client.downloadPublicResource(data.getPublicKey(),
				data.getPath(),
				saveTo,
				null);
		return saveTo;
	}
}
