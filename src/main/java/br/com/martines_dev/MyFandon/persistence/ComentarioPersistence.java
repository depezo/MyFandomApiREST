package br.com.martines_dev.MyFandon.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.martines_dev.MyFandon.domain.Comentario;

@Repository
public interface ComentarioPersistence  extends JpaRepository<Comentario, Long>{

}
