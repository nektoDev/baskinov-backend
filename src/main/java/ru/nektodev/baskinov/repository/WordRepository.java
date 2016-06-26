package ru.nektodev.baskinov.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.nektodev.baskinov.model.Word;

@Repository
public interface WordRepository extends MongoRepository<Word, String> {
}
