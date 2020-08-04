package br.com.martines_dev.MyFandon.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.martines_dev.MyFandon.domain.Comentario;
import br.com.martines_dev.MyFandon.service.interfaces.ComentarioServicoInterface;

@RestController
public class ComentarioController {

	@Autowired
	ComentarioServicoInterface comentarioService;
	
	@DeleteMapping("api/comentario/{id}")
	public void deletar( @PathVariable Long id ) {
		comentarioService.deletar( id );
	}
	
	
	@PutMapping("api/comentario/{id}")
	public Comentario atualizar( 
		@RequestBody Comentario categoria ,
		@PathVariable Long id) 
	{
		
		return comentarioService.atualizar(categoria, id);
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
