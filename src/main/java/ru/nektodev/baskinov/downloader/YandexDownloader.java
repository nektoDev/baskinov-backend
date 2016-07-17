package ru.nektodev.baskinov.downloader;

import com.yandex.disk.rest.Credentials;
import com.yandex.disk.rest.RestClient;
import com.yandex.disk.rest.exceptions.ServerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.nektodev.baskinov.model.ImportParams;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author Slava Tsykin ts.slawa@gmail.com
 */

@Service
public class YandexDownloader {
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

	public File downloadFile(ImportParams params) throws IOException, ServerException {
		File saveTo = new File(tempDir, "temp_"+ new Date().getTime());

		client.downloadPublicResource(params.getPublicKey(),
				params.getPath(),
				saveTo,
				null);
		return saveTo;
	}
}
