package ru.nektodev.baskinov.service.impl;

import com.yandex.disk.rest.exceptions.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nektodev.baskinov.importer.WordImporter;
import ru.nektodev.baskinov.model.Homework;
import ru.nektodev.baskinov.model.ImportData;
import ru.nektodev.baskinov.model.Student;
import ru.nektodev.baskinov.model.Word;
import ru.nektodev.baskinov.repository.StudentRepository;
import ru.nektodev.baskinov.repository.WordRepository;
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

	@Autowired
	private WordRepository wordRepository;

	@Override
	public String importAll() {
		for (Student student : studentRepository.findAll()) {
			try {
				List<Word> words = wordImporter.doImport(student);
				wordRepository.save(words);
			} catch (IOException | ServerException e) {
				e.printStackTrace();
			}
		}



		return "OK";
	}

	private List<Student> generateStudents() {
		ArrayList<Student> result = new ArrayList<>();

		Student yulia = new Student();
		yulia.setName("aydar");

		Homework aydarVocabulary= new Homework();
		aydarVocabulary.setId("1");
		aydarVocabulary.setHomeworkDates(Collections.singletonList(new Date()));
		ImportData importData = new ImportData();

		importData.setPath("/homework/vocabulary/en-ru.html");
		importData.setPublicKey("DhLa7f6nRVrD8AZj9EGmFkyE8goTvQr0vPDb6WsdgtQ%3D");
		aydarVocabulary.setImportData(importData);
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
		ImportData importData2 = new ImportData();

		importData2.setPath("/homework/pronunciation/Aydar/practice-and-check.html");
		importData2.setPublicKey("DhLa7f6nRVrD8AZj9EGmFkyE8goTvQr0vPDb6WsdgtQ%3D");
		aydarPronunciation.setImportData(importData2);
		yulia.setPronunciation(aydarPronunciation);
		result.add(yulia);

		return result;
	}
}
