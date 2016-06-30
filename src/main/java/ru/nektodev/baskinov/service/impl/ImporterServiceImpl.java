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
		for (Student student : studentRepository.findAll()) {
			try {
				importVocabularyHomework(student);
				importPronunciationHomework(student);
			} catch (IOException | ServerException e) {
				e.printStackTrace();
			}
		}

		return "OK";
	}

	private void importPronunciationHomework(Student student) throws IOException, ServerException {
		Map<String, Word> saveWords = new HashMap<>();

		Map<String, String> pronunciationMap = homeworkImporter.doImport(student.getPronunciation().getImportParams());

		pronunciationMap.forEach((title, pronunciation) -> {
			Word word = getWord(saveWords, title);
			if (word == null)
				word = new Word(title);

			word.setPronunciation(pronunciation);
			saveWords.put(title, word);
		});
		wordRepository.save(saveWords.values());
	}

	private void importVocabularyHomework(Student student) throws IOException, ServerException {
		Map<String, Word> saveWords = new HashMap<>();

		Map<String, String> vocabularyMap = homeworkImporter.doImport(student.getVocabulary().getImportParams());

		vocabularyMap.forEach((title, translation) -> {
			Word word = getWord(saveWords, title);
			if (word == null)
				word = new Word(title);

			word.setTranslation(translation);
			saveWords.put(title, word);
		});
		wordRepository.save(saveWords.values());
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
}
