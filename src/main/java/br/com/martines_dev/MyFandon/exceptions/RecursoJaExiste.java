package br.com.martines_dev.MyFandon.exceptions;

public class RecursoJaExiste extends RuntimeException{

	private static final long serialVersionUID = 15449934177L;

	public RecursoJaExiste( String mensagem ) {
		super( mensagem );
	}
}
