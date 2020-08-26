package br.com.projetofinal.centraldeerros.service.impl;

import br.com.projetofinal.centraldeerros.entity.Evento;
import br.com.projetofinal.centraldeerros.entity.Usuario;
import br.com.projetofinal.centraldeerros.repository.UsuarioRepository;
import br.com.projetofinal.centraldeerros.service.interfaces.UsuarioServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService implements UsuarioServiceInterface, UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<Usuario> findById(Long id) {
        return this.usuarioRepository.findById(id);
    }

    @Override
    public Optional<Usuario> findByLogin(String login) {
        return this.usuarioRepository.findByLogin(login);
    }

    @Override
    public Usuario findByEmail(String email) {
        return this.usuarioRepository.findByEmail(email);
    }

    @Override
    public List<Usuario> findAll(Pageable pageable) {
        return this.usuarioRepository.findAll(pageable).getContent();
    }

    @Override
    public Usuario save(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return this.usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return this.usuarioRepository.findByLogin(login).get();
    }
}
