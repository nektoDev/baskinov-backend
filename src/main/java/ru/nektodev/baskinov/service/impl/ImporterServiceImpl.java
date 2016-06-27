package ru.nektodev.baskinov.service.impl;

import com.yandex.disk.rest.exceptions.ServerException;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nektodev.baskinov.importer.WordImporter;
import ru.nektodev.baskinov.model.Student;
import ru.nektodev.baskinov.model.Word;
import ru.nektodev.baskinov.repository.StudentRepository;
import ru.nektodev.baskinov.repository.WordRepository;
import ru.nektodev.baskinov.service.ImporterService;
import ru.nektodev.baskinov.util.NullAwareBeanUtilsBean;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class ImporterServiceImpl implements ImporterService {
	@Autowired
	private WordImporter wordImporter;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private WordRepository wordRepository;

	private BeanUtilsBean notNullBean;

	@PostConstruct
	private void init(){
		notNullBean = new NullAwareBeanUtilsBean();
	}

	@Override
	public String importAll() {
		for (Student student : studentRepository.findAll()) {
			try {
				List<Word> words = wordImporter.doImport(student);

				for (Word word : words) {
					Word foundedWord = wordRepository.findOne(word.getWord());
					if (foundedWord != null) {
						notNullBean.copyProperties(word, foundedWord);
					}
					wordRepository.save(word);
				}
			} catch (IOException | ServerException | InvocationTargetException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return "OK";
	}

/*	private List<Student> generateStudents() {
		ArrayList<Student> result = new ArrayList<>();

		Student yulia = new Student();
		yulia.setName("aydar");

		Task aydarVocabulary= new Task();
		aydarVocabulary.setId("1");
//		aydarVocabulary.setHomeworkDates(Collections.singletonList(new Date()));
		ImportData importData = new ImportData();

		importData.setPath("/homework/vocabulary/en-ru.html");
		importData.setPublicKey("DhLa7f6nRVrD8AZj9EGmFkyE8goTvQr0vPDb6WsdgtQ%3D");
		aydarVocabulary.setImportData(importData);
		yulia.setVocabulary(aydarVocabulary);

		Task aydarPronunciation = null;
		try {
			aydarPronunciation = (Task) aydarVocabulary.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		assert aydarPronunciation != null;
		aydarPronunciation.setId("1");
//		aydarPronunciation.setHomeworkDates(Collections.singletonList(new Date()));
		ImportData importData2 = new ImportData();

		importData2.setPath("/homework/pronunciation/Aydar/practice-and-check.html");
		importData2.setPublicKey("DhLa7f6nRVrD8AZj9EGmFkyE8goTvQr0vPDb6WsdgtQ%3D");
		aydarPronunciation.setImportData(importData2);
		yulia.setPronunciation(aydarPronunciation);
		result.add(yulia);

		return result;
	}*/
}
