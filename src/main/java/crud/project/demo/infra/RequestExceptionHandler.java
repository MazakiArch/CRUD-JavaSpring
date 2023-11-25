package crud.project.demo.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class RequestExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity threat404() {
		return ResponseEntity.badRequest().body("ID não encontorado");
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity threat400() {
		return ResponseEntity.badRequest().body("Deve-se inidicar se o produto ficara Ativo(true) ou Inativo(false). \nAcrescente 'active' : 'true/false.'");
	}

}
