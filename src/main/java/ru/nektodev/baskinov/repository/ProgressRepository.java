package ru.nektodev.baskinov.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.nektodev.baskinov.model.Progress;

/**
 * @author Slava Tsykin ts.slawa@gmail.com
 */
public interface ProgressRepository extends MongoRepository<Progress, String> {
}
