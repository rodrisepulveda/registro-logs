package awto.registrologs.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Value;

/**
 * Entidad asociada a la tabla awlog_logger.
 * 
 * @author Rodrigo Sepúlveda
 */
@Entity
@Table(name = "awlog_hashtag")
@Value
@Builder
public class AwlogHashtag implements Serializable {

	/**
	 * Serie.
	 */
	private static final long serialVersionUID = 1418388192194914233L;

	/**
	 * Identificador del hashtag.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	/**
	 * Descripcion del hashtag
	 */
	@Size(max = 50)
	@Column(name = "description")
	private String description;

	/**
	 * Lista de registros de la tabla awlog_logger_hashtag asociados.
	 */
	@OneToMany(mappedBy = "awlogHashtag", fetch = FetchType.LAZY)
	private List<AwlogLoggerHashtag> awlogLoggerHashtagList;

}
