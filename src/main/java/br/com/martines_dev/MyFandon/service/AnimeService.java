package br.com.martines_dev.MyFandon.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.martines_dev.MyFandon.domain.Anime;
import br.com.martines_dev.MyFandon.domain.Categoria;
import br.com.martines_dev.MyFandon.domain.Comentario;
import br.com.martines_dev.MyFandon.domain.Personagem;
import br.com.martines_dev.MyFandon.domain.Usuario;
import br.com.martines_dev.MyFandon.exceptions.ExceptionOAnimeTemOAdminInvalido;
import br.com.martines_dev.MyFandon.exceptions.RecursoJaExiste;
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
	public Anime inserir( Anime anime , String nomeUsuario) {
		
		
		Usuario usuario = pegarUsuarioPeloNome(nomeUsuario);
		anime.setAdmin( Arrays.asList(  usuario ) );
		
		return inserir(anime);			
	}
	

	@Transactional
	/* *****************************************************
	 * [----------DICA PARA O PROGRAMADOR----------]
	 * use o método inserir(Anime anime ,String nome) acima para inserir o anime com o usuario, assim não corre o risco 
	 * de ficar usuario nulo no banco de dados, e gerar inconsistencia*/
	public Anime inserir(Anime anime) {
	

		if( animeNaoEstarNoBancoDeDados(anime) ) {
			return animeDAO.save(anime);	
		}else {
			throw new RecursoJaExiste("Erro: " + anime.getNome()+" já existe");
		}
				
	}
	
	


	private boolean animeNaoEstarNoBancoDeDados(Anime anime) {
		
		List<Anime> animeLista = animeDAO.findByNome(anime.getNome() );
		
		return animeLista.isEmpty();
	}

	
	
	@Override
	@Transactional
	public Anime atualizar(Anime anime, String nomeUsuario) {
		
		Anime animeEncontrado = animeDAO.findById(anime.getId())
				.orElseThrow( () -> new RecursoNaoEncontrado("Anime não foi encontrado"));
			
		
		if ( ehUmDonoDoAnimeQuemEstaModificandoEle(anime.getId(), nomeUsuario) ) {

			
			anime.setAdmin( animeEncontrado.getAdmin() );
			
			return atualizar( anime , anime.getId() );
		}else {
			throw new ExceptionOAnimeTemOAdminInvalido("Erro você não é o dono desse anime");
		}

	}

	/* *****************************************************
	 * [----------DICA PARA O PROGRAMADOR----------]
	 * use o método atualizar(Anime anime ,String nome) acima para atualizar o anime com o usuario, assim não corre o risco 
	 * de ficar usuario nulo no banco de dados, e gerar inconsistencia*/
	@Transactional
	@Override
	public Anime atualizar(Anime anime , Long animeId) {
		
		return animeDAO.save( anime );
	}
	
	



	@Override
	@Transactional
	public void deletar(Long id,String usuarioNome) {
		
		usuarioDAO.findByUsername(usuarioNome).orElseThrow( () -> new RecursoNaoEncontrado("Usuario não encontrado") );
		
		
		
		if(ehUmDonoDoAnimeQuemEstaModificandoEle(id, usuarioNome))	{
			deletar(id);	
		}
		else {			
			throw new ExceptionOAnimeTemOAdminInvalido("Erro você não é o dono desse anime");
		}
		
	}


	private boolean ehUmDonoDoAnimeQuemEstaModificandoEle(Long idAnime, String usuarioNome)
			throws RecursoNaoEncontrado {
		Anime anime = animeDAO.findById(idAnime).orElseThrow(() -> new RecursoNaoEncontrado("Anime não existe")  );
		
		boolean validOperation = false;
		
		for( Usuario admin :anime.getAdmin() ) 
		{
			if( admin.getUsername().contentEquals( usuarioNome )) {
				validOperation = true;	
			}
		}
		return validOperation;
	}


	@Override
	@Transactional
	public void deletar(Long id) {
		try {			
			animeDAO.deleteById( id ) ;
		}catch(EmptyResultDataAccessException ex) {
			throw new RecursoNaoEncontrado("Anime nao existe: "+id);
		}
	}
	
	
	@Override
	public Page<Anime> buscar( Pageable pageable , String nomeAnime) {

		return animeDAO.findByNomeContainingIgnoreCase(pageable,nomeAnime);
	}
	
	@Override
	public List<Anime> listar() {
		
		return animeDAO.findAll();
	}

	@Override
	public Page<Anime> listar(Pageable pageable) {
		
		return animeDAO.findAll(pageable);
	}
	@Override
	public Page<Anime> listar(int page ) {
		
		
		Pageable pageable = PageRequest.of( page , QTD_ANIMES_POR_PAGINA);
		return animeDAO.findAll( pageable );
	}
	

	@Override
	@Transactional
	public void addPersonagem(Long animeId, Personagem personagem) {
		
		Anime animeFounded = animeDAO.findById( animeId )
			.orElseThrow( 
					() -> new RecursoNaoEncontrado("Anime não encontrado")
			);
		
		if ( !personagemDAO.findByNome( personagem.getNome()).isPresent() ) {
			
			Personagem personagemAdicionado = personagemDAO.save( personagem );
			personagemAdicionado.setAnime(animeFounded);
			animeFounded.getPersonagems().add( personagemAdicionado );
			
		}else {
			throw new RecursoJaExiste("Personagem já existe");
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
	/**
	 * na propriedade comentario é obrigatorio o nome do usuario
	 * */
	public Comentario addComentario(Long animeId, Comentario comentario, String nomeUsuario) {
		
		Optional<Anime> anime = animeDAO.findById( animeId );
		
		if( !anime.isPresent() ) {
			throw new RecursoNaoEncontrado("Erro anime invalido");			
		}
		
		
		comentario.setUsuario( pegarUsuarioPeloNome(nomeUsuario) );
		comentario.setAnime( anime.get() );
		Comentario novoComentario = comentarioDAO.save( comentario );
		
		anime.get().getComentarios().add( novoComentario );
		
		return novoComentario;	
	}
	
	/**
	 * @exception lançar uma RecursoNaoEncontrado se o usuario não existir
	 * */
	private Usuario pegarUsuarioPeloNome(String nomeUsuario) throws RecursoNaoEncontrado 
	{
		return usuarioDAO.findByUsername( nomeUsuario )
			 	.orElseThrow( () -> new RecursoNaoEncontrado("[Bug] Usuario que não existe está comentando") ) ;
	}


	@Override
	public List<Categoria> getCategorias(Long animeId) {

		Anime animeFounded = animeDAO
			.findById( animeId )
			.orElseThrow(		() -> new RecursoNaoEncontrado("Erro anime não foi encontrado") );
		
		return animeFounded.getCategorias();
	}





	
	
}
