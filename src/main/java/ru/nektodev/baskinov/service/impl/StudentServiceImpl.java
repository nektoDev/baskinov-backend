package ru.nektodev.baskinov.service.impl;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nektodev.baskinov.model.ImportParams;
import ru.nektodev.baskinov.model.ProgressDataWrapper;
import ru.nektodev.baskinov.model.Student;
import ru.nektodev.baskinov.model.Task;
import ru.nektodev.baskinov.repository.StudentRepository;
import ru.nektodev.baskinov.service.StudentService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> getStudent(String studentId) {
		if (Strings.isNullOrEmpty(studentId)) {
			return studentRepository.findAll();
		} else {
			return Collections.singletonList(studentRepository.findOne(studentId));
		}
	}

	@Override
	public List<Student> generate() {
		Student aydar = generateStudent("aydar", "Aydar");
		Student yuliya = generateStudent("yuliya", "Yuliya");

		Student slava = generateStudent("slava", "Slava");
		Student daria = generateStudent("dasha", "Dasha");
		slava.getPronunciation().getImportParams().setPublicKey("jDY08AP3zoSUx80EWqOBhduCV9KPdOMM41xsUOmKI7o%3D");
		slava.getVocabulary().getImportParams().setPublicKey("jDY08AP3zoSUx80EWqOBhduCV9KPdOMM41xsUOmKI7o%3D");
		daria.getPronunciation().getImportParams().setPublicKey("jDY08AP3zoSUx80EWqOBhduCV9KPdOMM41xsUOmKI7o%3D");
		daria.getVocabulary().getImportParams().setPublicKey("jDY08AP3zoSUx80EWqOBhduCV9KPdOMM41xsUOmKI7o%3D");
		slava.getVocabulary().getImportParams().setPath("/homework/vocabulary.html");
		daria.getVocabulary().getImportParams().setPath("/homework/vocabulary.html");
		slava.setDialect("UK");
		daria.setDialect("UK");

		slava.setProgressName("Slava_Dasha");
		daria.setProgressName("Slava_Dasha");

		List<Student> students = new ArrayList<>();
		students.add(aydar);
		students.add(slava);
		students.add(daria);
		students.add(yuliya);

		studentRepository.save(students);
		return students;
	}

	@Override
	public void clear() {
		studentRepository.deleteAll();
	}

	@Override
	public List<ProgressDataWrapper> getProgress(String studentId) {
		return studentRepository.findOne(studentId).getProgress();
	}

	@Override
	public void saveStudents(List<Student> students) {
		studentRepository.save(students);
	}

	private Student generateStudent(String id, String name) {
		Student student = new Student();
		student.setId(id);
		student.setName(name);

		Task vocabulary= new Task();
		vocabulary.setId("1");
		ImportParams importData = new ImportParams();

		importData.setPath("/homework/vocabulary/ru-en.html");
		importData.setPublicKey("DhLa7f6nRVrD8AZj9EGmFkyE8goTvQr0vPDb6WsdgtQ%3D");
		vocabulary.setImportParams(importData);
		student.setVocabulary(vocabulary);

		Task pronunciation = new Task();

		student.setDialect("US");
		pronunciation.setId("1");
		ImportParams importData2 = new ImportParams();

		importData2.setPath("/homework/pronunciation/" + name + "/practice-and-check.html");
		importData2.setPublicKey("DhLa7f6nRVrD8AZj9EGmFkyE8goTvQr0vPDb6WsdgtQ%3D");
		pronunciation.setImportParams(importData2);
		student.setPronunciation(pronunciation);
		student.setProgressName("Aydar_Yuliya");
		return student;
	}
}
