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
import awto.registrologs.model.Log;
import awto.registrologs.repository.AwlogHashtagRepository;
import awto.registrologs.repository.AwlogLoggerHashtagRepository;
import awto.registrologs.repository.AwlogLoggerRepository;

@Service
public class RegistroLogsService {

	@Autowired
	private AwlogLoggerRepository registroLogsRepository;

	@Autowired
	private AwlogHashtagRepository awlogHashtagRepository;

	@Autowired
	private AwlogLoggerHashtagRepository awlogLoggerHashtagRepository;

	public void ingresarLog(Log log) {

		if (log.getHashtags() == null || log.getHashtags().isEmpty()) {

			throw new BadRequestRuntime("Se debe enviar al menos 1 hashtag.");

		}

		AwlogLogger awlogLogger = new AwlogLogger();

		awlogLogger.setCreationDate(new Date());

		awlogLogger.setDetails(log.getDetails());

		awlogLogger.setHost(log.getHost());

		awlogLogger.setOrigin(log.getOrigin());

		awlogLogger.setStacktrace(log.getStacktrace());

		registroLogsRepository.save(awlogLogger);

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

}
