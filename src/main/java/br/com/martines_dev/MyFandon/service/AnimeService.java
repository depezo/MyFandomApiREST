package br.com.martines_dev.MyFandon.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.martines_dev.MyFandon.domain.Anime;
import br.com.martines_dev.MyFandon.domain.Categoria;
import br.com.martines_dev.MyFandon.domain.Comentario;
import br.com.martines_dev.MyFandon.domain.Personagem;
import br.com.martines_dev.MyFandon.domain.Usuario;
import br.com.martines_dev.MyFandon.exceptions.RecursoNaoEncontrado;
import br.com.martines_dev.MyFandon.persistence.AnimePersistence;
import br.com.martines_dev.MyFandon.persistence.ComentarioPersistence;
import br.com.martines_dev.MyFandon.persistence.PersonagemPersistence;
import br.com.martines_dev.MyFandon.persistence.UsuarioPersistence;
import br.com.martines_dev.MyFandon.service.interfaces.AnimeServiceInterface;

@Service
public class AnimeService implements AnimeServiceInterface{

	private static final int QTD_ANIMES_POR_PAGINA = 8;

	@Autowired
	AnimePersistence animeDAO;
	@Autowired
	UsuarioPersistence usuarioDAO;
	@Autowired
	ComentarioPersistence comentarioDAO;
	@Autowired
	PersonagemPersistence personagemDAO;
	@Override
	@Transactional
	public Anime inserir(Anime anime) {
	
		/*preciso verificar se esse if é necessario ou redundante pois
		 * já coloquei uma validação no model Anime para o campo admin*/
		if( anime.getAdmin().isEmpty() ) {
			throw new RecursoNaoEncontrado("Erro não é possivel inserir um anime sem um usuário");
		}
		
		return animeDAO.save(anime);
	}

	
	@Transactional
	@Override
	public Anime atualizar(Anime anime , Long id) {
		
		Optional<Anime> founded = animeDAO.findById( id );
		
		if( !founded.isPresent() ) {
			throw new RecursoNaoEncontrado("Erro não é possivel inserir um anime que não foi encontrado");
		}
		
		return animeDAO.save( anime );
		
	}
	

	@Override
	@Transactional
	public void deletar(Long id) {
		
		animeDAO.deleteById( id );
	}

	@Override
	public List<Anime> listar() {
		
		return animeDAO.findAll();
	}

	@Override
	public Page<Anime> listar(int page ) {
		
		
		Pageable pageable = PageRequest.of( page , QTD_ANIMES_POR_PAGINA);
		return animeDAO.findAll( pageable );
	}
	
	@Override
	@Transactional
	@Deprecated
	public Personagem addPersonagem(Anime anime , Personagem personagem) {

		Anime encontrado = this.pegarUm ( anime.getId() );
		
		Personagem personagemAdicionado = personagemDAO.save( personagem );
		encontrado.getPersonagems().add( personagemAdicionado );
		
		return personagem;
	}

	@Override
	@Transactional
	public void addPersonagem(Long animeId, Personagem personagem) {
		
		Optional<Anime> animeFounded = animeDAO.findById( animeId );
		
		if( animeFounded.isPresent() ) 
		{
			Personagem personagemAdicionado = personagemDAO.save( personagem );
			animeFounded.get().getPersonagems().add( personagemAdicionado );
		}else {
			throw new RecursoNaoEncontrado("Anime não encontrado");
		}
		
	}
	
	@Override
	@Transactional
	public Anime pegarUm(Long id) {
		
		return animeDAO
		  .findById(id)
		  .orElseThrow( () -> new RecursoNaoEncontrado("anime não encontrado") );
	}


	@Override
	@Transactional
	public Comentario addComentario(Long animeId, Comentario comentario) {
		
		Optional<Anime> animeFounded = animeDAO.findById( animeId );
		
		Optional<Usuario> usuarioFounded = usuarioDAO.findById(
			comentario.getUsuario().getId()
		);
		
		
		
		if( animeFounded.isPresent() && usuarioFounded.isPresent() ) 
		{
			comentario.setUsuario( usuarioFounded.get() );
			Comentario novoComentario = comentarioDAO.save( comentario );
			animeFounded.get().getComentarios().add( novoComentario );
			
			return novoComentario;
		}
		else {
			throw new RecursoNaoEncontrado("Erro usuario ou anime invalido");
		}
	}


	@Override
	public List<Categoria> getCategorias(Long animeId) {

		Anime animeFounded = animeDAO
			.findById( animeId )
			.orElseThrow( 
				() -> new RecursoNaoEncontrado("Erro anime não encontrado") 
			);
		
		return animeFounded.getCategorias();
	}


	





	
}
