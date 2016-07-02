package ru.nektodev.baskinov.service;

public interface ImporterService {
	public String importAllStudentsHomework();

	String importStudentHomework(String studentId);
}
