package br.com.martines_dev.MyFandon.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerDeExceptions  {

	@ExceptionHandler(RecursoNaoEncontrado.class)
	@ResponseStatus( HttpStatus.NOT_FOUND )
	public String erro404( RecursoNaoEncontrado erro404 ) {
		return erro404.getMessage();
	}
	
	
	@ExceptionHandler(RecursoJaExiste.class)
	@ResponseStatus( HttpStatus.CONFLICT )
	public String erroItemJaExiste(RecursoJaExiste recursoExistente) {
		
		return recursoExistente.getMessage();
	}
	
	
	@ExceptionHandler(IdNaoConfereException.class)
	@ResponseStatus( HttpStatus.BAD_REQUEST )
	public ResponseEntity<String> 
		erroIdNaoCombina(IdNaoConfereException err) {
		
		return ResponseEntity
				.badRequest()
				.body( err.getMessage() );
	}
}
