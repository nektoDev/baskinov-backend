package ru.nektodev.baskinov.service.impl;

import com.yandex.disk.rest.exceptions.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nektodev.baskinov.downloader.YandexDownloader;
import ru.nektodev.baskinov.model.*;
import ru.nektodev.baskinov.parser.HomeworkParser;
import ru.nektodev.baskinov.parser.ProgressParserFactory;
import ru.nektodev.baskinov.repository.ProgressRepository;
import ru.nektodev.baskinov.repository.StudentRepository;
import ru.nektodev.baskinov.repository.WordRepository;
import ru.nektodev.baskinov.service.ImporterService;
import ru.nektodev.baskinov.util.FileUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ImporterServiceImpl implements ImporterService {
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ProgressRepository progressRepository;

	@Autowired
	private WordRepository wordRepository;

	@Autowired
	private YandexDownloader yandexDownloader;

	private ProgressParserFactory progressParserFactory;

	@PostConstruct
	public void init() {
		progressParserFactory = new ProgressParserFactory();
	}

	@Override
	public String importAllStudentsHomework() {
		boolean errors = false;
		for (Student student : studentRepository.findAll()) {
			try {
				importHomework(student);
			} catch (IOException | ServerException | NoSuchAlgorithmException e) {
				errors = true;
				e.printStackTrace();
			}
		}

		return errors ? "ERROR" : "OK";
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

	@Override
	public String importPronunciationHomework(String studentId, File file) {
		Student student = studentRepository.findOne(studentId);

		try {
			if (file == null) {
				file = yandexDownloader.downloadFile(student.getVocabulary().getImportParams());
			}

			importPronunciationHomework(student, file);
		} catch (IOException | ServerException | NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "ERROR";
		}

		return "OK";
	}

	@Override
	public String importVocabularyHomework(String studentId, File file) {
		Student student = studentRepository.findOne(studentId);

		try {
			if (file == null) {
				file = yandexDownloader.downloadFile(student.getVocabulary().getImportParams());
			}

			importVocabularyHomework(student, file);
		} catch (IOException | ServerException | NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "ERROR";
		}

		return "OK";
	}

	@Override
	public String importStudentProgress(String studentId) {
		Student student = studentRepository.findOne(studentId);
		Progress progress = progressRepository.findOne(student.getProgressName());
		try {
			File file = yandexDownloader.downloadFile(progress.getImportParams());

			ProgressDataWrapper[] progressDataWrappers = progressParserFactory.getParser(progress).doParse(file, progress.getImportParams().getProgressDataWrappers());

			student.setProgress(Arrays.asList(progressDataWrappers)
					.stream()
					.filter((v) -> v != null)
					.filter((v) -> v.getStudent().equalsIgnoreCase(student.getId()))
					.collect(Collectors.toList()));

			studentRepository.save(student);
		} catch (IOException | ServerException e) {
			e.printStackTrace();
			return "ERROR";
		}
		return "OK";
	}

	@Override
	public String importAllStudentProgress() {
		List<Student> students = studentRepository.findAll();
		boolean errors = false;
		for (Student s : students) {
			String result = importStudentProgress(s.getId());
			if ("ERROR".equalsIgnoreCase(result)) errors = true;
		}

		return errors ? "ERROR" : "OK";
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

		File vocabularyFile = yandexDownloader.downloadFile(student.getVocabulary().getImportParams());
		importVocabularyHomework(student, vocabularyFile);

		File pronunciationFile = yandexDownloader.downloadFile(student.getPronunciation().getImportParams());
		importPronunciationHomework(student, pronunciationFile);
	}

	private void importPronunciationHomework(Student student, File file) throws NoSuchAlgorithmException, IOException {
		String hash = FileUtils.calculateFileHash(file);

		if (student.getPronunciation().getHomeworks()
				.stream()
				.anyMatch(homework -> hash.equalsIgnoreCase(homework.getFileHash()))) {
			return;
		}

		Map<String, Word> saveWords = new HashMap<>();
		Map<String, String> pronunciationMap = HomeworkParser.doParse(file);
		List<HomeworkWord> homeworkWords = new ArrayList<>();

		pronunciationMap.forEach((title, pronunciation) -> {
			Word word = getWord(saveWords, title, student.getId());

			if (word.getPronunciation() == null)
				word.setPronunciation(new HashMap<>());

			word.getPronunciation().put(student.getDialect(), pronunciation);
			saveWords.put(title, word);
			homeworkWords.add(new HomeworkWord(title, pronunciation, title));
		});

		student.getPronunciation().getHomeworks().add(getHomework(homeworkWords, hash));
		student.getWords().addAll(pronunciationMap.keySet());
		studentRepository.save(student);
		wordRepository.save(saveWords.values());
	}

	private void importVocabularyHomework(Student student, File file) throws IOException, ServerException, NoSuchAlgorithmException {
		Map<String, Word> saveWords = new HashMap<>();

		String hash = FileUtils.calculateFileHash(file);
		if (student.getVocabulary().getHomeworks()
				.stream()
				.anyMatch(homework -> hash.equalsIgnoreCase(homework.getFileHash()))) {
			return;
		}

		Map<String, String> vocabularyMap = HomeworkParser.doParse(file);
		List<HomeworkWord> homeworkWords = new ArrayList<>();

		vocabularyMap.forEach((translation, title) -> {
			Word word = getWord(saveWords, title, student.getId());

			if (word.getTranslation() == null)
				word.setTranslation(new HashSet<>());

			word.getTranslation().add(translation);
			saveWords.put(title, word);
			homeworkWords.add(new HomeworkWord(title, translation, title));
		});

		student.getVocabulary().getHomeworks().add(getHomework(homeworkWords, hash));
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
