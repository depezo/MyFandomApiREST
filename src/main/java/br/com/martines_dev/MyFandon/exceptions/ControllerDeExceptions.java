package br.com.martines_dev.MyFandon.exceptions;

import javax.persistence.EntityNotFoundException;

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
	
	@ExceptionHandler(ExceptionOAnimeTemOAdminInvalido.class)
	@ResponseStatus( HttpStatus.BAD_REQUEST )
	public String erroNaoExisteAdmin( ExceptionOAnimeTemOAdminInvalido errorAdmin  ) {
		return errorAdmin.getMessage();
	}
	
	
	@ExceptionHandler( EntityNotFoundException.class )
	@ResponseStatus( HttpStatus.NOT_FOUND)
	public String erroItemNaoExiste() {
		
		return "Algum item que você quer modificar não foi encontrado na base de dados, portanto é impossivel de modificar o item!";
	}
	
	@ExceptionHandler(ExceptionORecursoTemOAdminInvalido.class)
	@ResponseStatus( HttpStatus.BAD_REQUEST )
	public String erroNaoExisteAdminGeral( ExceptionORecursoTemOAdminInvalido errorAdmin  ) {
		return errorAdmin.getMessage();
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
