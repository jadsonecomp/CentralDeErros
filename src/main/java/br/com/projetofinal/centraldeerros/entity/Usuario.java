package br.com.projetofinal.centraldeerros.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    @NotBlank(message = "Preencha seu nome.")
    @Size(max = 100)
    private String nome;

    @Column
    @NotNull
    @NotBlank(message = "Preencha seu login.")
    @Size(max = 50)
    private String login;

    @Column
    @NotNull
    @NotBlank(message = "Informe sua nome.")
    @Size(max = 255)
    private String senha;

    @Column
    @Email
    @Size(max = 100)
    @NotNull
    @NotBlank(message = "Preencha seu e-mail.")
    private String email;

    @Column
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime criadoEm;

    @Column
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime alteradoEm;


}