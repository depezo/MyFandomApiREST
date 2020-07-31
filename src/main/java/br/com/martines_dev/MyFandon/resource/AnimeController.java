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
import br.com.martines_dev.MyFandon.domain.Comentario;
import br.com.martines_dev.MyFandon.domain.Personagem;
import br.com.martines_dev.MyFandon.service.interfaces.AnimeServiceInterface;

@RestController
public class AnimeController {

	
	@Autowired
	private AnimeServiceInterface animeService;
	/*
	 * ROTAS DELATIVAS AO ANIME
	 **/
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
	public Anime atualizar( @PathVariable Long id , @RequestBody Anime anime ) {
		
		return animeService.atualizar( anime , id );
	}
	@GetMapping("api/anime/{id}")
	public Anime verUm( @PathVariable Long id ) {
		
		return animeService.pegarUm( id );
	}
	@GetMapping("api/anime")
	public Page<Anime>listar( 
		@RequestParam(name="page",defaultValue = "0") int id  ) 
	{
		return animeService.listar( id );
	}
		
	/**
	 * ROTAS RELATIVAS AO PERSONAGEM
	 * */
	@PostMapping("api/anime/{id}/inserirPersonagem")
	public void registrarAnime( 
		@PathVariable Long id,  @RequestBody Personagem personagem ) 
	{
		animeService.addPersonagem( id , personagem ) ;
	}
	
	
	/*
	 * ROTAS RELATIVAS AO COMENTARIO
	 **/
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
	
	/*
	 * ROTAS RELATIVAS A CATEGORIA
	 **/
	@PostMapping("api/anime/{id}/registrarCategoria")
	public String registrarCategoria(  ) {
		
		
		return "registrar comentario";
	}
	@DeleteMapping("api/anime/{id}/deletarCategoria/{cid}")
	public String deletarCategoria(  ) {
		return "deletar comentario";
	}
	@GetMapping("api/anime/{id}/categorias")
	public String pegarCategorias() {
		return "comentarios";
	}
	
	
	
}
