package awto.registrologs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import awto.registrologs.entity.AwlogLogger;

/**
 * 
 * Repositorio CRUD para la entidad AwlogLogger.
 * 
 * @author Rodrigo Sepúlveda.
 *
 */
@Repository
public interface AwlogLoggerRepository extends JpaRepository<AwlogLogger, Integer> {

}
