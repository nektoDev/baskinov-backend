package ru.nektodev.baskinov.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.nektodev.baskinov.importer.WordImporter;

@RestController
@RequestMapping("/importer")
public class ImporterFacade {

	@Autowired
	private WordImporter importer;

	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public String getVocabulary(){
		return "OK";
	}

}
