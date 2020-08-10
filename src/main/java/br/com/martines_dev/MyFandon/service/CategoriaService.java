package br.com.martines_dev.MyFandon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.martines_dev.MyFandon.domain.Anime;
import br.com.martines_dev.MyFandon.domain.Categoria;
import br.com.martines_dev.MyFandon.domain.Usuario;
import br.com.martines_dev.MyFandon.exceptions.ExceptionORecursoTemOAdminInvalido;
import br.com.martines_dev.MyFandon.exceptions.RecursoNaoEncontrado;
import br.com.martines_dev.MyFandon.persistence.AnimePersistence;
import br.com.martines_dev.MyFandon.persistence.CategoriaPersistence;
import br.com.martines_dev.MyFandon.persistence.UsuarioPersistence;
import br.com.martines_dev.MyFandon.service.interfaces.CategoriaServiceInterface;


@Service
public class CategoriaService implements CategoriaServiceInterface {

	@Autowired
	CategoriaPersistence categoriaDAO;
	@Autowired
	AnimePersistence animeDAO;

	@Autowired
	UsuarioPersistence usuarioDAO;
	
	
	@Override
	public Categoria inserir(Categoria categoria) {
		
		return categoriaDAO.save( categoria );
	}

	@Override
	public Categoria atualizar(Categoria categoria, Long id) {
		return categoriaDAO.save( categoria );
	}

	@Override
	public Categoria pegarUm(Long id) {
		return categoriaDAO
				  .getOne( id );
	}

	@Override
	public void deletar(Long id) {
		
		try {			
			categoriaDAO.deleteById( id );
		}catch(EmptyResultDataAccessException ex) {
			throw new RecursoNaoEncontrado("Categoria nao existe: "+id);
		}
	}

	@Override
	public List<Categoria> listar() {
		return categoriaDAO.findAll(  );
	}

	
	@Override
	public void inserirAnime( Long idCategoria , Long idAnime , String nomeUsuario) {
		
		if( ehUmDonoDoAnimeQuemEstaModificandoEle(idAnime, nomeUsuario)) {
			
			usuarioDAO.findByUsername(nomeUsuario);
		}
		else 
		{
			throw new ExceptionORecursoTemOAdminInvalido("o admin não é o dono desse anime");
		}
		
		
		inserirAnime(idCategoria,idAnime);
	}
	@Override
	public void inserirAnime(Long idCategoria, Long idAnime)
	{
		
		Anime animeFounded = animeDAO.findById( idAnime )
				.orElseThrow( () -> new RecursoNaoEncontrado("Esse anime não existe"));
		
		Categoria categoriaFounded = categoriaDAO.findById( idCategoria )
				.orElseThrow( () -> new RecursoNaoEncontrado("Essa categoria não existe!") );
		
		
		animeFounded.getCategorias().add( categoriaFounded );
		animeDAO.save( animeFounded );
	}

	
	
	
	
	@Override
	@Deprecated
	public Page<Categoria> listar(int page) {
		
		return null;
	}
	
	private boolean ehUmDonoDoAnimeQuemEstaModificandoEle(Long idAnime, String usuarioNome)
								throws RecursoNaoEncontrado
	{
		
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



	

}
