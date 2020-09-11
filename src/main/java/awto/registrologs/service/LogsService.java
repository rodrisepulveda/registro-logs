package awto.registrologs.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import awto.registrologs.entity.AwlogHashtag;
import awto.registrologs.entity.AwlogLogger;
import awto.registrologs.entity.AwlogLoggerHashtag;
import awto.registrologs.exeption.BadRequestRuntime;
import awto.registrologs.exeption.NotFoundRuntime;
import awto.registrologs.model.HashTagRequest;
import awto.registrologs.model.LogRequest;
import awto.registrologs.model.LogResponse;
import awto.registrologs.repository.AwlogHashtagRepository;
import awto.registrologs.repository.AwlogLoggerHashtagRepository;
import awto.registrologs.repository.AwlogLoggerRepository;
import awto.registrologs.service.util.Util;

/**
 * Clase service para el crud de manejo de logs de la empresa.
 * 
 * @author Rodrigo Sepúlveda.
 *
 */
@Service
public class LogsService {

	/**
	 * Apuntador al repositorio CRUD para la entidad AwlogLogger.
	 */
	@Autowired
	private AwlogLoggerRepository awlogLoggerRepository;

	/**
	 * Apuntador al repositorio CRUD para la entidad AwlogHashtag.
	 */
	@Autowired
	private AwlogHashtagRepository awlogHashtagRepository;

	/**
	 * Apuntador al repositorio CRUD para la entidad AwlogLoggerHashtag.
	 */
	@Autowired
	private AwlogLoggerHashtagRepository awlogLoggerHashtagRepository;

	/**
	 * Ingresa un registro de log a la base de datos asociado a el o los hashtags
	 * correspondientes.
	 * 
	 * @param log
	 *            objeto de log a insertar.
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveLog(LogRequest log) {

		if (log.getHashtags() == null || log.getHashtags().isEmpty()) {

			throw new BadRequestRuntime("Se debe enviar al menos 1 hashtag.");

		}

		AwlogLogger awlogLogger = new AwlogLogger();

		awlogLogger.setCreationDate(new Date());

		awlogLogger.setDetails(log.getDetails());

		awlogLogger.setHost(log.getHost());

		awlogLogger.setOrigin(log.getOrigin());

		awlogLogger.setStacktrace(log.getStacktrace());

		awlogLoggerRepository.save(awlogLogger);

		List<AwlogHashtag> listaAwlogHashtag = new LinkedList<>();

		List<AwlogLoggerHashtag> listaAwlogLoggerHashtag = new LinkedList<>();

		for (String hashTag : log.getHashtags()) {

			AwlogHashtag awlogHashtag = awlogHashtagRepository.findByDescription(Util.obtenerHashTagSinGato(hashTag));

			if (awlogHashtag == null) {

				awlogHashtag = new AwlogHashtag();

				awlogHashtag.setDescription(Util.obtenerHashTagSinGato(hashTag));

				listaAwlogHashtag.add(awlogHashtag);

			}

			AwlogLoggerHashtag awlogLoggerHashtag = new AwlogLoggerHashtag();

			awlogLoggerHashtag.setAwlogHashtag(awlogHashtag);

			awlogLoggerHashtag.setAwlogLogger(awlogLogger);

			listaAwlogLoggerHashtag.add(awlogLoggerHashtag);

		}

		if (!listaAwlogHashtag.isEmpty()) {

			awlogHashtagRepository.saveAll(listaAwlogHashtag);

		}

		awlogLoggerHashtagRepository.saveAll(listaAwlogLoggerHashtag);

	}

	/**
	 * Lista todos los registros de log.
	 * 
	 * @return lista de registros de logs y cada uno con sus hashtags asociados.
	 */
	public List<LogResponse> findLogs() {

		List<LogResponse> listLogResponse = new LinkedList<>();

		for (AwlogLogger awlogLogger : awlogLoggerRepository.findAll()) {

			List<String> listHashTags = new LinkedList<>();

			for (AwlogLoggerHashtag awlogLoggerHashtag : awlogLogger.getAwlogLoggerHashtagList()) {

				listHashTags.add(
						Util.agregarGatoAHashtagSiNoLoTiene(awlogLoggerHashtag.getAwlogHashtag().getDescription()));

			}

			listLogResponse.add(LogResponse.builder().details(awlogLogger.getDetails()).host(awlogLogger.getHost())
					.id(awlogLogger.getId()).origin(awlogLogger.getOrigin()).hashtags(listHashTags).build());

		}

		return listLogResponse;

	}

