package ru.nektodev.baskinov.importer;

import com.yandex.disk.rest.exceptions.ServerException;
import ru.nektodev.baskinov.model.Student;
import ru.nektodev.baskinov.model.Word;

import java.io.IOException;
import java.util.List;

public interface WordImporter {

	List<Word> doImport(Student student) throws IOException, ServerException;
}
