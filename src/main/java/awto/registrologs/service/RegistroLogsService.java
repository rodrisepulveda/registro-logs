package awto.registrologs.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import awto.registrologs.entity.AwlogLogger;
import awto.registrologs.model.Log;
import awto.registrologs.repository.AwlogLoggerRepository;

@Service
public class RegistroLogsService {

	@Autowired
	private AwlogLoggerRepository registroLogsRepository;

	public void ingresarLog(Log log) {

		AwlogLogger awlogLogger = new AwlogLogger();

		awlogLogger.setCreationDate(new Date());

		awlogLogger.setDetails(log.getDetails());

		awlogLogger.setHost(log.getHost());

		awlogLogger.setOrigin(log.getOrigin());

		awlogLogger.setStacktrace(log.getStacktrace());

		registroLogsRepository.save(awlogLogger);

	}

}
