package ru.nektodev.baskinov.service.impl;

import com.yandex.disk.rest.exceptions.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nektodev.baskinov.importer.HomeworkImporter;
import ru.nektodev.baskinov.model.Student;
import ru.nektodev.baskinov.model.Word;
import ru.nektodev.baskinov.repository.StudentRepository;
import ru.nektodev.baskinov.repository.WordRepository;
import ru.nektodev.baskinov.service.ImporterService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImporterServiceImpl implements ImporterService {
	@Autowired
	private HomeworkImporter homeworkImporter;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private WordRepository wordRepository;

	@Override
	public String importAll() {
		Map<String, Word> saveWords = new HashMap<>();

		for (Student student : studentRepository.findAll()) {
			try {
				Map<String, String> vocabularyMap = homeworkImporter.doImport(student.getVocabulary().getImportParams());

				vocabularyMap.forEach((translation, title) -> {
					Word word = getWord(saveWords, title);
					if (word == null)
						word = new Word(title);

					word.setTranslation(translation);
					saveWords.put(title, word);
				});

				Map<String, String> pronunciationMap = homeworkImporter.doImport(student.getVocabulary().getImportParams());

				pronunciationMap.forEach((translation, title) -> {
					Word word = getWord(saveWords, title);
					if (word == null)
						word = new Word(title);

					word.setPronunciation(translation);
					saveWords.put(title, word);
				});

			} catch (IOException | ServerException e) {
				e.printStackTrace();
			}
		}

		wordRepository.save(saveWords.values());
		return "OK";
	}

	private Word getWord(Map<String, Word> saveWords, String title) {
		Word word;
		if (saveWords.containsKey(title)) {
			word = saveWords.get(title);
		} else {
			word = wordRepository.findOne(title);
		}
		return word;
	}

/*	private List<Student> generateStudents() {
		ArrayList<Student> result = new ArrayList<>();

		Student yulia = new Student();
		yulia.setName("aydar");

		Task aydarVocabulary= new Task();
		aydarVocabulary.setId("1");
//		aydarVocabulary.setHomeworkDates(Collections.singletonList(new Date()));
		ImportParams importData = new ImportParams();

		importData.setPath("/homework/vocabulary/en-ru.html");
		importData.setPublicKey("DhLa7f6nRVrD8AZj9EGmFkyE8goTvQr0vPDb6WsdgtQ%3D");
		aydarVocabulary.setImportParams(importData);
		yulia.setVocabulary(aydarVocabulary);

		Task aydarPronunciation = null;
		try {
			aydarPronunciation = (Task) aydarVocabulary.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		assert aydarPronunciation != null;
		aydarPronunciation.setId("1");
//		aydarPronunciation.setHomeworkDates(Collections.singletonList(new Date()));
		ImportParams importData2 = new ImportParams();

		importData2.setPath("/homework/pronunciation/Aydar/practice-and-check.html");
		importData2.setPublicKey("DhLa7f6nRVrD8AZj9EGmFkyE8goTvQr0vPDb6WsdgtQ%3D");
		aydarPronunciation.setImportParams(importData2);
		yulia.setPronunciation(aydarPronunciation);
		result.add(yulia);

		return result;
	}*/
}
