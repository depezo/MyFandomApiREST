package br.com.martines_dev.MyFandon.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.martines_dev.MyFandon.domain.Anime;
import br.com.martines_dev.MyFandon.domain.Comentario;
import br.com.martines_dev.MyFandon.domain.Personagem;
import br.com.martines_dev.MyFandon.persistence.AnimePersistence;
import br.com.martines_dev.MyFandon.persistence.ComentarioPersistence;
import br.com.martines_dev.MyFandon.persistence.PersonagemPersistence;
import br.com.martines_dev.MyFandon.service.interfaces.PersonagemServiceInterface;

@Service
public class PersonagemService implements PersonagemServiceInterface {

	private static final int QTD_PERSONAGEM_PER_PAGE = 8;
	
	@Autowired
	PersonagemPersistence personagemDAO;
	@Autowired
	AnimePersistence animeDAO;
	@Autowired
	ComentarioPersistence comentarioDAO;
	
	
	@Override
	public Personagem inserir(@NotNull Personagem personagem) {
		return personagemDAO.save( personagem );
	}
	
	@Override
	public Personagem inserir(Personagem personagem, Long animeId) {
		
		Personagem novoPersonagem = inserir(personagem);
		Optional<Anime> anime = animeDAO.findById( animeId );
			
		if( anime.isPresent() ) {
			
			anime.get().getPersonagems().add(personagem);
			animeDAO.save( anime.get() );			
		}else {
			throw new RuntimeException("Não é possivel adicionar um personagem ao anime que não existe");
		}
		
		return novoPersonagem;
	}
	
	@Override
	public Personagem atualizar(Personagem personagem, Long id) {
		
		Optional<Personagem> founded = personagemDAO.findById( id );
		
		if( !founded.isPresent() ) {
			throw new RuntimeException("Erro não é possivel inserir um anime que não foi encontrado");
		}
		
		return personagemDAO.save( personagem );
	}

	@Override
	public Personagem pegarUm(Long id) {
		
		
		return personagemDAO.findById( id )
				.orElseThrow( () -> {
					throw new RuntimeException("recurso não encontrado");	
				} );
	}

	@Override
	public void deletar(Long id) {

		personagemDAO.deleteById(id);
	}

	@Override
	public List<Personagem> listar() {

		return personagemDAO.findAll();
	}

	@Override
	public Page<Personagem> listar(int page)
	{
		
		Pageable pageable = PageRequest.of( page , QTD_PERSONAGEM_PER_PAGE );
		return personagemDAO.findAll( pageable );
	}

	@Override
 
	public void addComentario(Long personagemId, Comentario comentario) {
		
		Optional<Personagem> personagem = personagemDAO.findById( personagemId );
		
		if( personagem.isPresent() ) {
			
			Comentario novoComentario = comentarioDAO.save( comentario );
			personagem.get().getComentarios().add( novoComentario  );
			
			personagemDAO.save( personagem.get() );
		}
		else {
			throw new RuntimeException("Não é possivel inserir um comentario para um personagem que não existe");
		}
		
	}
	

}
