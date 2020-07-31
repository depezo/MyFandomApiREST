package br.com.martines_dev.MyFandon.service.interfaces;

import org.springframework.stereotype.Service;


import br.com.martines_dev.MyFandon.domain.Anime;
import br.com.martines_dev.MyFandon.domain.Comentario;
import br.com.martines_dev.MyFandon.domain.Personagem;


@Service
public interface AnimeServiceInterface extends CrudServiceInterface<Anime,Long> {
	
	
	Personagem addPersonagem( Anime anime, Personagem personagem );
	 
	Comentario addComentario( Long animeId, Comentario comentario );
	void addPersonagem( Long animeId, Personagem personagem );
	
}
