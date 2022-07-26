package dh.meli.qabugs.repository;

import dh.meli.qabugs.model.TestCase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestCaseRepository extends CrudRepository<TestCase, Long> {
    @Query(value = "SELECT * FROM test_case WHERE CONVERT(last_update, date) = ?1", nativeQuery = true)
    List<TestCase> getTestCaseByLastUpdate(String lastUpdate);
}
