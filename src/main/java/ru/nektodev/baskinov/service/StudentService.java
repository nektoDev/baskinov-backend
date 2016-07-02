package ru.nektodev.baskinov.service;

import org.springframework.stereotype.Service;
import ru.nektodev.baskinov.model.Student;

import java.util.List;

@Service
public interface StudentService {

	List<Student> getStudent(String studentId);
}
