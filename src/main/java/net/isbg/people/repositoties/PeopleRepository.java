package net.isbg.people.repositoties;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import net.isbg.people.entity.People;
import java.util.Optional;

@Repository
public interface PeopleRepository extends CrudRepository<People, Long> {
    
    Optional<People> findByNameIgnoreCase(String name);

    @Transactional
    void deleteByNameAllIgnoreCase(String name);

}