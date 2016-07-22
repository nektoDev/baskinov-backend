package ru.nektodev.baskinov.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.nektodev.baskinov.model.UsefulLink;
import ru.nektodev.baskinov.service.UsefulLinkService;

import java.util.List;

/**
 * @author Slava Tsykin ts.slawa@gmail.com
 */

@RestController
@RequestMapping("/service")
public class ServiceFacade {

	@Autowired
	private UsefulLinkService usefulLinkService;

	@RequestMapping(value = "usefulLinks", method = RequestMethod.GET)
	public List<UsefulLink> getUsefulLinks() {
		return usefulLinkService.listUsefulLinks();
	}

	@RequestMapping(value = "usefulLinks", method = RequestMethod.POST)
	public void saveUsefulLinks(@RequestBody List<UsefulLink> usefulLinks) {
		usefulLinkService.saveUsefulLinks(usefulLinks);
	}
}
