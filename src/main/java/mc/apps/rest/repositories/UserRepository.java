package mc.apps.rest.repositories;

import mc.apps.rest.modele.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CrudRepository - CRUD methods
 * PagingAndSortingRepository - methods for pagination and sorting (extends CrudRepository)
 * JpaRepository extends PagingAndSortingRepository that extends CrudRepository.
 * JpaRepository provides CRUD and pagination operations, along with additional methods like flush(), saveAndFlush(), and deleteInBatch(), etc.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT f FROM User f WHERE LOWER(f.lastName) = LOWER(:name)")
    List<User> findByLastName(String name);
}
