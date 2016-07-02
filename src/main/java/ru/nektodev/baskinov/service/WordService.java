package ru.nektodev.baskinov.service;

import org.springframework.stereotype.Service;
import ru.nektodev.baskinov.model.Homework;
import ru.nektodev.baskinov.model.Word;

import java.util.Date;
import java.util.List;

@Service
public interface WordService {

	public List<Word> getVocabulary(String student);

	public List<Word> getPronunciation(String student);

	Homework getPronunciationHomework(String studentId, Date date);

	Homework getVocabularyHomework(String studentId, Date date);
}
