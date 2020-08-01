package br.com.martines_dev.MyFandon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.martines_dev.MyFandon.domain.Comentario;
import br.com.martines_dev.MyFandon.exceptions.RecursoNaoEncontrado;
import br.com.martines_dev.MyFandon.persistence.ComentarioPersistence;
import br.com.martines_dev.MyFandon.service.interfaces.ComentarioServicoInterface;


@Service
public class ComentarioService implements ComentarioServicoInterface{

	@Autowired
	ComentarioPersistence comentarioDAO;
	
	@Override
	public Comentario inserir(Comentario comentario) {
		
		if( comentario.getUsuario() == null) {
			throw new RecursoNaoEncontrado("Erro não é possivel inserir um comentario o usuario não existe");
		}
		return comentarioDAO.save( comentario );
	}

	@Override
	public Comentario atualizar(Comentario comentario, Long id) {
		
		if( comentario.getUsuario() == null) {
			throw new RecursoNaoEncontrado("Erro não é possivel inserir um comentario sem usuario");
		}
		
		return comentarioDAO.save( comentario );
	}

	@Override
	public Comentario pegarUm(Long id) {
		return comentarioDAO.findById( id ).orElseGet( () -> {
			throw new RecursoNaoEncontrado("Esse comentario não existe");
		});
	}

	@Override
	public void deletar(Long id) {
		
		comentarioDAO.deleteById( id );
	}

	@Override
	public List<Comentario> listar() {
		return comentarioDAO.findAll();
	}
	
	
	
	
	
	
	
	@Override
	@Deprecated
	public Page<Comentario> listar(int page) {
		
		//categoriaDAO.findAll(pageable);
		return null;
	}
	

}
