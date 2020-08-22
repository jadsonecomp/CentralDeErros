package br.com.projetofinal.centraldeerros.repository;

import br.com.projetofinal.centraldeerros.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findById(Long id);

    Usuario findByEmail(String email);

    Optional<Usuario> findByLogin(String login);

}
