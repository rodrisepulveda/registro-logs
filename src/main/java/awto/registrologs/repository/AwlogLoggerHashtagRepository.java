package awto.registrologs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import awto.registrologs.entity.AwlogLoggerHashtag;

/**
 * 
 * Repositorio CRUD para la entidad AwlogLoggerHashtag.
 * 
 * @author Rodrigo Sepúlveda.
 *
 */
@Repository
public interface AwlogLoggerHashtagRepository extends JpaRepository<AwlogLoggerHashtag, Integer> {

}
