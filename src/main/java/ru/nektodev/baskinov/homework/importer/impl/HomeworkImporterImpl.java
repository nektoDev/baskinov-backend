package ru.nektodev.baskinov.homework.importer.impl;

import com.yandex.disk.rest.Credentials;
import com.yandex.disk.rest.RestClient;
import com.yandex.disk.rest.exceptions.ServerException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.nektodev.baskinov.homework.importer.HomeworkImporter;
import ru.nektodev.baskinov.model.ImportData;
import ru.nektodev.baskinov.model.ImportParams;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class HomeworkImporterImpl implements HomeworkImporter {

	private static final Credentials CREDENTIALS = new Credentials("", "");

	@Value("${importer.temp.directory}")
	private String tempDirPath;

	private File tempDir;
	private RestClient client;

	@PostConstruct
	public void init() {
		tempDir = new File(tempDirPath);

		if (!tempDir.exists()) {
			tempDir.mkdirs();
		}

		if (!tempDir.isDirectory() || !tempDir.canWrite()) {
			//throw exception
		}

		client = new RestClient(CREDENTIALS);
	}

	@Override
	public ImportData doImport(ImportParams params) throws IOException, ServerException, NoSuchAlgorithmException {
		ImportData result = new ImportData();

		File file = downloadFile(params);
		result.setResult(parseFile(file));
		result.setFileHash(calculateFileHash(file));
		return result;
	}

	private String calculateFileHash(File file) throws IOException, NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("MD5");
		FileInputStream fis = new FileInputStream(file);
		byte[] dataBytes = new byte[1024];
		int nread;
		while ((nread = fis.read(dataBytes)) != -1) {
			md.update(dataBytes, 0, nread);
		};
		byte[] mdbytes = md.digest();

		StringBuilder sb = new StringBuilder("");
		for (byte mdbyte : mdbytes) {
			sb.append(Integer.toString((mdbyte & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
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
		File saveTo = new File(tempDir, "temp_"+ new Date().getTime());

		client.downloadPublicResource(params.getPublicKey(),
				params.getPath(),
				saveTo,
				null);
		return saveTo;
	}
}
