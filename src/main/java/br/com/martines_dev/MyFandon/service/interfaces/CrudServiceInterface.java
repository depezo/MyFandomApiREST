package br.com.martines_dev.MyFandon.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;

/**
 * T -> tipo do parametro primario Long
 * C -> class exemplo: Usuario
**/

public interface CrudServiceInterface<C,T> {

	C inserir( C objeto );
	C atualizar( C objeto , T id);
	C pegarUm( T id );
	void deletar( T id );
	
	List<C> listar();
	Page<C> listar( int page );
}
