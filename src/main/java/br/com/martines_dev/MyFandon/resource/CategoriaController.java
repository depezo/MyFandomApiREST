package br.com.martines_dev.MyFandon.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.martines_dev.MyFandon.domain.Categoria;
import br.com.martines_dev.MyFandon.service.CategoriaService;

@RestController
public class CategoriaController {

	@Autowired
	CategoriaService categoriaDAO;
	
	@DeleteMapping("api/categoria/{id}")
	public void deletar( @PathVariable Long id ) {
		categoriaDAO.deletar( id );
	}
	
	@PostMapping("api/categoria")
	public Categoria inserir( @RequestBody Categoria categoria ) {
		
		return categoriaDAO.inserir( categoria );
	}
	
	@PutMapping("api/categoria/{id}")
	public Categoria atualizar( 
		@RequestBody Categoria categoria ,
		@PathVariable Long id) 
	{
		
		return categoriaDAO.atualizar(categoria, id);
	}
	
	@GetMapping("api/categoria/{id}")
	public Categoria verUma( @PathVariable Long id ) {
		
		return categoriaDAO.pegarUm( id );
	}
	
	@GetMapping("api/categoria")
	public List<Categoria> listar() {
		return categoriaDAO.listar();
	}
	
	
	
	
	/*
	 * ROTAS RELATIVAS A ANIME
	 * **/
	@GetMapping("api/categoria/{idCategoria}/inserirAnime/{idAnime}")
	public String inserirAnime(
		@PathVariable Long idCategoria,
		@PathVariable Long idAnime ) 
	{
		categoriaDAO.inserirAnime(idCategoria, idAnime);
		return "Anime inserido!";
	}	
	
}
