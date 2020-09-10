package awto.registrologs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import awto.registrologs.model.Log;
import awto.registrologs.service.RegistroLogsService;
import awto.registrologs.service.util.Constantes;

/**
 * Rest controller para el manejo de logs.
 * 
 * @author Rodrigo Sepúlveda.
 *
 */
@RestController
public class RegistroLogsController {

	/**
	 * Objeto service para el crud de manejo de logs de la empresa.
	 */
	@Autowired
	private RegistroLogsService registroLogsService;

	@PostMapping(value = "logs")
	public ResponseEntity<String> logs(@RequestBody Log log) {

		registroLogsService.ingresarLog(log);

		return new ResponseEntity<>(Constantes.RESPUESTA_REST_OK, HttpStatus.OK);

	}

}
