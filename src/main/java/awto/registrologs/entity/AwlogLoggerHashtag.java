package awto.registrologs.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Value;

/**
 * Entidad que referencia a la tabla awlog_hashtag.
 * 
 * @author Rodriigo Sepúlveda.
 */
@Entity
@Table(name = "awlog_logger_hashtag")
@Value
@Builder
public class AwlogLoggerHashtag implements Serializable {

	/**
	 * Serie.
	 */
	private static final long serialVersionUID = -2531175422211289452L;

	/**
	 * Identificador de la tabla.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	/**
	 * Calve foranea que referencia a la tabla awlog_logger.
	 */
	@JoinColumn(name = "log_id", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private AwlogLogger awlogLogger;

	/**
	 * Calve foranea que referencia a la awlog_hashtag.
	 */
	@JoinColumn(name = "hastag_id", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private AwlogHashtag awlogHashtag;

}
