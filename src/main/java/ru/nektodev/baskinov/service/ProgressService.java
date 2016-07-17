package ru.nektodev.baskinov.service;

import org.springframework.stereotype.Service;
import ru.nektodev.baskinov.model.Progress;

import java.util.List;

/**
 * @author Slava Tsykin ts.slawa@gmail.com
 */

@Service
public interface ProgressService {
	Progress getProgress(String progressId);
	List<Progress> getAllProgresses();
	Progress generateProgress();
}
