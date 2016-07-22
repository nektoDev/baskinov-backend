package ru.nektodev.baskinov.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.nektodev.baskinov.model.UsefulLink;

/**
 * @author Slava Tsykin ts.slawa@gmail.com
 */
@Repository
public interface UsefulLinkRepository extends MongoRepository<UsefulLink, String> {
}
