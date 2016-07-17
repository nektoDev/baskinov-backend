package ru.nektodev.baskinov.importer.impl;

import com.yandex.disk.rest.exceptions.ServerException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nektodev.baskinov.downloader.YandexDownloader;
import ru.nektodev.baskinov.importer.HomeworkImporter;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Service
public class HomeworkImporterImpl implements HomeworkImporter {

	@Autowired
	private YandexDownloader downloader;

	@Override
	public Map<String, String> doImport(File file) throws IOException, ServerException, NoSuchAlgorithmException {
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
}
