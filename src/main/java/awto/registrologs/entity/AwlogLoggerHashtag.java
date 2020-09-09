/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 * Entidad que referencia a la tabla awlog_hashtag.
 * 
 * @author Rodriigo Sepúlveda.
 */
@Entity
@Table(name = "awlog_logger_hashtag")
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

	public AwlogLoggerHashtag() {
	}

	public AwlogLoggerHashtag(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AwlogLogger getAwlogLogger() {
		return awlogLogger;
	}

	public void setAwlogLogger(AwlogLogger awlogLogger) {
		this.awlogLogger = awlogLogger;
	}

	public AwlogHashtag getAwlogHashtag() {
		return awlogHashtag;
	}

	public void setAwlogHashtag(AwlogHashtag awlogHashtag) {
		this.awlogHashtag = awlogHashtag;
	}

}
