package br.com.martines_dev.MyFandon.resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnimeController {

	
	
	/*
	 * ROTAS DELATIVAS AO ANIME
	 **/
	@DeleteMapping("api/anime/{id}")
	public void deletarAnime( int id ) {
		
	}
	@PostMapping("api/anime")
	public String inserirAnime() {
		return "inserir anime";
	}
	@PutMapping("api/anime/{id}")
	public String atualizarAnime() {
		return "atualizar anime";
	}
	@GetMapping("api/anime/{id}")
	public String verUmAnime() {
		return "atualizar anime";
	}
	@GetMapping("api/anime")
	public String listarAnimes() {
		return "atualizar anime";
	}
		
	/*
	 * ROTAS RELATIVAS AO COMENTARIO
	 **/
	@PostMapping("api/anime/{id}/registrarComentario")
	public String registrarComentario(  ) {
		return "registrar comentario";
	}
	@DeleteMapping("api/anime/{id}/deletarComentario/{cid}")
	public String deletarComentario(  ) {
		return "deletar comentario";
	}
	@GetMapping("api/anime/{id}/comentarios")
	public String pegarComentarios() {
		return "comentarios";
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
