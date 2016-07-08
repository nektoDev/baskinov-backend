package ru.nektodev.baskinov.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.nektodev.baskinov.model.Student;
import ru.nektodev.baskinov.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentFacade {

	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Student> getStudent(@RequestParam(value = "full", defaultValue = "false") boolean full) {
		List<Student> students = studentService.getStudent(null);
		if (!full) students.forEach(this::reduceStudent);
		return students;
	}

	@RequestMapping(value = "/{studentId}", method = RequestMethod.GET)
	public Student getStudent(@PathVariable String studentId,
	                          @RequestParam(value = "full", defaultValue = "false") boolean full) {
		Student student = studentService.getStudent(studentId).get(0);
		if (!full) reduceStudent(student);
		return student;
	}

	@RequestMapping(value = "/generate", method = RequestMethod.GET)
	public List<Student> generate() {
		return studentService.generate();
	}

	@RequestMapping(value = "/clear", method = RequestMethod.GET)
	public String clear() {
		studentService.clear();
		return "OK";
	}

	private void reduceStudent(Student student) {
		if (student == null) return;
		student.setPronunciation(null);
		student.setVocabulary(null);
		student.setWords(null);
	}
}
