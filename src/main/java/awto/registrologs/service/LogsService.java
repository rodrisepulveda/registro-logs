package awto.registrologs.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import awto.registrologs.entity.AwlogHashtag;
import awto.registrologs.entity.AwlogLogger;
import awto.registrologs.entity.AwlogLoggerHashtag;
import awto.registrologs.exeption.BadRequestRuntime;
import awto.registrologs.model.LogRequest;
import awto.registrologs.model.LogResponse;
import awto.registrologs.repository.AwlogHashtagRepository;
import awto.registrologs.repository.AwlogLoggerHashtagRepository;
import awto.registrologs.repository.AwlogLoggerRepository;

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

			AwlogHashtag awlogHashtag = awlogHashtagRepository.findByDescription(hashTag);

			if (awlogHashtag != null) {

				awlogHashtag.setDescription(hashTag);

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

			LogResponse logResponse = new LogResponse();

			logResponse.setDetails(awlogLogger.getDetails());

			logResponse.setHost(awlogLogger.getHost());

			logResponse.setId(awlogLogger.getId());

			logResponse.setOrigin(awlogLogger.getOrigin());

			logResponse.setStacktrace(awlogLogger.getStacktrace());

			List<String> listHashTags = new LinkedList<>();

			for (AwlogLoggerHashtag awlogLoggerHashtag : awlogLogger.getAwlogLoggerHashtagList()) {

				listHashTags.add(awlogLoggerHashtag.getAwlogHashtag().getDescription());

			}

			logResponse.setHashtags(listHashTags);

			listLogResponse.add(logResponse);

		}

		return listLogResponse;

	}

}
