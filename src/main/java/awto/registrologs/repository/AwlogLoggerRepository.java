package awto.registrologs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import awto.registrologs.entity.AwlogLogger;

@Repository
public interface AwlogLoggerRepository extends JpaRepository<AwlogLogger, Integer> {

}
