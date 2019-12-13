package hello.dataBase;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DollarRateCrudRepository extends CrudRepository<DollarRate, Long> {
    Optional<DollarRate> findByDate(String dateString);
}
