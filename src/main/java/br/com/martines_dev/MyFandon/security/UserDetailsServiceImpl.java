package br.com.martines_dev.MyFandon.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.martines_dev.MyFandon.domain.Usuario;
import br.com.martines_dev.MyFandon.service.UsuarioService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = Optional
				.of(  usuarioService.buscarUsuario( username ) )
				.orElseThrow( () -> new UsernameNotFoundException("Falha no Login: Usuanio não encontrado") );
		
		
		
		UserDetailsImpl userDetails = new UserDetailsImpl();
			userDetails.setUsuario( usuario );
		
		return userDetails;
	}

}
