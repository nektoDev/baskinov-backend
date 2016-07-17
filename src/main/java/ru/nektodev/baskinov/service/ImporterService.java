package ru.nektodev.baskinov.service;

import com.yandex.disk.rest.exceptions.ServerException;
import ru.nektodev.baskinov.model.Student;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface ImporterService {
	String importAllStudentsHomework();

	String importStudentHomework(String studentId);

	void importPronunciationHomework(Student student, File file) throws IOException, ServerException, NoSuchAlgorithmException;

	void importVocabularyHomework(Student student, File file) throws IOException, ServerException, NoSuchAlgorithmException;
}
