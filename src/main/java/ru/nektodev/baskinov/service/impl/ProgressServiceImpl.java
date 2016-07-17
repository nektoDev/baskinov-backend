package ru.nektodev.baskinov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nektodev.baskinov.model.ImportParamsProgress;
import ru.nektodev.baskinov.model.Progress;
import ru.nektodev.baskinov.model.ProgressDataWrapper;
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
		ImportParamsProgress importParams = new ImportParamsProgress();
		importParams.setPath("/progress/progress.pdf");
		importParams.setPublicKey("jDY08AP3zoSUx80EWqOBhduCV9KPdOMM41xsUOmKI7o%3D");
		ProgressDataWrapper[] progressDataWrappers = {null,
				new ProgressDataWrapper("Dasha Pronunciation", "dasha"),
				new ProgressDataWrapper("Slava Pronunciation", "slava"),
				new ProgressDataWrapper("Dasha Vocabulary", "dasha"),
				new ProgressDataWrapper("Slava Vocabulary", "slava"),
				new ProgressDataWrapper("Dasha Test", "dasha"),
				new ProgressDataWrapper("Slava Test", "slava"),
		};
		importParams.setProgressDataWrappers(progressDataWrappers);

		progress.setImportParams(importParams);

		result.add(progress);

		progressRepository.save(result);
		return result;
	}
}
