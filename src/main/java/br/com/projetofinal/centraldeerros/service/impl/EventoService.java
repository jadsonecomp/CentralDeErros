package br.com.projetofinal.centraldeerros.service.impl;

import br.com.projetofinal.centraldeerros.entity.Evento;
import br.com.projetofinal.centraldeerros.repository.EventoRepository;
import br.com.projetofinal.centraldeerros.service.interfaces.EventoServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventoService implements EventoServiceInterface {

    @Autowired
    private EventoRepository repository;

    @Override
    public Optional<Evento> findById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public List<Evento> findAll(Specification<Evento> specification, Pageable pageable) {
        return this.repository.findAll(specification, pageable).getContent();
    }

    @Override
    public Evento save(Evento evento) {
        return this.repository.save(evento);
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }
}
