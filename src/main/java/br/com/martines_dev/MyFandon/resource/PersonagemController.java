package br.com.martines_dev.MyFandon.resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonagemController {

	@DeleteMapping("api/personagem/{id}")
	public void deletarPersonagem( int id ) {
		
	}
	@PostMapping("api/personagem")
	public String inserirPersonagem() {
		return "inserir personagem";
	}
	@PutMapping("api/personagem/{id}")
	public String atualizarPersonagem() {
		return "atualizar personagem";
	}
	@GetMapping("api/personagem/{id}")
	public String verUmPersonagem() {
		return "atualizar personagem";
	}
	@GetMapping("api/personagem")
	public String listarPersonagem() {
		return "atualizar personagem";
	}
}
