package ru.nektodev.baskinov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nektodev.baskinov.model.ImportParamsProgress;
import ru.nektodev.baskinov.model.Progress;
import ru.nektodev.baskinov.model.ProgressDataWrapper;
import ru.nektodev.baskinov.model.ProgressType;
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
				new ProgressDataWrapper("Pronunciation", "dasha"),
				new ProgressDataWrapper("Pronunciation", "slava"),
				new ProgressDataWrapper("Vocabulary", "dasha"),
				new ProgressDataWrapper("Vocabulary", "slava"),
				new ProgressDataWrapper("Test", "dasha"),
				new ProgressDataWrapper("Test", "slava"),
		};
		importParams.setProgressDataWrappers(progressDataWrappers);
		progress.setType(ProgressType.PDF);
		progress.setImportParams(importParams);

		result.add(progress);

		Progress progress2 = new Progress();
		progress2.setName("Aydar_Yuliya");
		ImportParamsProgress importParams2 = new ImportParamsProgress();
		importParams2.setPath("/progress/progress.ods");
		importParams2.setPublicKey("DhLa7f6nRVrD8AZj9EGmFkyE8goTvQr0vPDb6WsdgtQ%3D");
		ProgressDataWrapper[] progressDataWrappers2 = {null,
				new ProgressDataWrapper("Pronunciation", "yuliya"),
				new ProgressDataWrapper("Pronunciation", "aydar"),
				new ProgressDataWrapper("Vocabulary", "yuliya"),
				new ProgressDataWrapper("Vocabulary", "aydar"),
				new ProgressDataWrapper("Test", "yuliya"),
				new ProgressDataWrapper("Test", "aydar"),
		};
		importParams2.setProgressDataWrappers(progressDataWrappers2);
		progress2.setType(ProgressType.ODS);
		progress2.setImportParams(importParams2);

		result.add(progress2);
		progressRepository.save(result);
		return result;
	}
}
