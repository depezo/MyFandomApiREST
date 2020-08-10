package br.com.martines_dev.MyFandon.service.interfaces;

import br.com.martines_dev.MyFandon.domain.Usuario;

public interface UsuarioServiceInterface {
	
	public Usuario cadastrarUsuario( Usuario novoUsuario ) ;
	
	public Usuario buscarUsuario( String usuarioNome );

	public Usuario atualizarUsuario( Usuario usuario , Long usuarioId , String principalUsuario );
}
