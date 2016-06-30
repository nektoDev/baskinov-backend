package ru.nektodev.baskinov.importer.impl;


import com.yandex.disk.rest.Credentials;
import com.yandex.disk.rest.RestClient;
import com.yandex.disk.rest.exceptions.ServerException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import ru.nektodev.baskinov.importer.HomeworkImporter;
import ru.nektodev.baskinov.model.ImportParams;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class HomeworkImporterImpl implements HomeworkImporter {

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
	public Map<String, String> doImport(ImportParams params) throws IOException, ServerException {
		File vocabularyFile = downloadFile(params);

		return parseFile(vocabularyFile);
	}

	private Map<String, String> parseFile(File file) throws IOException {
		Map<String, String> result = new HashMap<>();

		Document document = Jsoup.parse(file, "utf8");

		Elements wordElements = document.getElementsByClass("qa-wrapper");
		for (Element wordElement : wordElements) {
			Element question = wordElement.getElementsByClass("question").get(0);
			Element answer = wordElement.getElementsByClass("answer").get(0);
			result.put(question.text(), answer.text());
		}

		return result;
	}

	private File downloadFile(ImportParams params) throws IOException, ServerException {
		File saveTo = new File(TEMP_DIR, "temp_"+ new Date().getTime());

		client.downloadPublicResource(params.getPublicKey(),
				params.getPath(),
				saveTo,
				null);
		return saveTo;
	}
}