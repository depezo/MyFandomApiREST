package br.com.martines_dev.MyFandon.resource;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.martines_dev.MyFandon.domain.Comentario;
import br.com.martines_dev.MyFandon.service.interfaces.ComentarioServicoInterface;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@RestController
public class ComentarioController {

	@Autowired
	ComentarioServicoInterface comentarioService;
	
        
        
         @ApiOperation(
                value = "Deletar um comentario por id!",
		notes = "Para poder deletar um comentario por id é necessario que o cliente"
                        + "seja dono do comentario!",
                authorizations = {@Authorization(value="basicAuth")}
	)
        @ApiResponses( 
            @ApiResponse( code = 404 ,message = "Recurso Não Encontrado") 
        )
        
	@DeleteMapping("api/comentario/{id}")
	@ResponseStatus( code = HttpStatus.NO_CONTENT )
	public void deletar( 
					@PathVariable Long id , 
					Principal principal ) 
	{
		
		comentarioService.deletar( id , principal.getName() );
	}
	
	@ApiOperation(
                value = "Atualizar um comentario por id!",
		notes = "Para poder atualizar um comentario por id é necessario que o cliente"
                        + "seja dono do comentario!",
                authorizations = {@Authorization(value="basicAuth")}
	)
        
	@PutMapping("api/comentario/{id}")
	public Comentario atualizar( 
            @RequestBody Comentario categoria ,
            @PathVariable Long id ,
            Principal principal) 
	{
		
		return comentarioService.atualizar(categoria, id , principal.getName() );
	}
	
        
        
        @ApiOperation(
                value = "Ver um comentario por id!",
		notes = "Ver um comentario por ID"	)
	@GetMapping("api/comentario/{id}")
	public Comentario verUma( @PathVariable Long id ) {
		
		return comentarioService.pegarUm( id );
	}
        
        
        @ApiOperation(
                value = "Listar cometarios",
		notes = "Receber uma [] listagem de comentarios."
	)
        
	@GetMapping("api/comentario")
	public List<Comentario> listar() {
		return comentarioService.listar();
	}
	
}
