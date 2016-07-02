package ru.nektodev.baskinov.facade;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.nektodev.baskinov.service.ImporterService;

@RestController
@RequestMapping("/import")
public class ImporterFacade {

	@Autowired
	private ImporterService importer;

	@RequestMapping(value = "/start/{student}", method = RequestMethod.GET)
	public String getVocabulary(@PathVariable String student){
		return Strings.isNullOrEmpty(student) ?
				importer.importAllStudentsHomework() :
				importer.importStudentHomework(student);
	}

}
