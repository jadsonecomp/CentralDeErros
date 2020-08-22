package br.com.projetofinal.centraldeerros.dto;

import br.com.projetofinal.centraldeerros.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventoDTO {

    private Long id;
    private Level level;
    private String descricao;
    private String origem;
    private Long quantidade = 0L;
    private LocalDateTime data;
    private LocalDateTime criadoEm;
    private LocalDateTime alteradoEm;

}
