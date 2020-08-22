package br.com.projetofinal.centraldeerros.repository;

import br.com.projetofinal.centraldeerros.entity.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository {

    Optional<Usuario> findById(Long id);

    Usuario findByEmail(String email);

    Optional<Usuario> findByLogin(String login);

}
