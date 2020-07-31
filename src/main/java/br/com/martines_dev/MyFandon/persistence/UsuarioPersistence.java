package br.com.martines_dev.MyFandon.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.martines_dev.MyFandon.domain.Usuario;

@Repository
public interface UsuarioPersistence extends JpaRepository<Usuario,Long> {

}
