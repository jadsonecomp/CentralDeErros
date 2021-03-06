package br.com.projetofinal.centraldeerros.rules;

import br.com.projetofinal.centraldeerros.entity.Evento;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;

public class DataEventoRule implements Specification<Evento> {

    private String colunaNome;
    private LocalDateTime colunaValor;


    public DataEventoRule(String name, LocalDateTime value) {
        this.colunaNome = name;
        this.colunaValor = value;
    }

    @Override
    public Predicate toPredicate(Root<Evento> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (this.colunaValor == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        return criteriaBuilder.equal(root.get(this.colunaNome), this.colunaValor);
    }

}
