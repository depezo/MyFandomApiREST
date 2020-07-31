package br.com.martines_dev.MyFandon.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.martines_dev.MyFandon.domain.Categoria;

@Repository
public interface CategoriaPersistence extends JpaRepository<Categoria, Long> {

}
