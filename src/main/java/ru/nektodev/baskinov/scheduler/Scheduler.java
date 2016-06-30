package ru.nektodev.baskinov.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.nektodev.baskinov.service.ImporterService;

@Component
public class Scheduler {

	@Autowired
	private ImporterService importerService;

	@Scheduled(cron="${scheduler.import.cron}")
	public void importAllStudents() {
		importerService.importAllStudents();
	}
}
