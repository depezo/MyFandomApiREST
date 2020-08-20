package br.com.martines_dev.MyFandon.resource;

import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
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

import com.sun.istack.NotNull;

import br.com.martines_dev.MyFandon.domain.Comentario;
import br.com.martines_dev.MyFandon.domain.Personagem;
import br.com.martines_dev.MyFandon.resource.interfaces.CrudController;
import br.com.martines_dev.MyFandon.service.interfaces.PersonagemServiceInterface;
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
public class PersonagemController extends CrudController<Personagem, Long> {

	@Autowired
	PersonagemServiceInterface personagemService;

	
	@Autowired
	private NullAwareBeanUtils copyUtils;
	
        

          
        @ApiImplicitParams({
            @ApiImplicitParam(
                name = "personagem",
                value = "Esse é o minimo necessario para conseguir inserir um personagem em um anime!",
                required = true,
                dataType = "String",
                paramType = "body",
                example = "{\n \"nome\": \"Naruto\", \n "
                        + "\"idade\": \"20\",\n "
                        + "\"historia\": \"Era uma vez...\", \n "
                        + "\"descricao\": \"É um anime sobre ninjas\", \n "
                        + "\"ator\": \"O nome do dublador!\", \"genero\": \"ação e aventura\" \n}" 
            )
        } )
        @ApiOperation(
                value = "Inserir um personagem em um anime" ,
		notes = "Registrar um persnoagem no anime pela rota personagem"
                        + ", atenção aqui o personagem é criado e inserido para o anime"
                        + ", se você passar o id como parametro vai receber um 404, "
                        + "se o anime não existir, também receberá um 404",
		authorizations = {@Authorization(value="basicAuth")}
	)

	@PostMapping("api/personagem/{id_anime}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Personagem inserir(
                
                @RequestBody Personagem personagem, 
                @PathVariable("id_anime") Long idAnime) {

            
            
		return personagemService.inserir(personagem, idAnime);
	}


         
         
         
        
         
         
         @ApiImplicitParams({
            @ApiImplicitParam(
                name = "personagem",
                value = "Esse é o minimo necessario para conseguir inserir um personagem em um anime!",
                required = true,
                dataType = "String",
                paramType = "body",
                example = "{\n \"nome\": \"Naruto\", \n "
                        + "\"idade\": \"20\",\n "
                        + "\"historia\": \"Era uma vez...\", \n "
                        + "\"descricao\": \"É um anime sobre ninjas\", \n "
                        + "\"ator\": \"O nome do dublador!\", \"genero\": \"ação e aventura\" \n}" 
            )
        } )
        @ApiOperation(
                value = "Atualizar personagem",
		notes = "Atualizar no anime pela rota personagem"
                        + ", atenção aqui o personagem é criado e inserido para o anime"
                        + ", se você passar o id como parametro vai receber um 404, "
                        + "se o anime não existir, também receberá um 404",
		authorizations = {@Authorization(value="basicAuth")}
	)     
         
	@PutMapping("api/personagem/{anime_id}")
	public Personagem atualizar(
                @RequestBody Personagem personagem,
                @PathVariable("anime_id") Long idAnime) {
		return personagemService.atualizar(personagem, idAnime);
	}
	
	
        
        
        @ApiOperation(
                value = "Atualizar um personagem",
		notes = "Para atualizar um personagem basta passar o id na url"
                        + "e o item que quer atualizar",
		authorizations = {@Authorization(value="basicAuth")}
	)
        @ApiImplicitParams({
            @ApiImplicitParam(
                name = "personagem",
                value = "Atualizar só o nome por exemplo",
                required = true,
                dataType = "String",
                paramType = "body",
                example = "{\n \"nome\": \"Naruto\" \n}" 
            )
        } )
        
	@PatchMapping("api/personagem/{personagem_id}")
	public Personagem atualizarAvancado(
                    @PathVariable("personagem_id") Long personagemID, 
                    @RequestBody Personagem personagem,
                    @ApiParam(hidden=true) Principal principal)
                    throws IllegalAccessException, InvocationTargetException 
	{
		
		Personagem personagemPersistido = personagemService.pegarUm(personagemID);

		LongIdUtil.validateId(personagemPersistido.getId(), personagemID);
                
                /* copia do personagem para o personagem persistido os atributos não nulos*/
		copyUtils.copyProperties(personagemPersistido , personagem);
		
		return personagemService.atualizar(personagemPersistido, personagemID );
	}	

        
        
        
	
        @ApiOperation(
                value = "Pegar um personagem por id",
		notes = "Pegar um personagem por id!"
	)
        @ApiResponses( 
            @ApiResponse( code = 404 ,message = "Recurso Não Encontrado") 
        )
        
        @GetMapping("api/personagem/{personagem_id}")
	public Personagem pegarUm(@PathVariable("personagem_id") Long id) {

		return personagemService.pegarUm(id);
	}

        
        
        
	@DeleteMapping("api/personagem/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
        @ApiOperation(
                value = "Deletar um personagem por id!",
		notes = "Delete um personagem por id",
                authorizations = {@Authorization(value="basicAuth")}
	)
        
        @ApiResponses( 
            @ApiResponse( code = 404 ,message = "Recurso Não Encontrado") 
        )
	public void deletar(@PathVariable("id") Long id) {

		personagemService.deletar(id);
	}

        
        @ApiOperation(
            value = "Pegar um personagem por id",
            notes = "Pegar um personagem por id! Na rota pode receber um parametro page "
                    + "exemplo /api/personagem?page=1 que determina que o cliente está na"
                    + "pagina 1"
	)
        
	@GetMapping("api/personagem")
	public Page<Personagem> listar(
                @RequestParam(name = "page", 
                defaultValue = "0") int page)
        {
            
		return personagemService.listar(page);
	}
        
        
        
        @ApiOperation(
           value = "Buscar um personagem",
            notes = "Buscar um personagem pelo parametro q"
	)

	@GetMapping("api/personagem/buscar")
	public Page<Personagem> buscar(
                
                @PageableDefault( size = 10 )
                Pageable pageable, 
                @RequestParam(name = "q", value = "") String nomePersonagem) {
		return personagemService.buscar(pageable, nomePersonagem);
	}

        
        
        @ApiOperation(
           value = "Registrar comentario",
            notes = "Registrar um comentario no personagem!",
            authorizations = {@Authorization(value="basicAuth")}
	)
        
	@PostMapping("api/personagem/{personagem_id}/registrarComentario")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void registrarComentario(
                @PathVariable("personagem_id") Long id, 
                @RequestBody Comentario comentario,
                @ApiParam(hidden=true) Principal principal) {

		personagemService.addComentario(id, comentario, principal.getName());
	}

        
        
        @ApiOperation(
           value = "Ver comentarios do personagem",
            notes = "Receber uma [] lista de comentarios sobre um personagem"
	)
        
	@GetMapping("api/personagem/{id_personagem}/comentarios")
	public List<Comentario> pegarComentarios(@NotNull @PathVariable Long id_personagem) {

		return personagemService.getComentarios(id_personagem);
	}

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
	/** abaixo não vale a pena olhar */

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
