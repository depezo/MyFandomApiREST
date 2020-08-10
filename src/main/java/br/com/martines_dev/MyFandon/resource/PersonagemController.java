package br.com.martines_dev.MyFandon.resource;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sun.istack.NotNull;

import br.com.martines_dev.MyFandon.domain.Comentario;
import br.com.martines_dev.MyFandon.domain.Personagem;
import br.com.martines_dev.MyFandon.resource.interfaces.CrudController;
import br.com.martines_dev.MyFandon.service.interfaces.PersonagemServiceInterface;

@RestController
public class PersonagemController extends CrudController<Personagem,Long>{

	@Autowired
	PersonagemServiceInterface personagemService;


	@PostMapping("api/personagem/{id}")
	@ResponseStatus( code = HttpStatus.CREATED )
	public Personagem inserir(
			@RequestBody Personagem personagem  , 
			@PathVariable("id") Long idAnime
	) {
		
		return personagemService.inserir( personagem , idAnime );
	}

	@PutMapping("api/personagem/{id}")
	public Personagem atualizar( 
					@RequestBody Personagem personagem, 
					@PathVariable("id") Long idAnime) 
	{
		return personagemService.atualizar( personagem , idAnime );
	}

	@GetMapping("api/personagem/{id}")
	public Personagem pegarUm( 
					@PathVariable("id") Long id) {
		
		return personagemService.pegarUm( id );
	}

	@DeleteMapping("api/personagem/{id}")
	@ResponseStatus( code = HttpStatus.NO_CONTENT )
	public void deletar( @PathVariable("id") Long id) {
		
		personagemService.deletar( id );
		
	}

	@GetMapping("api/personagem")
	public Page<Personagem> listar( 
		@RequestParam( name = "page" , defaultValue  = "0") int page) 
	{
		return personagemService.listar(page);
	}
	
	@PostMapping("api/personagem/{id}/registrarComentario")
	@ResponseStatus( code = HttpStatus.NO_CONTENT )
	public void registrarComentario( 
				@PathVariable("id") Long id,  
				@RequestBody Comentario comentario , 
				Principal principal ) 
	{
		
		personagemService.addComentario( id , comentario , principal.getName() ) ;
	}

	@GetMapping("api/personagem/{id}/comentarios")
	public List<Comentario> pegarComentarios( @NotNull @PathVariable Long id ) {
				
		return personagemService.getComentarios( id );
	}
	
	/**abaixo não vale a pena olhar*/
	
	
	
	
	
	
	@Deprecated()
	public List<Personagem> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Deprecated
	public Personagem inserir(Personagem categoria) {
		// TODO Auto-generated method stub
		return null;
	}

}
