package awto.registrologs.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import awto.registrologs.exeption.BadRequestRuntime;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(BadRequestRuntime.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handle(BadRequestRuntime exception) {

		log.error(exception.getMessage(), exception);

		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);

	}

}
