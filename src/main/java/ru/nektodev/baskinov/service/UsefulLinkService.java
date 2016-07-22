package ru.nektodev.baskinov.service;

import org.springframework.stereotype.Service;
import ru.nektodev.baskinov.model.UsefulLink;

import java.util.List;

/**
 * @author Slava Tsykin ts.slawa@gmail.com
 */

@Service
public interface UsefulLinkService {
	List<UsefulLink> listUsefulLinks();

	void saveUsefulLinks(List<UsefulLink> usefulLinks);
}
