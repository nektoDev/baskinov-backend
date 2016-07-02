package ru.nektodev.baskinov.service;

public interface ImporterService {
	String importAllStudentsHomework();

	String importStudentHomework(String studentId);
}
