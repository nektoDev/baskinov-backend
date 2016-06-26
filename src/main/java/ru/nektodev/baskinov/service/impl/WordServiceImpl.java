package ru.nektodev.baskinov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nektodev.baskinov.model.Word;
import ru.nektodev.baskinov.repository.WordRepository;
import ru.nektodev.baskinov.service.WordService;

import java.util.List;

@Service
public class WordServiceImpl implements WordService {

	@Autowired
	private WordRepository wordRepository;

	public List<Word> getVocabulary(String student) {

		return wordRepository.findAll();
	}

	public List<Word> getPronunciation(String student) {

		return wordRepository.findAll();
	}
}
