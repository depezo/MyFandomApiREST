package br.com.martines_dev.MyFandon.exceptions;

public class IdNaoConfereException extends RuntimeException {

	
	private static final long serialVersionUID = 77958546L;

	public IdNaoConfereException() {
		super("Erro id não confere");
	}
}
