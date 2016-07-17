package ru.nektodev.baskinov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nektodev.baskinov.model.ImportParams;
import ru.nektodev.baskinov.model.Progress;
import ru.nektodev.baskinov.repository.ProgressRepository;
import ru.nektodev.baskinov.service.ProgressService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Slava Tsykin ts.slawa@gmail.com
 */

@Service
public class ProgressServiceImpl implements ProgressService {
	@Autowired
	private ProgressRepository progressRepository;

	@Override
	public Progress getProgress(String progressId) {
		return progressRepository.findOne(progressId);
	}

	@Override
	public List<Progress> getAllProgresses() {
		return progressRepository.findAll();
	}

	@Override
	public List<Progress> generateProgress() {
		List<Progress> result = new ArrayList<>();

		Progress progress = new Progress();
		progress.setName("Slava_Dasha");
		ImportParams importParams = new ImportParams();
		importParams.setPath("/progress/progress.pdf");
		importParams.setPublicKey("jDY08AP3zoSUx80EWqOBhduCV9KPdOMM41xsUOmKI7o%3D");
		progress.setImportParams(importParams);

		result.add(progress);

		progressRepository.save(result);
		return result;
	}
}
