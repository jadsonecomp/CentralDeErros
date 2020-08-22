package br.com.projetofinal.centraldeerros.service.interfaces;

import br.com.projetofinal.centraldeerros.entity.Usuario;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UsuarioServiceInterface {

    Optional<Usuario> findById(Long id);

    Optional<Usuario> findByLogin(String login);

    Optional<Usuario> findByEmail(String email);

    Usuario save(Usuario usuario);

}
