package ru.nektodev.baskinov.service;

import org.springframework.stereotype.Service;
import ru.nektodev.baskinov.model.ProgressDataWrapper;
import ru.nektodev.baskinov.model.Student;

import java.util.List;

@Service
public interface StudentService {

	List<Student> getStudent(String studentId);

	List<Student> generate();

	void clear();

	List<ProgressDataWrapper> getProgress(String studentId);
}
