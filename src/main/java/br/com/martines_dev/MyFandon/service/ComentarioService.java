package br.com.martines_dev.MyFandon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.martines_dev.MyFandon.domain.Comentario;
import br.com.martines_dev.MyFandon.domain.Usuario;
import br.com.martines_dev.MyFandon.exceptions.ExceptionORecursoTemOAdminInvalido;
import br.com.martines_dev.MyFandon.exceptions.RecursoNaoEncontrado;
import br.com.martines_dev.MyFandon.persistence.ComentarioPersistence;
import br.com.martines_dev.MyFandon.persistence.UsuarioPersistence;
import br.com.martines_dev.MyFandon.service.interfaces.ComentarioServicoInterface;


@Service
public class ComentarioService implements ComentarioServicoInterface{

	@Autowired
	ComentarioPersistence comentarioDAO;
	
	@Autowired
	UsuarioPersistence usuarioDAO;
	
	@Override
	public Comentario inserir(Comentario comentario) {
		
		if( comentario.getUsuario() == null) {
			throw new RecursoNaoEncontrado("Erro não é possivel inserir um comentario o usuario não existe");
		}
		return comentarioDAO.save( comentario );
	}

	@Override
	public Comentario atualizar(Comentario comentario, Long id , String nome) 
	{
		Comentario comentarioAtualizavel = comentarioDAO.findById(comentario.getId()).get();
		
		
		if( !comentarioAtualizavel.getUsuario().getName().equals(nome)) {
			throw new ExceptionORecursoTemOAdminInvalido("Não é possivel deletar o comentario do outro");
		}
		
		comentarioAtualizavel.setMensagem( comentario.getMensagem() );
		
		return atualizar( comentarioAtualizavel ,id);
	}
	/**
	 * Aviso ao programador: Não utilize esse método, ao invés disso utilize o metodo atualizar com o parametro nome 
	 * para garantir que o comentario tenha as validações e o usuario
	 */
	@Override
	public Comentario atualizar(Comentario comentario , Long id ) {
		usuarioDAO.findByUsername( comentario.getUsuario().getName() )
				.orElseThrow( ( ) -> new RecursoNaoEncontrado("Usuario não existe") );
		
		return comentarioDAO.save( comentario );
	}

	
	@Override
	public Comentario pegarUm(Long id) {
		return comentarioDAO.findById( id ).orElseThrow( () -> 
			new RecursoNaoEncontrado("Esse comentario não existe")
		);
	}

	
	@Override
	public void deletar(Long idComentario, String nomeUsuario) {

		Usuario usuario = usuarioDAO.findByUsername(nomeUsuario)
					.orElseThrow( () -> new RecursoNaoEncontrado("Não é possivel deletar um comentario que não tem dono!") );
		
		Comentario comentario = comentarioDAO.findById(idComentario)
				.orElseThrow( () -> new RecursoNaoEncontrado("Comentario não existe") );
		
		
		if( usuarioEhDonoDoComentario(usuario, comentario) ) {
			deletarComentario( idComentario );
		} else {
			throw new ExceptionORecursoTemOAdminInvalido("Operação invalida o usuario não é dono do comentario que quer deletar");
		}
	}
	
	private void deletarComentario(Long id ) { deletar(id);}

	@Override
	public void deletar(Long id) {
		
		
		comentarioDAO.findById( id ).orElseThrow( () -> new RecursoNaoEncontrado("Comentario nao existe: "+id)	);
		
		comentarioDAO.deleteById( id ) ;
	}

	@Override
	public List<Comentario> listar() {
		return comentarioDAO.findAll( Sort.by("id").descending() );
	}
	
	
	
	
	private boolean usuarioEhDonoDoComentario(Usuario usuario, Comentario comentario) {
		return comentario.getUsuario().getName() == usuario.getName();
	}
	
	@Override
	@Deprecated
	public Page<Comentario> listar(int page) {
		
		return null;
	}


	

}
