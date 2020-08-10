package br.com.martines_dev.MyFandon.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.martines_dev.MyFandon.domain.Usuario;
import br.com.martines_dev.MyFandon.exceptions.ExceptionORecursoTemOAdminInvalido;
import br.com.martines_dev.MyFandon.exceptions.RecursoJaExiste;
import br.com.martines_dev.MyFandon.exceptions.RecursoNaoEncontrado;
import br.com.martines_dev.MyFandon.persistence.UsuarioPersistence;
import br.com.martines_dev.MyFandon.service.interfaces.UsuarioServiceInterface;

@Service
public class UsuarioService implements UsuarioServiceInterface {

	@Autowired
	UsuarioPersistence usuarioDAO;
	
	@Override
	public Usuario cadastrarUsuario(@Valid Usuario novoUsuario) {
		
		if( usuarioDAO.findByUsername(novoUsuario.getUsername()).isPresent()  ) {
			throw new RecursoJaExiste("Usuario já foi cadastrado");
		}
		else {
			return usuarioDAO.save( novoUsuario );	
		}
	}

	@Override
	public Usuario buscarUsuario(String usuarioNome) {
		
		return usuarioDAO.findByUsername(usuarioNome)
					.orElseThrow( () -> new RecursoNaoEncontrado("Usuario Não encontrado"));
	}

	@Override
	public Usuario atualizarUsuario(Usuario usuario, Long usuarioId, String principalNome ) {
		
		Usuario encontrado = usuarioDAO.findById(usuarioId)
				.orElseThrow( () -> new RecursoNaoEncontrado("Usuario Não encontrado"));
		
		/* é melhor comparar o usuario encontrado ao invés do parametro usuario
		 * pois ainda o encontrado não teve seus dados modificados, então eles 
		 * são confiaveis para verificação*/
		if( !encontrado.getUsername().equals(principalNome) ) {
			throw new ExceptionORecursoTemOAdminInvalido("Somente o usuario dono pode se atualizar");
		}
		
		return usuarioDAO.save( usuario );
		
	}

}
