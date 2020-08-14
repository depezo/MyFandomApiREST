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

@RestController
public class AnimeController {

	
	@Autowired
	private AnimeServiceInterface animeService;

	@Autowired
	private NullAwareBeanUtils copyUtils;
	
	
	@GetMapping("api/anime/buscar")
	public Page<Anime> buscar( Pageable pageable,
								@RequestParam(name = "q" , value="") String nomeAnime ) 
	{
		
		return animeService.buscar( pageable, nomeAnime);
	}
	
	
	@GetMapping("api/anime")
	public Page<Anime> listar( Pageable pageable )  {
		
		return animeService.listar( pageable );
	}
	
	@DeleteMapping("api/anime/{id}")
	public void deletar( @PathVariable("id") Long id , Principal principal) 
	{
		animeService.deletar( id , principal.getName() );
	}
	
	
	@PostMapping("api/anime")
	@ResponseStatus(HttpStatus.CREATED)
	public Anime inserir( @RequestBody Anime anime , Principal principal) {
		
		System.out.println(principal.getName());	
		return animeService.inserir( anime , principal.getName() );
	}
	
	
	@PutMapping("api/anime/{id}")
	public Anime atualizar
				( @PathVariable("id") Long anime_id , 
				  @RequestBody Anime anime , 
				  Principal principal ) 
	{
		
 		if( !anime.getId().equals(anime_id)) {
			throw new IdNaoConfereException(); 
		}
		
		return animeService.atualizar( anime , principal.getName() );
	}
	

	@PatchMapping("api/anime/{id}")
	public Anime atualizarAvancado
				( @PathVariable("id") Long animeId , 
				  @RequestBody AnimeDTO animeParameters , 
				  Principal principal	 ) 
				  throws IllegalAccessException, InvocationTargetException 
	{
		
		Anime anime = animeService.pegarUm(animeId) ;
		
		LongIdUtil.validateId(animeParameters.getId(), animeId);
		copyUtils.copyProperties( anime , animeParameters);
		
		
		return animeService.atualizar( anime , principal.getName() );
	}


	@GetMapping("api/anime/{id}")
	public Anime verUm( @PathVariable("id") Long id ) {
		
		return animeService.pegarUm( id );
	}
	
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
	@GetMapping("api/anime/{id}/categorias")
	public List<Categoria> listarCategoriasDeUmAnime( 
					@PathVariable("id") Long id )  {
		
		return animeService.getCategorias ( id );
	}
	
	

		

	@PostMapping("api/anime/{id}/registrarPersonagem")
	@ResponseStatus(HttpStatus.CREATED)
	public void registrarPersonagem( 
					@PathVariable("id") Long id,  
					@RequestBody Personagem personagem ) 
	{
		animeService.addPersonagem( id , personagem ) ;
	}
	
	
	@PostMapping("api/anime/{id}/registrarComentario")
	@ResponseStatus(HttpStatus.CREATED)
	public Comentario registrarComentario( 
					@PathVariable("id") Long id,  
					@RequestBody Comentario comentario , 
					Principal principal ) 
	{
			
		return animeService.addComentario( id , comentario , principal.getName()) ;
	}

	
	@GetMapping("api/anime/{id}/comentarios")
	public List<Comentario> pegarComentarios(
						@PathVariable("id") Long id ) 
	{
		return animeService.pegarUm(id).getComentarios();
	}
	
//	private AnimeDTO convertToDTO(Anime anime) {
//		AnimeDTO animeDTO = mapper.map(anime, AnimeDTO.class);
//		return animeDTO;
//	}
//	private Anime convertFromDTO(AnimeDTO animeDTO) {
//		Anime anime = mapper.map( animeDTO, Anime.class );
//		return anime;
//	}
//	
//	
	
	
	
	
}
