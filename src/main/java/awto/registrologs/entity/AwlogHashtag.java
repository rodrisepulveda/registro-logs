/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 * Entidad asociada a la tabla awlog_logger.
 * 
 * @author Rodrigo Sepúlveda
 */
@Entity
@Table(name = "awlog_hashtag")
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

	public AwlogHashtag() {
	}

	public AwlogHashtag(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<AwlogLoggerHashtag> getAwlogLoggerHashtagList() {
		return awlogLoggerHashtagList;
	}

	public void setAwlogLoggerHashtagList(List<AwlogLoggerHashtag> awlogLoggerHashtagList) {
		this.awlogLoggerHashtagList = awlogLoggerHashtagList;
	}

}
