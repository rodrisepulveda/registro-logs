package awto.registrologs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import awto.registrologs.entity.AwlogHashtag;
import awto.registrologs.entity.AwlogLoggerHashtag;

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

	/**
	 * Query que actualiza la descripción de un hashtag por su identificador.
	 * 
	 * @param id
	 *            identificador del hashtag.
	 * @param description
	 *            descripción del hashtag.
	 */
	@Modifying
	@Query("UPDATE AwlogHashtag a SET a.description = :description WHERE a.id = :id")
	void updateDescription(@Param("id") Integer id, @Param("description") String description);

	/**
	 * Query que obtiene una lista de objetos AwlogLoggerHashtag filtrando por la
	 * descripción de uno de sus hashtags.
	 * 
	 * @param description
	 *            descripción de hashtag.
	 * @return lista de objetos AwlogLoggerHashtag.
	 */
	@Query("SELECT a.awlogLoggerHashtagList from AwlogHashtag a WHERE a.description = :description")
	List<AwlogLoggerHashtag> findListAwlogLoggerHashtagByDescription(@Param("description") String description);

}
