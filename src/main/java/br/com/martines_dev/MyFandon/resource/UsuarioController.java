package br.com.martines_dev.MyFandon.resource;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.martines_dev.MyFandon.UsuarioDTO;
import br.com.martines_dev.MyFandon.domain.Usuario;
import br.com.martines_dev.MyFandon.service.interfaces.UsuarioServiceInterface;

@RestController
public class UsuarioController {

	@Autowired
	UsuarioServiceInterface usuarioService;
	
	@PostMapping("/api/usuario")
	public Usuario cadastrarUsuario( 
						@Valid @RequestBody UsuarioDTO novoUsuario ) 
	{
		
		Usuario _novoUsuario = mapDoUsuarioDTOparaUsuario(novoUsuario);
		
		return usuarioService.cadastrarUsuario( _novoUsuario );
	}

	@PutMapping("/api/usuario/{id}")
	public Usuario atualizar( 
						@Valid @RequestBody UsuarioDTO usuario ,
						@PathVariable("id") Long usuarioId ,
						Principal principal ) 
	{
		
		Usuario _usuario = mapDoUsuarioDTOparaUsuario(usuario);
		
		return usuarioService.atualizarUsuario( _usuario, usuarioId , principal.getName() );
	}
	
	
	
	private Usuario mapDoUsuarioDTOparaUsuario(UsuarioDTO novoUsuario) {
		Usuario _novoUsuario = new Usuario( );
		_novoUsuario.setName( novoUsuario.getNomeCompleto() );
		_novoUsuario.setId( novoUsuario.getId() );
		_novoUsuario.setPassword( novoUsuario.getSenha() );
		_novoUsuario.setUsername( novoUsuario.getNomeUsuario() );
		return _novoUsuario;
	}
	
	
}
