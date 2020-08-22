package br.com.projetofinal.centraldeerros.entity;

import br.com.projetofinal.centraldeerros.enums.Level;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Preencha o level do evento.")
    private Level level;

    @Column
    @Size(max = 255)
    @NotNull
    @NotBlank(message = "Preencha a descrição do evento.")
    private String descricao;

    @Column
    @Size(max = 255)
    @NotNull
    @NotBlank(message = "Preencha o log do evento.")
    private String log;

    @Column
    @Size(max = 100)
    @NotNull
    @NotBlank(message = "Preencha a origem do evento.")
    private String origem;

    @Column
    @NotNull
    @Min(value = 0L, message = "Quantidade não pode ser negativa")
    private Long quantidade = 0L;

    @Column
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime data;

    @Column
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime criadoEm;

    @Column
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime alteradoEm;

}
