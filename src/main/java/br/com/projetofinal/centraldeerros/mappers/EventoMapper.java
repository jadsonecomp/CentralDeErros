package br.com.projetofinal.centraldeerros.mappers;

import br.com.projetofinal.centraldeerros.dto.EventoDTO;
import br.com.projetofinal.centraldeerros.entity.Evento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventoMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "level", target = "level"),
            @Mapping(source = "descricao", target = "descricao"),
            @Mapping(source = "origem", target = "origem"),
            @Mapping(source = "quantidade", target = "quantidade"),
            @Mapping(source = "data", target = "data", dateFormat = "yyyy-MM-dd HH:mm"),
            @Mapping(source = "criadoEm", target = "criadoEm", dateFormat = "yyyy-MM-dd HH:mm"),
            @Mapping(source = "alteradoEm", target = "alteradoEm", dateFormat = "yyyy-MM-dd HH:mm")
    })
    EventoDTO map(Evento evento);

    List<EventoDTO> map(List<Evento> EventoList);
}
