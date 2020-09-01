package br.com.projetofinal.centraldeerros.controller;

import br.com.projetofinal.centraldeerros.dto.EventoDTO;
import br.com.projetofinal.centraldeerros.entity.Evento;
import br.com.projetofinal.centraldeerros.exceptions.ErroInternoServidorException;
import br.com.projetofinal.centraldeerros.exceptions.RecursoNaoEncontradoException;
import br.com.projetofinal.centraldeerros.mappers.EventoMapper;
import br.com.projetofinal.centraldeerros.rules.DataEventoRule;
import br.com.projetofinal.centraldeerros.rules.EnumEventoRule;
import br.com.projetofinal.centraldeerros.rules.LongEventoRule;
import br.com.projetofinal.centraldeerros.rules.StringEventoRule;
import br.com.projetofinal.centraldeerros.service.impl.EventoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/evento")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    private EventoMapper eventoMapper = Mappers.getMapper(EventoMapper.class);

    private final String MENSAGEMERROR = ": Verifique os dados informados";



    @GetMapping(produces = "application/json")
    @ApiOperation("Lista todos os eventos cadastrados")
    @ApiResponses(value = {@ApiResponse(code = 200, message= "Eventos listados com sucesso"),
            @ApiResponse(code = 500, message = "Um erro interno ocorreu, não vai ser possível processar sua requisição")
    })
    public List<EventoDTO> findAll(@ApiParam @RequestParam(required = false) String level,
                                   @ApiParam  @RequestParam(required =false) String descricao,
                                   @ApiParam  @RequestParam(required =false) String origem,
                                   @ApiParam  @RequestParam(required =false) String log,
                                   @ApiParam  @RequestParam(required =false)  Long  quantidade,
                                   @ApiParam  @RequestParam(required =false) LocalDateTime data,
                                   Pageable pageable) {

        Specification<Evento> specifications = Specification.where(new EnumEventoRule("level", level))
                .and(new StringEventoRule("log", log))
                .and(new StringEventoRule("origem", origem))
                .and(new StringEventoRule("descricao", descricao))
                .and(new LongEventoRule("quantidade", quantidade))
                .and(new DataEventoRule("data", data));
        try{
            return eventoMapper.map(eventoService.findAll(specifications,pageable));
        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Evento(s) não localizados");
        }
    }



    @PostMapping(produces = "application/json", consumes = "application/json")
    @ApiOperation("Cria um novo evento")
    @ApiResponses(value = {@ApiResponse(code = 201, message= "Evento criado com sucesso"),
                           @ApiResponse(code = 401, message = "Usuário não possui credenciais de autenticação válidas"),
                           @ApiResponse(code = 403, message = "Usuário não possui permissão para criar este evento"),
                           @ApiResponse(code = 500, message = "Um erro interno ocorreu, não vai ser possível processar sua requisição")
    })
    public ResponseEntity<Evento> create(@Valid @RequestBody Evento evento){
        try{
            return new ResponseEntity<Evento>(this.eventoService.save(evento), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ErroInternoServidorException(e.getCause().getMessage() + MENSAGEMERROR);
        }
    }



    @PutMapping
    @ApiOperation("Atualiza um evento")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Sucesso ao atualizar o evento"),
            @ApiResponse(code = 404, message = "Evento não encontrado")
    })
    public ResponseEntity<Evento> update(@Valid @RequestBody Evento evento) {
        try{
            return new ResponseEntity<Evento>(this.eventoService.save(evento), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new ErroInternoServidorException(e.getCause().getMessage() + MENSAGEMERROR);
        }
    }



    @ApiOperation("Deleta um evento")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Sucesso ao deletar o evento"),
            @ApiResponse(code = 404, message = "Evento não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEvent(@PathVariable Long id) {
        try {
            this.eventoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Evento de id "+ id);
        }
    }



    @GetMapping(value= "/{id}", produces = "application/json")
    @ApiOperation("Mostra um evento pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso ao encontrar o evento"),
            @ApiResponse(code = 404, message = "Evento não encontrado")
    })
    public ResponseEntity<Evento> findById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<Evento>(this.eventoService.findById(id).get(), HttpStatus.ACCEPTED);
        } catch (Exception e){
            throw new RecursoNaoEncontradoException("Evento de id "+ id);
        }
    }

}
