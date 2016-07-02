package ru.nektodev.baskinov.service.impl;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nektodev.baskinov.model.Student;
import ru.nektodev.baskinov.repository.StudentRepository;
import ru.nektodev.baskinov.service.StudentService;

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
			return Collections.singletonList(studentRepository.findOne("aydar"));
		}
	}
}
