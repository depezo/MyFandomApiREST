package br.com.martines_dev.MyFandon.persistence;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.martines_dev.MyFandon.domain.Personagem;

@Repository
public interface PersonagemPersistence extends JpaRepository<Personagem, Long> {
	
	Optional<Personagem> findByNome(String nome);
	
	Page<Personagem> findByNomeContainingIgnoreCase(Pageable pageable, String nome);
}
