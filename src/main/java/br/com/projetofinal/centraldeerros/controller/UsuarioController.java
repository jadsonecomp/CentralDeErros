package br.com.projetofinal.centraldeerros.controller;

import br.com.projetofinal.centraldeerros.exceptions.ErroInternoServidorException;
import br.com.projetofinal.centraldeerros.exceptions.RecursoNaoEncontradoException;
import br.com.projetofinal.centraldeerros.dto.UsuarioDTO;
import br.com.projetofinal.centraldeerros.entity.Usuario;
import br.com.projetofinal.centraldeerros.mappers.UsuarioMapper;
import br.com.projetofinal.centraldeerros.service.impl.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private UsuarioService usuarioService;

    private final String MENSAGEMERROR = ": Verifique os dados informados, " +
                                         "principalmente os dados de login e e-mail que " +
                                         "já podem estar sendo utilizados pelo sistema";


    @GetMapping("/lista")
    @ApiOperation("Busca todos os Usuários cadastrados")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Usuários não encontrados"),
            @ApiResponse(code = 403, message = "Você não possui permissão para visualizar os usuários"),
            @ApiResponse(code = 401, message = "Você não possui credenciais de autenticação válidas"),
            @ApiResponse(code = 200, message = "Usuários encontrados com sucesso")
    })
    public ResponseEntity findAll(Pageable pageable) {
        try{
            return new ResponseEntity(usuarioMapper.map(this.usuarioService.findAll(pageable)), HttpStatus.OK);
        } catch (Exception e) {
            throw new ErroInternoServidorException(e.getCause().getMessage() + MENSAGEMERROR);
        }
    }


    @ApiOperation(value = "Cria uma conta de usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuário criado com sucesso!"),
            @ApiResponse(code = 409, message = "Dados de Login ou e-mail já cadastrados"),
            @ApiResponse(code = 500, message = "Erro interno, não foi possível completar a requisição")
    })
    @PostMapping(produces = "application/json")
    public ResponseEntity<UsuarioDTO> save(@Valid @RequestBody Usuario usuario){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioMapper.map(usuarioService.save(usuario)));
        } catch (Exception e) {
            throw new ErroInternoServidorException(e.getCause().getMessage() + MENSAGEMERROR);
        }
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "Busca o usuário pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuário encontrado com sucesso"),
            @ApiResponse(code = 401, message = "Você não possui credenciais de autenticação válidas"),
            @ApiResponse(code = 403, message = "Você não possui permissão para buscar usuários"),
            @ApiResponse(code = 404, message = "Usuário não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno, não foi possível completar a requisição")
    })
    public ResponseEntity<UsuarioDTO> findById(@PathVariable(value = "id") Long id) {

        Optional<Usuario> account = usuarioService.findById(id);
        if (account.isPresent()) {
            try{
                return new ResponseEntity<UsuarioDTO>(usuarioMapper.map(account.get()), HttpStatus.OK);
            } catch (Exception e) {
                throw new ErroInternoServidorException(e.getCause().getMessage() + MENSAGEMERROR);
            }
        }else{
            throw new RecursoNaoEncontradoException("Usuário de id "+ id);
        }

    }


    @GetMapping("/login/{login}")
    @ApiOperation(value = "Busca o usuário pelo login")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuário encontrado com sucesso"),
            @ApiResponse(code = 401, message = "Você não possui credenciais de autenticação válidas"),
            @ApiResponse(code = 403, message = "Você não possui permissão para buscar usuários"),
            @ApiResponse(code = 404, message = "Usuário não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno, não foi possível completar a requisição")
    })
    public ResponseEntity<UsuarioDTO> findByLogin(@PathVariable(value = "login") String login) {

        Optional<Usuario> account = usuarioService.findByLogin(login);
        if (account.isPresent()) {
            try{
                return new ResponseEntity<UsuarioDTO>(usuarioMapper.map(account.get()), HttpStatus.OK);
            } catch (Exception e) {
                throw new ErroInternoServidorException(e.getCause().getMessage() + MENSAGEMERROR);
            }
        }else{
            throw new RecursoNaoEncontradoException("Usuário de login "+ login);
        }

    }


    @GetMapping("/email/{email}")
    @ApiOperation(value = "Busca o usuário pelo e-mail")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuário encontrado com sucesso"),
            @ApiResponse(code = 401, message = "Você não possui credenciais de autenticação válidas"),
            @ApiResponse(code = 403, message = "Você não possui permissão para buscar usuários"),
            @ApiResponse(code = 404, message = "Usuário não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno, não foi possível completar a requisição")
    })
    public ResponseEntity<UsuarioDTO> findByEmail(@PathVariable(value = "email") String email) {

        Optional<Usuario> account = Optional.ofNullable(usuarioService.findByEmail(email));
        if (account.isPresent()) {
            try{
                return new ResponseEntity<UsuarioDTO>(usuarioMapper.map(account.get()), HttpStatus.OK);
            } catch (Exception e) {
                throw new ErroInternoServidorException(e.getCause().getMessage() + MENSAGEMERROR);
            }
        }else{
            throw new RecursoNaoEncontradoException("Usuário de email "+ email);
        }

    }


    @ApiOperation(value = "Atualiza as informações do usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuário atualizado"),
            @ApiResponse(code = 401, message = "Você não possui credenciais de autenticação válidas"),
            @ApiResponse(code = 403, message = "Você não possui permissão para buscar usuários"),
            @ApiResponse(code = 404, message = "Usuário não encontrado!"),
            @ApiResponse(code = 500, message = "Erro interno, não foi possível completar a requisição")
    })
    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Usuario> updateUser(@Valid @RequestBody Usuario userAtualizacao, @PathVariable(value = "id") Long id) {

        Optional<Usuario> user = usuarioService.findById(id);
        if (user.isPresent()) {
            try {
                userAtualizacao.setId(user.get().getId());
                userAtualizacao.setCriadoEm(user.get().getCriadoEm());
                return new ResponseEntity<Usuario>(usuarioService.save(userAtualizacao), HttpStatus.OK);
            } catch (Exception e) {
                throw new ErroInternoServidorException(e.getCause().getMessage() + MENSAGEMERROR);
            }
        }else{
            throw new RecursoNaoEncontradoException("Usuário de id "+ id);
        }

    }

}
