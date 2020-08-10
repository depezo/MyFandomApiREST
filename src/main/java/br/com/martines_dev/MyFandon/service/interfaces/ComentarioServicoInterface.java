package br.com.martines_dev.MyFandon.service.interfaces;

import br.com.martines_dev.MyFandon.domain.Comentario;

public interface ComentarioServicoInterface 
	extends CrudServiceInterface<Comentario,Long> {

	void deletar(Long idComentario, String nomeUsuario);

	Comentario atualizar(Comentario categoria, Long id, String name);

 
}
