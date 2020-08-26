package br.com.projetofinal.centraldeerros.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String login;
    private String email;
    private LocalDateTime criadoEm;
    private LocalDateTime alteradoEm;

}
