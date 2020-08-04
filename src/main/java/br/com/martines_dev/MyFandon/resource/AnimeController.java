package br.com.martines_dev.MyFandon.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.martines_dev.MyFandon.domain.Anime;
import br.com.martines_dev.MyFandon.domain.Categoria;
import br.com.martines_dev.MyFandon.domain.Comentario;
import br.com.martines_dev.MyFandon.domain.Personagem;
import br.com.martines_dev.MyFandon.exceptions.IdNaoConfereException;
import br.com.martines_dev.MyFandon.service.interfaces.AnimeServiceInterface;

@RestController
public class AnimeController {

	
	@Autowired
	private AnimeServiceInterface animeService;

	@GetMapping("api/anime")
	public Page<Anime> listar( 
		@RequestParam(name="page",defaultValue = "0") int id )  {
		
		return animeService.listar( id );
	}
	
	@DeleteMapping("api/anime/{id}")
	public void deletar( @PathVariable Long id ) 
	{
		animeService.deletar( id );
	}
	
	
	@PostMapping("api/anime")
	public Anime inserir( @RequestBody Anime anime ) {
		
		return animeService.inserir( anime );
	}
	
	
	@PutMapping("api/anime/{id}")
	public Anime atualizar
		( @PathVariable Long id , @RequestBody Anime anime ) {
		
 		if( !anime.getId().equals(id)) {
			throw new IdNaoConfereException(); 
		}
		
		return animeService.atualizar( anime , id );
	}
	
	
	@GetMapping("api/anime/{id}")
	public Anime verUm( @PathVariable Long id ) {
		
		return animeService.pegarUm( id );
	}
	
	
	
	
	
	
	@GetMapping("api/anime/{id}/categorias")
	public List<Categoria> listarCategoriasDeUmAnime( 
		@PathVariable Long id )  {
		
		return animeService.getCategorias ( id );
	}
	
	

		

	@PostMapping("api/anime/{id}/registrarPersonagem")
	public void registrarPersonagem( 
		@PathVariable Long id,  
		@RequestBody Personagem personagem ) 
	{
		animeService.addPersonagem( id , personagem ) ;
	}
	
	

	@PostMapping("api/anime/{id}/registrarComentario")
	public Comentario registrarComentario( 
		@PathVariable Long id,  @RequestBody Comentario comentario ) 
	{
		return animeService.addComentario( id , comentario ) ;
	}

	
	@GetMapping("api/anime/{id}/comentarios")
	public List<Comentario> pegarComentarios( @PathVariable Long id ) {
		return animeService.pegarUm(id).getComentarios();
	}
	
	
	
}
