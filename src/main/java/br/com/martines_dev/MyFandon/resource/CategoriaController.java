package br.com.martines_dev.MyFandon.resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriaController {


	@DeleteMapping("api/categoria/{id}")
	public void deletarCategoria( int id ) {
		
	}
	@PostMapping("api/categoria")
	public String inserirCategoria() {
		return "inserir categoria";
	}
	@PutMapping("api/categoria/{id}")
	public String atualizarCategoria() {
		return "atualizar categoria";
	}
	@GetMapping("api/categoria/{id}")
	public String verUmaCategoria() {
		return "atualizar categoria";
	}
	@GetMapping("api/categoria")
	public String listarCategoria() {
		return "atualizar categoria";
	}
	
	/*
	 * ROTAS RELATIVAS A ANIME
	 * **/
	@GetMapping("api/categoria/{id}/inserirAnime")
	public String inserirAnime() {
		return "Inserir Anime";
	}
	@GetMapping("api/categoria/{id}/inserirPersonagem")
	public String inserirPersonagem() {
		return "Inserir Categoria";
	}
}
