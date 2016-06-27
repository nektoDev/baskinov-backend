package ru.nektodev.baskinov.service.impl;

import com.yandex.disk.rest.exceptions.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nektodev.baskinov.importer.WordImporter;
import ru.nektodev.baskinov.model.Homework;
import ru.nektodev.baskinov.model.ImportData;
import ru.nektodev.baskinov.model.Student;
import ru.nektodev.baskinov.repository.StudentRepository;
import ru.nektodev.baskinov.service.ImporterService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ImporterServiceImpl implements ImporterService {
	@Autowired
	private WordImporter wordImporter;

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public String importAll() {
		try {
			ImportData importData = new ImportData();

			importData.setPath("/homework/vocabulary/en-ru.html");
			importData.setPublicKey("DhLa7f6nRVrD8AZj9EGmFkyE8goTvQr0vPDb6WsdgtQ%3D");

			wordImporter.doImport(importData);
		} catch (IOException | ServerException e) {
			e.printStackTrace();
		}
		return "OK";
	}

	private List<Student> generateStudents() {
		ArrayList<Student> result = new ArrayList<>();

		Student yulia = new Student();
		yulia.setName("yulia");

		Homework aydarVocabulary= new Homework();
		aydarVocabulary.setId("1");
		aydarVocabulary.setHomeworkDates(Collections.singletonList(new Date()));
		aydarVocabulary.setDownloadPath("https://cloud-api.yandex.net:443/v1/disk/public/resources/download?public_key=DhLa7f6nRVrD8AZj9EGmFkyE8goTvQr0vPDb6WsdgtQ%3D&path=%2Fhomework%2Fvocabulary%2Fen-ru.html");
		yulia.setVocabulary(aydarVocabulary);

		Homework aydarPronunciation = null;
		try {
			aydarPronunciation = (Homework) aydarVocabulary.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		assert aydarPronunciation != null;
		aydarPronunciation.setId("1");
		aydarPronunciation.setHomeworkDates(Collections.singletonList(new Date()));
		aydarPronunciation.setDownloadPath("https://cloud-api.yandex.net/v1/disk/public/resources/download?public_key=DhLa7f6nRVrD8AZj9EGmFkyE8goTvQr0vPDb6WsdgtQ%3D&path=%2Fhomework%2Fpronunciation%Yuliya%2Fpractice-and-check.html");
		yulia.setPronunciation(aydarPronunciation);

		result.add(yulia);

		return result;
	}
}
