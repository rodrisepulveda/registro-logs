package awto.registrologs.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Value;

/**
 *
 * @author rodrigo
 */
@Entity
@Table(name = "awlog_logger")
@Value
@Builder
public class AwlogLogger implements Serializable {

	/**
	 * Serie.
	 */
	private static final long serialVersionUID = -6387570389485907732L;

	/**
	 * Identificador del registro de log.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	/**
	 * Fecha de creación de del registro de log.
	 */
	@Column(name = "creation_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	/**
	 * Host en el que se originó el log.
	 */
	@Size(max = 100)
	@Column(name = "host")
	private String host;

	/**
	 * Origen del log.
	 */
	@Size(max = 100)
	@Column(name = "origin")
	private String origin;

	/**
	 * Detalles del log.
	 */
	@Lob
	@Size(max = 65535)
	@Column(name = "details")
	private String details;

	/**
	 * Pila de la traza del log.
	 */
	@Lob
	@Size(max = 65535)
	@Column(name = "stacktrace")
	private String stacktrace;

	/**
	 * Lista de registros de la tabla awlog_logger_hashtag asociados.
	 */
	@OneToMany(mappedBy = "awlogLogger", fetch = FetchType.LAZY)
	private List<AwlogLoggerHashtag> awlogLoggerHashtagList;

}
