package br.com.martines_dev.MyFandon.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.martines_dev.MyFandon.domain.Anime;

@Repository
public interface AnimePersistence extends JpaRepository<Anime,Long> {

	List<Anime> findByNome(String nome);
}
