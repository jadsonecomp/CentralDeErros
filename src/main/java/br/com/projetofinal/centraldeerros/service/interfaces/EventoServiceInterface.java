package br.com.projetofinal.centraldeerros.service.interfaces;

import br.com.projetofinal.centraldeerros.entity.Evento;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface EventoServiceInterface {

    Optional<Evento> findById(Long id);

    List<Evento> findAll(Specification<Evento> specification, Pageable pageable);

    Evento save(Evento evento);

    void deleteById(Long id);

}
