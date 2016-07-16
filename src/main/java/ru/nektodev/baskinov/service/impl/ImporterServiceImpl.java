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
	public String importAllStudentsHomework() {

		for (Student student : studentRepository.findAll()) {
			try {
				importHomework(student);
			} catch (IOException | ServerException | NoSuchAlgorithmException e) {
				e.printStackTrace();
				return "ERROR";
			}
		}

		return "OK";
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

		boolean homeworkImported = student.getPronunciation().getHomeworks()
				.stream()
				.anyMatch(homework -> importData.getFileHash().equalsIgnoreCase(homework.getFileHash()));

		if (homeworkImported) {
			return;
		}

		Map<String, String> pronunciationMap = importData.getResult();
		List<HomeworkWord> homeworkWords = new ArrayList<>();

		pronunciationMap.forEach((title, pronunciation) -> {
			Word word = getWord(saveWords, title, student.getId());

			if (word.getPronunciation() == null)
				word.setPronunciation(new HashMap<>());

			word.getPronunciation().put(student.getDialect(), pronunciation);
			saveWords.put(title, word);
			homeworkWords.add(new HomeworkWord(title, pronunciation, title));
		});

		student.getPronunciation().getHomeworks().add(getHomework(homeworkWords, importData.getFileHash()));
		student.getWords().addAll(pronunciationMap.keySet());
		studentRepository.save(student);
		wordRepository.save(saveWords.values());
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
		List<HomeworkWord> homeworkWords = new ArrayList<>();

		vocabularyMap.forEach((translation, title) -> {
			Word word = getWord(saveWords, title, student.getId());

			if (word.getTranslation() == null)
				word.setTranslation(new HashSet<>());

			word.getTranslation().add(translation);
			saveWords.put(title, word);
			homeworkWords.add(new HomeworkWord(title, translation, title));
		});

		student.getVocabulary().getHomeworks().add(getHomework(homeworkWords, importData.getFileHash()));
		student.getWords().addAll(vocabularyMap.values());
		studentRepository.save(student);
		wordRepository.save(saveWords.values());
	}

	private Homework getHomework(List<HomeworkWord> words, String fileHash) {
		Homework homework = new Homework();
		homework.setDate(new Date());
		homework.setFileHash(fileHash);
		homework.setWords(words);
		return homework;
	}


	private Word getWord(Map<String, Word> saveWords, String title, String student) {
		Word word;
		if (saveWords.containsKey(title)) {
			word = saveWords.get(title);
		} else {
			word = wordRepository.findOne(title);
		}

		if (word == null) {
			word = new Word(student, title);
		} else {
			Integer countUses = word.getCountUses().getOrDefault(student, 0);
			word.getCountUses().put(student, ++countUses);
			word.getFirstAppeared().putIfAbsent(student, new Date());
			word.getLastUsed().put(student, new Date());
		}

		return word;
	}
}