	/**
	 * Lista los registros de log asociados a un hashtag.
	 * 
	 * @param hashtag
	 *            filtro por hashtag.
	 * @return lista de registros de logs asociados al hashtag y cada uno con sus
	 *         hashtags asociados.
	 */
	public List<LogResponse> findLogs(String description) {

		List<LogResponse> listLogResponse = new LinkedList<>();

		for (AwlogLoggerHashtag awlogLoggerHashtag : awlogHashtagRepository
				.findListAwlogLoggerHashtagByDescription(description)) {

			AwlogLogger awlogLogger = awlogLoggerHashtag.getAwlogLogger();

			List<String> listHashTags = new LinkedList<>();

			for (AwlogLoggerHashtag awlogLoggerHashtagLinked : awlogLogger.getAwlogLoggerHashtagList()) {

				listHashTags.add(Util
						.agregarGatoAHashtagSiNoLoTiene(awlogLoggerHashtagLinked.getAwlogHashtag().getDescription()));

			}

			listLogResponse.add(LogResponse.builder().details(awlogLogger.getDetails()).host(awlogLogger.getHost())
					.id(awlogLogger.getId()).origin(awlogLogger.getOrigin()).hashtags(listHashTags).build());

		}

		return listLogResponse;

	}

	/**
	 * Busca un log por si identificador.
	 * 
	 * @param id
	 *            identificador de los.
	 * @return objeto Log.
	 */
	public LogResponse findLog(Integer id) {

		java.util.Optional<AwlogLogger> optAwlogLogger = awlogLoggerRepository.findById(id);

		if (!optAwlogLogger.isPresent()) {

			throw new NotFoundRuntime("No existe un Log con el identificador " + id);

		}

		AwlogLogger awlogLogger = optAwlogLogger.get();

		List<String> listHashTags = new LinkedList<>();

		for (AwlogLoggerHashtag awlogLoggerHashtag : awlogLogger.getAwlogLoggerHashtagList()) {

			listHashTags
					.add(Util.agregarGatoAHashtagSiNoLoTiene(awlogLoggerHashtag.getAwlogHashtag().getDescription()));

		}

		return LogResponse.builder().details(awlogLogger.getDetails()).host(awlogLogger.getHost())
				.id(awlogLogger.getId()).origin(awlogLogger.getOrigin()).hashtags(listHashTags).build();
	}

	/**
	 * Actualiza el nombre de un hashtag por su identificador.
	 * 
	 * @param hashTagRequest
	 *            objeto hashtag a modificar.
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateHastag(HashTagRequest hashTagRequest) {

		if (Util.obtenerHashTagSinGato(hashTagRequest.getDescription()).trim().equals("")) {

			throw new BadRequestRuntime("Descripción de hashtag vacia.");

		}

		AwlogHashtag awlogHashtagExistente = awlogHashtagRepository
				.findByDescription(Util.obtenerHashTagSinGato(hashTagRequest.getDescription()));

		if (awlogHashtagExistente != null) {

			throw new BadRequestRuntime("Ya existe un hashtag con el nombre " + hashTagRequest.getDescription());

		}

		awlogHashtagRepository.updateDescription(hashTagRequest.getId(),
				Util.obtenerHashTagSinGato(hashTagRequest.getDescription()));

	}

}
