package ru.nektodev.baskinov.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.nektodev.baskinov.model.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
}
