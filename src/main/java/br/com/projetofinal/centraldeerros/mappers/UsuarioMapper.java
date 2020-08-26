package br.com.projetofinal.centraldeerros.mappers;

import br.com.projetofinal.centraldeerros.dto.UsuarioDTO;
import br.com.projetofinal.centraldeerros.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "nome", target = "nome"),
        @Mapping(source = "login", target = "login"),
        @Mapping(source = "email", target = "email"),
        @Mapping(source = "criadoEm", target = "criadoEm", dateFormat = "yyyy-MM-dd HH:mm"),
        @Mapping(source = "alteradoEm", target = "alteradoEm", dateFormat = "yyyy-MM-dd HH:mm")
    })
    UsuarioDTO  map(Usuario usuario);

    List<UsuarioDTO> map(List<Usuario> usuarioList);

}
