package br.com.martines_dev.MyFandon.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.martines_dev.MyFandon.domain.Comentario;
import br.com.martines_dev.MyFandon.domain.Personagem;

public interface PersonagemServiceInterface 
	extends CrudServiceInterface<Personagem,Long>
{	
	Personagem inserir( Personagem personagem , Long id );


	List<Comentario> getComentarios(Long id);

	void addComentario(Long id, Comentario comentario, String usuarioNome);
	
	Page<Personagem> buscar( Pageable pageable, String buscar);
}
