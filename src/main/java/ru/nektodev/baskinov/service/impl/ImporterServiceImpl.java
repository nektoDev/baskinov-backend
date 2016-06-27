package ru.nektodev.baskinov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nektodev.baskinov.importer.WordImporter;
import ru.nektodev.baskinov.model.Homework;
import ru.nektodev.baskinov.model.Student;
import ru.nektodev.baskinov.repository.StudentRepository;
import ru.nektodev.baskinov.service.ImporterService;

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
	public String doImport() {
		studentRepository.save(generateStudents());
		wordImporter.doImport();
		return "OK";
	}

	private List<Student> generateStudents() {
		ArrayList<Student> result = new ArrayList<>();

		Student aydar = new Student();
		aydar.setName("aydar");

		Homework aydarVocabulary= new Homework();
		aydarVocabulary.setId("1");
		aydarVocabulary.setHomeworkDates(Collections.singletonList(new Date()));
		aydarVocabulary.setDownloadPath("https://cloud-api.yandex.net:443/v1/disk/public/resources/download?public_key=DhLa7f6nRVrD8AZj9EGmFkyE8goTvQr0vPDb6WsdgtQ%3D&path=%2Fhomework%2Fvocabulary%2Fen-ru.html");
		aydar.setVocabulary(aydarVocabulary);

		Homework aydarPronunciation = null;
		try {
			aydarPronunciation = (Homework) aydarVocabulary.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		assert aydarPronunciation != null;
		aydarPronunciation.setId("1");
		aydarPronunciation.setHomeworkDates(Collections.singletonList(new Date()));
		aydarPronunciation.setDownloadPath("https://cloud-api.yandex.net/v1/disk/public/resources/download?public_key=DhLa7f6nRVrD8AZj9EGmFkyE8goTvQr0vPDb6WsdgtQ%3D&path=%2Fhomework%2Fpronunciation%2FAydar%2Fpractice-and-check.html");
		aydar.setPronunciation(aydarPronunciation);

		result.add(aydar);

		return result;
	}
}
