package br.com.martines_dev.MyFandon.resource;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.martines_dev.MyFandon.domain.Usuario;
import br.com.martines_dev.MyFandon.dto.UsuarioDTO;
import br.com.martines_dev.MyFandon.service.interfaces.UsuarioServiceInterface;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;

@RestController
public class UsuarioController {

	@Autowired
	UsuarioServiceInterface usuarioService;
	
        
        
        @ApiOperation(
                value = "Cadastrar-se na Fandom!"
	)
        @ApiImplicitParams({
            @ApiImplicitParam(
                name = "novoUsuario",
                value = "Cadastrar-se na plataforma",
                required = true,
                dataType = "String",
                paramType = "body",
                example = "{\n \"nomeCompleto\": \"Maria Antonieta\" ,"
                        + "\n \"nomeUsuario\": \"mariazinha\" "
                        + "\n \"senha\": \"minhaSenha\" "
                        + " \n}" 
            )
        } )
        
	@PostMapping("/api/usuario")
	public Usuario cadastrarUsuario( 
                @Valid @RequestBody UsuarioDTO novoUsuario ) 
	{
		
		Usuario _novoUsuario = mapDoUsuarioDTOparaUsuario(novoUsuario);
		
		return usuarioService.cadastrarUsuario( _novoUsuario );
	}

        
        @ApiOperation(
                value = "Atualizar o Usuario!",
		notes = "Atualizar os dados do usuario, somente o usuairo pode atualizar "
                        + "seus dados!",
                authorizations = {@Authorization(value="basicAuth")}
	)
	@PutMapping("/api/usuario/{id}")
	public Usuario atualizar( 
                @Valid @RequestBody UsuarioDTO usuario ,
                @PathVariable("id") Long usuarioId ,
                @ApiParam(hidden=true) Principal principal ) 
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
