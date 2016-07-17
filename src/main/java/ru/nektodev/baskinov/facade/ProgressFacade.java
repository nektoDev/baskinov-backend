package ru.nektodev.baskinov.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.nektodev.baskinov.model.Progress;
import ru.nektodev.baskinov.service.ProgressService;

import java.util.List;

/**
 * @author Slava Tsykin ts.slawa@gmail.com
 */

@RestController
@RequestMapping("/progress")
public class ProgressFacade {

	@Autowired
	private ProgressService progressService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Progress> getProgress() {
		return progressService.getAllProgresses();
	}

	@RequestMapping(value = "/{progressId}", method = RequestMethod.GET)
	public Progress getProgress(@PathVariable String progressId) {
		return progressService.getProgress(progressId);
	}

	@RequestMapping(value = "/generate", method = RequestMethod.GET)
	public List<Progress> generate() {
		return progressService.generateProgress();
	}
}
