package ru.nektodev.baskinov.importer.impl;


import com.yandex.disk.rest.Credentials;
import com.yandex.disk.rest.RestClient;
import com.yandex.disk.rest.exceptions.ServerException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import ru.nektodev.baskinov.importer.WordImporter;
import ru.nektodev.baskinov.model.ImportData;
import ru.nektodev.baskinov.model.Student;
import ru.nektodev.baskinov.model.Word;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.*;

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
	public List<Word> doImport(Student student) throws IOException, ServerException {
		List<Word> result = new ArrayList<>();

		File vocabularyFile = downloadFile(student.getVocabulary().getImportData(), "voc_"+student.getName());
		Map<String, String> vocabularyWordsMap = parseFile(vocabularyFile);
		vocabularyWordsMap.forEach((word, translation) -> {
			Word w = new Word();
			w.setWord(word);
			w.setTranslation(translation);
			result.add(w);
		});

		File pronunciationFile = downloadFile(student.getPronunciation().getImportData(), "pron_"+student.getName());
		Map<String, String> pronunciationWordMap = parseFile(pronunciationFile);
		pronunciationWordMap.forEach((word, pronunciation) -> {
			Word w = new Word();
			w.setWord(word);
			w.setPronunciation(pronunciation);
			result.add(w);
		});

		return result;
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

	private File downloadFile(ImportData data, String name) throws IOException, ServerException {
		File saveTo = new File(TEMP_DIR, "name"+ new Date().getTime());

		client.downloadPublicResource(data.getPublicKey(),
				data.getPath(),
				saveTo,
				null);
		return saveTo;
	}
}
