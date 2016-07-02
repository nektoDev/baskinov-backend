package ru.nektodev.baskinov.service.impl;

import com.yandex.disk.rest.exceptions.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nektodev.baskinov.importer.HomeworkImporter;
import ru.nektodev.baskinov.model.*;
import ru.nektodev.baskinov.repository.StudentRepository;
import ru.nektodev.baskinov.repository.WordRepository;
import ru.nektodev.baskinov.service.ImporterService;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

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
				importHomework(student);
			} catch (IOException | ServerException | NoSuchAlgorithmException e) {
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
		} catch (IOException | ServerException | NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "ERROR";
		}

		return "OK";
	}

	private void importHomework(Student student) throws IOException, ServerException, NoSuchAlgorithmException {
		if (student.getPronunciation().getHomeworks() == null) {
			student.getPronunciation().setHomeworks(new ArrayList<>());
			studentRepository.save(student);
		}
		if (student.getVocabulary().getHomeworks() == null) {
			student.getVocabulary().setHomeworks(new ArrayList<>());
			studentRepository.save(student);
		}
		if (student.getWords() == null) {
			student.setWords(new HashSet<>());
			studentRepository.save(student);
		}

		importVocabularyHomework(student);
		importPronunciationHomework(student);
	}

	private void importPronunciationHomework(Student student) throws IOException, ServerException, NoSuchAlgorithmException {
		Map<String, Word> saveWords = new HashMap<>();

		ImportData importData = homeworkImporter.doImport(student.getPronunciation().getImportParams());
		Map<String, String> pronunciationMap = importData.getResult();

		boolean homeworkImported = student.getPronunciation().getHomeworks()
				.stream()
				.anyMatch(homework -> importData.getFileHash().equalsIgnoreCase(homework.getFileHash()));

		if (homeworkImported) {
			return;
		}

		pronunciationMap.forEach((title, pronunciation) -> {
			Word word = getWord(saveWords, title);
			if (word == null)
				word = new Word(title);

			if (word.getPronunciation() == null)
				word.setPronunciation(new HashMap<>());

			word.getPronunciation().put(student.getDialect(), pronunciation);
			saveWords.put(title, word);
		});

		student.getPronunciation().getHomeworks().add(getHomework(saveWords, importData.getFileHash()));
		student.getWords().addAll(saveWords.keySet());
		studentRepository.save(student);
		wordRepository.save(saveWords.values());
	}

	private Homework getHomework(Map<String, Word> saveWords, String fileHash) {
		Homework homework = new Homework();
		homework.setDate(new Date());
		homework.setFileHash(fileHash);
		homework.setWords(new ArrayList<>(saveWords.values()));
		return homework;
	}

	private void importVocabularyHomework(Student student) throws IOException, ServerException, NoSuchAlgorithmException {
		Map<String, Word> saveWords = new HashMap<>();

		ImportData importData = homeworkImporter.doImport(student.getVocabulary().getImportParams());

		boolean homeworkImported = student.getVocabulary().getHomeworks()
				.stream()
				.anyMatch(homework -> importData.getFileHash().equalsIgnoreCase(homework.getFileHash()));

		if (homeworkImported) {
			return;
		}

		Map<String, String> vocabularyMap = importData.getResult();

		vocabularyMap.forEach((title, translation) -> {
			Word word = getWord(saveWords, title);

			if (word == null)
				word = new Word(title);

			if (word.getTranslation() == null)
				word.setTranslation(new HashSet<>());

			Collections.addAll(word.getTranslation(), translation.split(","));
			saveWords.put(title, word);
		});

		student.getVocabulary().getHomeworks().add(getHomework(saveWords, importData.getFileHash()));
		student.getWords().addAll(saveWords.keySet());
		studentRepository.save(student);
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
