package ru.nektodev.baskinov.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.nektodev.baskinov.model.Homework;
import ru.nektodev.baskinov.service.WordService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

	@RequestMapping(value = "/homework/vocabulary/{studentId}", method = RequestMethod.GET)
	public Homework getHomeworkVocabulary(@PathVariable String studentId,
	                                      @RequestParam(value = "date", defaultValue = "") String dateString) {

		Date date;
		try {
			date = DATE_FORMAT.parse(dateString);
		} catch (ParseException e) {
			date = null;
		}

		return wordService.getVocabularyHomework(studentId, date);
	}

	@RequestMapping(value = "/homework/pronunciation/{studentId}", method = RequestMethod.GET)
	public Homework getHomeworkPronunciation(@PathVariable String studentId,
	                                         @RequestParam(value = "date", defaultValue = "") String dateString) {
		Date date;
		try {
			date = DATE_FORMAT.parse(dateString);
		} catch (ParseException e) {
			date = null;
		}

		return wordService.getPronunciationHomework(studentId, date);
	}

}
