package awto.registrologs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import awto.registrologs.model.LogRequest;
import awto.registrologs.model.LogResponse;
import awto.registrologs.service.LogsService;
import awto.registrologs.service.util.Constantes;

/**
 * Rest controller para el manejo de logs.
 * 
 * @author Rodrigo Sepúlveda.
 *
 */
@RestController
public class LogsController {

	/**
	 * Objeto service para el crud de manejo de logs de la empresa.
	 */
	@Autowired
	private LogsService logsService;

	/**
	 * Método Http que inserta logs.
	 * 
	 * @param log
	 *            objeto del log a insertar.
	 * @return
	 */
	@PostMapping(value = "logs")
	public ResponseEntity<String> postLogs(@RequestBody LogRequest log) {

		logsService.saveLog(log);

		return new ResponseEntity<>(Constantes.REST_RESPONSE_OK, HttpStatus.OK);

	}

	/**
	 * Método Http que obtiene todos los logs.
	 * 
	 * @return
	 */
	@GetMapping(value = "logs")
	public ResponseEntity<List<LogResponse>> getLogs() {

		return new ResponseEntity<>(logsService.findLogs(), HttpStatus.OK);

	}

}
