package br.com.martines_dev.MyFandon.resource;

import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.martines_dev.MyFandon.domain.Anime;
import br.com.martines_dev.MyFandon.domain.Categoria;
import br.com.martines_dev.MyFandon.domain.Comentario;
import br.com.martines_dev.MyFandon.domain.Personagem;
import br.com.martines_dev.MyFandon.dto.AnimeDTO;
import br.com.martines_dev.MyFandon.exceptions.IdNaoConfereException;
import br.com.martines_dev.MyFandon.service.interfaces.AnimeServiceInterface;
import br.com.martines_dev.MyFandon.util.LongIdUtil;
import br.com.martines_dev.MyFandon.util.NullAwareBeanUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.springframework.data.web.PageableDefault;

@RestController
public class AnimeController {

	
	@Autowired
	private AnimeServiceInterface animeService;

	@Autowired
	private NullAwareBeanUtils copyUtils;
	
	
	@GetMapping("api/anime/buscar")
	@ApiOperation( 
                value = "Buscar Personagem",
                notes = "Para buscar algo prencha o campo q, e uma listagem de personagens"
                        + " será retornada!"
        )
	public Page<Anime> buscar( 
                
                @PageableDefault( size = 10 )
                Pageable pageable,
                @RequestParam(name = "q" , value="") String nomeAnime ) 
	{
		
		return animeService.buscar( pageable, nomeAnime);
	}
	
	
        
	@GetMapping("api/anime")
	public Page<Anime> listar( 
                
                @PageableDefault( size = 10 )
                Pageable pageable )  
        {
		
            
		return animeService.listar( pageable );
	}
        
        
	
	@DeleteMapping("api/anime/{id}")
	@ApiOperation(
            value = "Deletar um anime"	, 
            authorizations = {@Authorization(value="basicAuth")} ,  
            notes = 
                   "Para deletar um anime basta estar autenticado e ser o dono do anime!"
        )
        
	@ApiResponses( 
        @ApiResponse( code = 404 ,message = "Recurso Não Encontrado") )
	public void deletar( 
                @PathVariable("id") Long id , 
                @ApiParam(hidden=true) Principal principal) 
	{
		animeService.deletar( id , principal.getName() );
	}
	
	
        @ApiOperation(
            value = "Inserir um anime"	, 
            authorizations = {@Authorization(value="basicAuth")} ,  
            notes = 
                   "Para inserir um anime basta que não exista outro com o mesmo nome!"
        )
        @ApiImplicitParams({
            @ApiImplicitParam(
                name = "anime",
                value = "Esse é o minimo necessario para conseguir inserir um anime!",
                required = true,
                dataType = "String",
                paramType = "body",
                example = "{\n \"nome\": \"Naruto\", \n "
                        + "\"resumo\": \"Anime sobre um garoto orfão que quer ser hokage\",\n "
                        + "\"genero\": \"Aventura e Luta\", \n "
                        + "\"descricao\": \"É um anime sobre ninjas\", \n "
                        + "\"autorDaObra\": \"Araki\", \"genero\": \"ação e aventura\" \n}" 
            )
        } )
        
        @PostMapping("api/anime")
	@ResponseStatus(HttpStatus.CREATED)
        public Anime inserir( 
            @RequestBody Anime anime , 
            @ApiParam(hidden=true) Principal principal) {
		
		System.out.println(principal.getName());	
		return animeService.inserir( anime , principal.getName() );
	}
	
	
        
        
        
        
	@ApiOperation(
            value = "Atualizar um Anime",
            notes = "Para atualizar um anime é necessario além de estar autenticado, "
                    + " passar o id pela rota e o objeto anime com o id, é fortemente "
                    + "recomendado utilizar a rota patch para atualizar apenas elementos necessários !" ,
            authorizations = {@Authorization(value="basicAuth")}
			
	)
        
        @PutMapping("api/anime/{id}")
	public Anime atualizar	(
            @PathVariable("id") Long anime_id , 
            @RequestBody Anime anime , 
            @ApiParam(hidden=true)Principal principal ) 
	{
		
 		if( !anime.getId().equals(anime_id)) {
			throw new IdNaoConfereException(); 
		}
		
		return animeService.atualizar( anime , principal.getName() );
	}
	
        
        
        
        
       
	@ApiOperation(
            value = "Atualizar um anime",
            notes = "É possivel atualizar os campos do anime individualmente, desde que seja passado por parametro\n"
                            + "o ID do anime, e caso no corpo da requisição o id também seja passado, o id do corpo deverá\n"
                            + "ser igual o id do parametro na url!",
            authorizations = {@Authorization(value="basicAuth")}
	)
        
