package ru.nektodev.baskinov.service.impl;

import com.yandex.disk.rest.exceptions.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nektodev.baskinov.importer.HomeworkImporter;
import ru.nektodev.baskinov.model.Homework;
import ru.nektodev.baskinov.model.ImportData;
import ru.nektodev.baskinov.model.Student;
import ru.nektodev.baskinov.model.Word;
import ru.nektodev.baskinov.repository.StudentRepository;
import ru.nektodev.baskinov.repository.WordRepository;
import ru.nektodev.baskinov.service.ImporterService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
	public String importAllStudents() {

		for (Student student : studentRepository.findAll()) {
			try {
				student.getVocabulary().setHomeworks(new ArrayList<>());
				student.getPronunciation().setHomeworks(new ArrayList<>());
				importHomework(student);
			} catch (IOException | ServerException e) {
				e.printStackTrace();
				return "ERROR";
			}
		}

		return studentRepository.findAll().toString();
	}

	@Override
	public String importStudentHomework(String studentId) {

		Student student = studentRepository.findOne(studentId);
		try {
			importHomework(student);
		} catch (IOException | ServerException e) {
			e.printStackTrace();
			return "ERROR";
		}

		return "OK";
	}

	private void importHomework(Student student) throws IOException, ServerException {
		if (student.getPronunciation().getHomeworks() == null) {
			student.getPronunciation().setHomeworks(new ArrayList<>());
			studentRepository.save(student);
		}
		if (student.getVocabulary().getHomeworks() == null) {
			student.getVocabulary().setHomeworks(new ArrayList<>());
			studentRepository.save(student);
		}

		importVocabularyHomework(student);
		importPronunciationHomework(student);
	}

	private void importPronunciationHomework(Student student) throws IOException, ServerException {
		Map<String, Word> saveWords = new HashMap<>();

		ImportData importData = homeworkImporter.doImport(student.getPronunciation().getImportParams());
		Map<String, String> pronunciationMap = importData.getResult();

		pronunciationMap.forEach((title, pronunciation) -> {
			Word word = getWord(saveWords, title);
			if (word == null)
				word = new Word(title);

			word.setPronunciation(pronunciation);
			saveWords.put(title, word);
		});

		student.getPronunciation().getHomeworks().add(getHomework(saveWords));
		studentRepository.save(student);
		wordRepository.save(saveWords.values());
	}

	private Homework getHomework(Map<String, Word> saveWords) {
		Homework homework = new Homework();
		homework.setDate(new Date());
		homework.setWords(new ArrayList<>(saveWords.values()));
		return homework;
	}

	private void importVocabularyHomework(Student student) throws IOException, ServerException {
		Map<String, Word> saveWords = new HashMap<>();

		ImportData importData = homeworkImporter.doImport(student.getVocabulary().getImportParams());
		Map<String, String> vocabularyMap = importData.getResult();

		vocabularyMap.forEach((title, translation) -> {
			Word word = getWord(saveWords, title);
			if (word == null)
				word = new Word(title);

			word.setTranslation(translation);
			saveWords.put(title, word);
		});

		student.getVocabulary().getHomeworks().add(getHomework(saveWords));

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
