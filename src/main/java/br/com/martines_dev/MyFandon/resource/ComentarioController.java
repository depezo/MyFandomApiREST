package br.com.martines_dev.MyFandon.resource;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.martines_dev.MyFandon.domain.Comentario;
import br.com.martines_dev.MyFandon.service.interfaces.ComentarioServicoInterface;

@RestController
public class ComentarioController {

	@Autowired
	ComentarioServicoInterface comentarioService;
	
	@DeleteMapping("api/comentario/{id}")
	@ResponseStatus( code = HttpStatus.NO_CONTENT )
	public void deletar( 
					@PathVariable Long id , 
					Principal principal ) 
	{
		
		comentarioService.deletar( id , principal.getName() );
	}
	
	
	@PutMapping("api/comentario/{id}")
	public Comentario atualizar( 
				@RequestBody Comentario categoria ,
				@PathVariable Long id ,
				Principal principal) 
	{
		
		return comentarioService.atualizar(categoria, id , principal.getName() );
	}
	
	@GetMapping("api/comentario/{id}")

	public Comentario verUma( @PathVariable Long id ) {
		
		return comentarioService.pegarUm( id );
	}
	
	@GetMapping("api/comentario")
	public List<Comentario> listar() {
		return comentarioService.listar();
	}
	
}
