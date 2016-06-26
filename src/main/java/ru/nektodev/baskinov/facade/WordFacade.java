package ru.nektodev.baskinov.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.nektodev.baskinov.model.Word;
import ru.nektodev.baskinov.service.WordService;

import java.util.List;

/**
 * REST facade for access to word repository
 *
 * @author Tsykin V.A ts.slawa@gmail.com
 */
@RestController
@RequestMapping("/word")
public class WordFacade {

	@Autowired
	private WordService wordService;

	@RequestMapping(value = "/vocabulary/{student}", method = RequestMethod.GET)
	public List<Word> getVocabulary(@PathVariable String student){

		return wordService.getVocabulary(student);
	}

	@RequestMapping(value = "/pronunciation/{student}", method = RequestMethod.GET)
	public List<Word> getPronunciation(@PathVariable String student){

		return wordService.getPronunciation(student);
	}

}