	@PatchMapping("api/anime/{id}")
	public Anime atualizarAvancado    ( 
              @PathVariable("id") Long animeId , 
              @RequestBody AnimeDTO animeParameters , 
              @ApiParam( name = "name", hidden=true)   Principal principal	
        ) throws IllegalAccessException, InvocationTargetException 
	{
		
		Anime anime = animeService.pegarUm(animeId) ;
		
		LongIdUtil.validateId(animeParameters.getId(), animeId);
		copyUtils.copyProperties( anime , animeParameters);
		
		
		return animeService.atualizar( anime , principal.getName() );
	}

                                
                                
                                
                                

	@ApiOperation(
            value = "Ver dados de um anime"
	)
        
	@GetMapping("api/anime/{id}")
	public Anime verUm( @PathVariable("id") Long id ) {
		
		return animeService.pegarUm( id );
	}
	
        
        
        
	
	@ApiOperation(
            value = "Listar os personagens de um anime"
	)
        
	@GetMapping("api/anime/{id}/personagem")
	public List<Personagem> verOsPersonagensDoAnime( @PathVariable("id") Long id ) {
		
		Anime anime = animeService.pegarUm( id );
		List<Personagem> personagens = new ArrayList<Personagem>();
		personagens = anime.getPersonagems();

		for( Personagem p : personagens) {
			p.setAnime(null);
		}
		
		return personagens;
	}
	
	@ApiOperation(
			value = "Listar as categorias de um anime",
			authorizations = {@Authorization(value="basicAuth")}
	)
	@GetMapping("api/anime/{id}/categorias")
	public List<Categoria> listarCategoriasDeUmAnime( @PathVariable("id") Long id )  {
		
		return animeService.getCategorias ( id );
	}
	
	
        
        
        
        
	@ApiOperation(
		value = "Registrar um persnoagem no anime",
		authorizations = {@Authorization(value="basicAuth")}
	)        
        @ApiImplicitParams({
            @ApiImplicitParam(
                name = "anime",
                value = "Esse é o minimo necessario para conseguir inserir um personagem em um anime!",
                required = true,
                dataType = "String",
                paramType = "body",
                example = "{\n \"nome\": \"Naruto\", \n "
                        + "\"idade\": \"20\",\n "
                        + "\"historia\": \"Era uma vez...\", \n "
                        + "\"descricao\": \"É um anime sobre ninjas\", \n "
                        + "\"ator\": \"Araki\", \"genero\": \"ação e aventura\" \n}" 
            )
        } )
        
        @PostMapping("api/anime/{id}/registrarPersonagem")
	@ResponseStatus(HttpStatus.CREATED)
	public void registrarPersonagem( 
            @PathVariable("id") Long id,  
            @RequestBody Personagem personagem ) 
	{
		animeService.addPersonagem( id , personagem ) ;
	}
	
        
        
        
	
	@ApiOperation(
		value = "Registrar um comentario no anime",
                notes = "Para registrar um comentario é necessario estar logado! E só quem"
                        + " criou o comentario pode modificar ou deletar ele!",
		authorizations = {@Authorization(value="basicAuth")}
	)
        @ApiImplicitParams({
            @ApiImplicitParam(
                name = "comentario",
                value = "Para inserir um comentario!",
                required = true,
                dataType = "String",
                paramType = "body",
                example = "{\n \"mensagem\": \"Esse anime é legal! \", \n }" 
            )
        } )
        
	@PostMapping("api/anime/{anime_id}/registrarComentario")
	@ResponseStatus(HttpStatus.CREATED)
	public Comentario registrarComentario( 
            @PathVariable("anime_id") Long anime_id,  
            @RequestBody Comentario comentario , 
            Principal principal ) 
	{
			
		return animeService.addComentario( anime_id , comentario , principal.getName()) ;
	}

        
        
        
	@ApiOperation(
            value = "Listar comentarios de um anime",
            notes = "Listar comentarios de um anime"
	)
        
	@GetMapping("api/anime/{anime_id}/comentarios")
	public List<Comentario> pegarComentarios(
		@PathVariable("anime_id") Long anime_id ) 
	{
		return animeService.pegarUm(anime_id).getComentarios();
	}
	
	
	
	
}
