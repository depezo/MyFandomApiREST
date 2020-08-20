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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@RestController
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;
	
        
        
        
        
        @ApiOperation(
                value = "Deletar uma categoria por id!",
		notes = "Delete uma categoria por id",
                authorizations = {@Authorization(value="basicAuth")}
	)
        @ApiResponses( 
            @ApiResponse( code = 404 ,message = "Recurso Não Encontrado") 
        )
        
	@DeleteMapping("api/categoria/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deletar( @PathVariable Long id ) {
		categoriaService.deletar( id );
	}
	
        
        
        
        @ApiOperation(
                value = "Inserir uma categoria!",
		notes = "Inserir uma categoria por id",
                authorizations = {@Authorization(value="basicAuth")}
	)
        @ApiImplicitParams({
            @ApiImplicitParam(
                name = "categoria",
                value = "Inserir uma categoria",
                required = true,
                dataType = "String",
                paramType = "body",
                example = "{\n \"nome\": \"Sua categoria\" \n}" 
            )
        } )
        
	@PostMapping("api/categoria")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Categoria inserir( @RequestBody Categoria categoria ) {
		
		return categoriaService.inserir( categoria );
	}
	
        
        
        
        @ApiOperation(
                value = "Atualizar uma categoria!",
		notes = "Atualizar uma categoria por id",
                authorizations = {@Authorization(value="basicAuth")}
	)
        
	@PutMapping("api/categoria/{id}")
	public Categoria atualizar( 
		@RequestBody Categoria categoria ,
		@PathVariable Long id) 
	{
		
		return categoriaService.atualizar(categoria, id);
	}
	
        
        @ApiOperation(
                value = "Ver uma categoria por ID!",
		notes = "Ver uma categoria por id"
	)
	@GetMapping("api/categoria/{id}")
	public Categoria verUma( @PathVariable Long id ) {
		
		return categoriaService.pegarUm( id );
	}
	
        
        @ApiOperation(
                value = "Listar categoria!",
		notes = "Recebe uma listagem de categoria!"
	)
	@GetMapping("api/categoria")
	public List<Categoria> listar() {
		return categoriaService.listar();
	}
	
	
	
	
	/*
	 * ROTAS RELATIVAS A ANIME
	 * **/
        
        
        @ApiOperation(
		value = "Inserir Categoria em um anime",
                notes = "Não precisa enviar nenhum corpo apenas pela url é possivel inserir "
                        + "a categoria em num anime!",
		authorizations = {@Authorization(value="basicAuth")}
	)        
        
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
