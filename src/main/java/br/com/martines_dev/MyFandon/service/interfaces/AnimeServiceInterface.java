package br.com.martines_dev.MyFandon.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.martines_dev.MyFandon.domain.Anime;
import br.com.martines_dev.MyFandon.domain.Categoria;
import br.com.martines_dev.MyFandon.domain.Comentario;
import br.com.martines_dev.MyFandon.domain.Personagem;


@Service
public interface AnimeServiceInterface extends CrudServiceInterface<Anime,Long> {
	
	
	void addPersonagem( Long animeId, Personagem personagem );
	 
	Comentario addComentario( Long animeId, Comentario comentario );

	List<Categoria> getCategorias(  Long animeId );
	
}
