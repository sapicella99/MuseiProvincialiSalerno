package unipegaso.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErroreResponse> gestisciIllegalArgumentException(IllegalArgumentException ex) {

		ErroreResponse errore = new ErroreResponse(ex.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errore);

	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErroreResponse> gestisciRuntimeException(RuntimeException ex) {

		ErroreResponse errore = new ErroreResponse(ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errore);

	}

}