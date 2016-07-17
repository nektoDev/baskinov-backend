package ru.nektodev.baskinov.service;

import java.io.File;

public interface ImporterService {
	String importAllStudentsHomework();

	String importStudentHomework(String studentId);

	String importStudentProgress(String studentId);

	String importPronunciationHomework(String studentId, File file);

	String importVocabularyHomework(String studentId, File file);

}
