package ru.nektodev.baskinov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nektodev.baskinov.model.UsefulLink;
import ru.nektodev.baskinov.repository.UsefulLinkRepository;
import ru.nektodev.baskinov.service.UsefulLinkService;

import java.util.List;

/**
 * @author Slava Tsykin ts.slawa@gmail.com
 */
@Service
public class UsefulLinkServiceImpl implements UsefulLinkService{

	@Autowired
	private UsefulLinkRepository usefulLinkRepository;

	@Override
	public List<UsefulLink> listUsefulLinks() {
		return usefulLinkRepository.findAll();
	}

	@Override
	public void saveUsefulLinks(List<UsefulLink> usefulLinks) {
		usefulLinkRepository.save(usefulLinks);
	}
}
