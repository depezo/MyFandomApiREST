package br.com.martines_dev.MyFandon.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.martines_dev.MyFandon.domain.Anime;
import br.com.martines_dev.MyFandon.domain.Categoria;
import br.com.martines_dev.MyFandon.exceptions.RecursoNaoEncontrado;
import br.com.martines_dev.MyFandon.persistence.AnimePersistence;
import br.com.martines_dev.MyFandon.persistence.CategoriaPersistence;
import br.com.martines_dev.MyFandon.service.interfaces.CategoriaServiceInterface;


@Service
public class CategoriaService implements CategoriaServiceInterface {

	@Autowired
	CategoriaPersistence categoriaDAO;
	@Autowired
	AnimePersistence animeDAO;

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
		return categoriaDAO.findAll();
	}

	
	@Override
	
	public void inserirAnime( Long idCategoria , Long idAnime) {
		
		Optional<Anime> animeFounded = animeDAO.findById( idAnime );
		Optional<Categoria> categoriaFounded = categoriaDAO.findById( idCategoria );
		
		if( animeFounded.isPresent()) {
			
			animeFounded.get().getCategorias().add( categoriaFounded.get() );
			animeDAO.save( animeFounded.get() );
		}else {
			throw new RecursoNaoEncontrado( "Não foi possivel inserir um anime!" ); 
		}
		
	}
	

	
	
	
	
	
	
	
	
	
	
	@Override
	@Deprecated
	public Page<Categoria> listar(int page) {
		
		return null;
	}


	

}
