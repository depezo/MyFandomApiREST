package br.com.martines_dev.MyFandon.service.interfaces;

import br.com.martines_dev.MyFandon.domain.Categoria;

public interface CategoriaServiceInterface 
	extends CrudServiceInterface<Categoria,Long>
{

	void inserirAnime(Long idCategoria, Long idanime );

}
