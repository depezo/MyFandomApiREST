package br.com.martines_dev.MyFandon.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.martines_dev.MyFandon.domain.Categoria;
import br.com.martines_dev.MyFandon.service.CategoriaService;

@RestController
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;
	
	@DeleteMapping("api/categoria/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deletar( @PathVariable Long id ) {
		categoriaService.deletar( id );
	}
	
	@PostMapping("api/categoria")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Categoria inserir( @RequestBody Categoria categoria ) {
		
		return categoriaService.inserir( categoria );
	}
	
	@PutMapping("api/categoria/{id}")
	public Categoria atualizar( 
		@RequestBody Categoria categoria ,
		@PathVariable Long id) 
	{
		
		return categoriaService.atualizar(categoria, id);
	}
	
	@GetMapping("api/categoria/{id}")
	public Categoria verUma( @PathVariable Long id ) {
		
		return categoriaService.pegarUm( id );
	}
	
	@GetMapping("api/categoria")
	public List<Categoria> listar() {
		return categoriaService.listar();
	}
	
	
	
	
	/*
	 * ROTAS RELATIVAS A ANIME
	 * **/
	@GetMapping("api/categoria/{idCategoria}/inserirAnime/{idAnime}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String inserirAnimeNaCategoria(
			@PathVariable Long idCategoria,
			@PathVariable Long idAnime ) 
	{
		categoriaService.inserirAnime(idCategoria, idAnime);
		return "Anime inserido!";
	}	
	
}
