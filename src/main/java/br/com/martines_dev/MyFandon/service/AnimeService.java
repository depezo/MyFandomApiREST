package br.com.martines_dev.MyFandon.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.martines_dev.MyFandon.domain.Anime;
import br.com.martines_dev.MyFandon.domain.Comentario;
import br.com.martines_dev.MyFandon.domain.Personagem;
import br.com.martines_dev.MyFandon.domain.Usuario;
import br.com.martines_dev.MyFandon.persistence.AnimePersistence;
import br.com.martines_dev.MyFandon.persistence.ComentarioPersistence;
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
	
	@Override
	@Transactional
	public Anime inserir(Anime anime) {
	
		/*preciso verificar se esse if é necessario ou redundante pois
		 * já coloquei uma validação no model Anime para o campo admin*/
		if( anime.getAdmin().isEmpty() ) {
			throw new RuntimeException("Erro não é possivel inserir um anime sem um usuário");
		}
		
		return animeDAO.save(anime);
	}

	
	@Transactional
	@Override
	public Anime atualizar(Anime anime , Long id) {
		
		Optional<Anime> founded = animeDAO.findById( id );
		
		if( !founded.isPresent() ) {
			throw new RuntimeException("Erro não é possivel inserir um anime que não foi encontrado");
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
	public List<Anime> listar(int page ) {
		
		
		Pageable pageable = PageRequest.of( page , QTD_ANIMES_POR_PAGINA);
		return animeDAO.findAll( pageable ).toList();
	}
	
	@Override
	@Transactional
	public Personagem addPersonagem(Anime anime , Personagem personagem) {

		Anime encontrado = this.pegarUm ( anime.getId() );
		
		encontrado.getPersonagems().add( personagem );
		
		return personagem;
	}

	@Override
	@Transactional
	public Anime pegarUm(Long id) {
		 
		return animeDAO.getOne( id );
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
			throw new RuntimeException("Erro usuario ou anime invalido");
		}
	}


	@Override
	public void addPersonagem(Long animeId, Personagem personagem) {
		
		Optional<Anime> animeFounded = animeDAO.findById( animeId );
		
		if( animeFounded.isPresent() ) 
		{
			animeFounded.get().getPersonagems().add(personagem);
		}
		
	}





	
}
