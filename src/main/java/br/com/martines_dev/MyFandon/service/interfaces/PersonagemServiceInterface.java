package br.com.martines_dev.MyFandon.service.interfaces;

import br.com.martines_dev.MyFandon.domain.Comentario;
import br.com.martines_dev.MyFandon.domain.Personagem;

public interface PersonagemServiceInterface 
	extends CrudServiceInterface<Personagem,Long>
{	
	Personagem inserir( Personagem personagem , Long id );

	void addComentario(Long id, Comentario comentario);
}
