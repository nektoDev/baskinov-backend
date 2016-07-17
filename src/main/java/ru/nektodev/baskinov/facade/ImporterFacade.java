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

	@RequestMapping(value = "/homework/start", method = RequestMethod.GET)
	public String importAllHomeworks(){
		return importer.importAllStudentsHomework();
	}

	@RequestMapping(value = "/homework/start/{studentId}", method = RequestMethod.GET)
	public String importHomework(@PathVariable String studentId){
		return Strings.isNullOrEmpty(studentId) ?
				importer.importAllStudentsHomework() :
				importer.importStudentHomework(studentId);
	}

	@RequestMapping(value = "/progress/start/{studentId}", method = RequestMethod.GET)
	public String getVocabulary(@PathVariable String studentId){
		return importer.importStudentProgress(studentId);
	}

}
