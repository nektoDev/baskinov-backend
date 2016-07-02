package ru.nektodev.baskinov.service.impl;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.JodaTimeConverters;
import org.springframework.stereotype.Service;
import ru.nektodev.baskinov.model.Homework;
import ru.nektodev.baskinov.model.Student;
import ru.nektodev.baskinov.model.Word;
import ru.nektodev.baskinov.repository.StudentRepository;
import ru.nektodev.baskinov.repository.WordRepository;
import ru.nektodev.baskinov.service.WordService;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WordServiceImpl implements WordService {

	@Autowired
	private WordRepository wordRepository;

	@Autowired
	private StudentRepository studentRepository;
	private JodaTimeConverters.DateToLocalDateConverter toLocaldateConverter;

	public WordServiceImpl() {
		toLocaldateConverter = JodaTimeConverters.DateToLocalDateConverter.INSTANCE;
	}

	public List<Word> getVocabulary(String student) {

		return wordRepository.findAll();
	}

	public List<Word> getPronunciation(String student) {

		return wordRepository.findAll();
	}

	@Override
	public Homework getPronunciationHomework(String studentId, Date date) {
		Student student = studentRepository.findOne(studentId);

		Optional<Homework> homework;
		List<Homework> homeworks = student.getPronunciation().getHomeworks();
		homework = getHomeworks(date, homeworks);

		return homework.orElse(null);
	}

	@Override
	public Homework getVocabularyHomework(String studentId, Date date) {
		Student student = studentRepository.findOne(studentId);

		Optional<Homework> homework;
		List<Homework> homeworks = student.getVocabulary().getHomeworks();
		homework = getHomeworks(date, homeworks);

		return homework.orElse(null);
	}

	private Optional<Homework> getHomeworks(Date date, List<Homework> homeworks) {
		Optional<Homework> homework;
		if (date == null) {
			homework = homeworks.stream().sorted(Comparator.comparing(Homework::getDate,
					Date::compareTo)).findFirst();
		} else {
			LocalDate homeworkDate = toLocaldateConverter.convert(date);
			homework = homeworks
					.stream()
					.filter((h) -> toLocaldateConverter.convert(h.getDate()).equals(homeworkDate)).findAny();
		}
		return homework;
	}
}
