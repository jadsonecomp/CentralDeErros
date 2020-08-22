package br.com.projetofinal.centraldeerros.service.impl;

import br.com.projetofinal.centraldeerros.entity.Usuario;
import br.com.projetofinal.centraldeerros.repository.UsuarioRepository;
import br.com.projetofinal.centraldeerros.service.interfaces.UsuarioServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService implements UsuarioServiceInterface {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<Usuario> findById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public Optional<Usuario> findByLogin(String login) {
        return this.repository.findByLogin(login);
    }

    @Override
    public Usuario findByEmail(String email) {
        return this.repository.findByEmail(email);
    }

    @Override
    public Usuario save(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return this.repository.save(usuario);
    }
}
