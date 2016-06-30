package ru.nektodev.baskinov.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.nektodev.baskinov.service.ImporterService;

@Component
public class Scheduler {

	@Autowired
	private ImporterService importerService;

	@Scheduled(cron="0 0 */1 * * *")
	public void importAllStudents() {
		importerService.importAll();
	}
}
