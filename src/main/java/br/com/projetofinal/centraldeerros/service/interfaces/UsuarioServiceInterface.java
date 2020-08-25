package br.com.projetofinal.centraldeerros.service.interfaces;

import br.com.projetofinal.centraldeerros.entity.Usuario;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UsuarioServiceInterface {

    Optional<Usuario> findById(Long id);

    Optional<Usuario> findByLogin(String login);

    Usuario findByEmail(String email);

    List<Usuario> findAll(Pageable pageable);

    Usuario save(Usuario usuario);

}
