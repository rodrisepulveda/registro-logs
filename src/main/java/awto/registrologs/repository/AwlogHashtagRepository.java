package awto.registrologs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import awto.registrologs.entity.AwlogHashtag;

/**
 * 
 * Repositorio CRUD para la entidad AwlogLogger.
 * 
 * @author Rodrigo Sepúlveda.
 *
 */
@Repository
public interface AwlogHashtagRepository extends JpaRepository<AwlogHashtag, Integer> {

	/**
	 * Método que busca un objeto AwlogHashtag por su descripción.
	 * 
	 * @param description
	 *            descripción a buscar.
	 * @return objeto AwlogHashtag.
	 */
	AwlogHashtag findByDescription(String description);

	@Modifying
	@Query("UPDATE AwlogHashtag a SET a.description = :description WHERE a.id = :id")
	void updateDescription(@Param("id") Integer id, @Param("description") String description);

}
