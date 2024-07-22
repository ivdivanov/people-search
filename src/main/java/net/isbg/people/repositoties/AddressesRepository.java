package net.isbg.people.repositoties;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.isbg.people.entity.Addresses;

@Repository
public interface AddressesRepository extends CrudRepository<Addresses, Long> {

}
