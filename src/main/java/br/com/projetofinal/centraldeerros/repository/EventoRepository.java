package br.com.projetofinal.centraldeerros.repository;

import br.com.projetofinal.centraldeerros.entity.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    Page<Evento> findAll(Specification<Evento> specification, Pageable pageable);

}
