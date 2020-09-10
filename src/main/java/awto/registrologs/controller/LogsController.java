package awto.registrologs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import awto.registrologs.model.Log;
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

	@PostMapping(value = "logs")
	public ResponseEntity<String> logs(@RequestBody Log log) {

		logsService.saveLog(log);

		return new ResponseEntity<>(Constantes.REST_RESPONSE_OK, HttpStatus.OK);

	}

}
