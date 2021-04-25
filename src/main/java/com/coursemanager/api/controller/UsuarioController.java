package com.coursemanager.api.controller;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coursemanager.domain.dto.CursoDTO;
import com.coursemanager.domain.dto.UsuarioDTO;
import com.coursemanager.domain.dto.UtilsEntidadeToDTO;
import com.coursemanager.domain.model.UsuarioEntidade;
import com.coursemanager.domain.model.UsuarioLogin;
import com.coursemanager.domain.service.UsuarioService;


@RestController 
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@GetMapping
	public String index() {
		return "servidor funcionando!";
	}
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	@GetMapping("/listar")
	public ResponseEntity<List<UsuarioDTO>> getAll(){
		return ResponseEntity.ok(UtilsEntidadeToDTO.usuarioEntidadeToDTO(usuarioService.listaUsuario()));
	}
	
	@GetMapping("/buscaPorId/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable long id){
		return ResponseEntity.ok(new UsuarioDTO(usuarioService.getById(id)));
	}
	@PostMapping("/cadastrar")
	public ResponseEntity<UsuarioDTO> postUsuario(@Valid @RequestBody UsuarioEntidade usuario) throws Exception{
		UsuarioEntidade user = usuarioService.cadastraUsuario(usuario);
		return this.valida(user, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UsuarioLogin> logar(@Valid @RequestBody UsuarioLogin userParam){
		UsuarioLogin respostaLogin= this.usuarioService.login(userParam);
		if (respostaLogin != null) return ResponseEntity.ok(respostaLogin);
		else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<UsuarioDTO> putUsuario(@Valid @RequestBody UsuarioEntidade usuario)
			throws Exception{
		UsuarioEntidade user = usuarioService.alteraUsuario(usuario);
		return this.valida(user, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable long id){
		this.usuarioService.deleteUsuario(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("{id_usuario}/cursos/matriculados")
	public ResponseEntity<Collection<CursoDTO>> getCusosAluno(@PathVariable long id_usuario){
		return ResponseEntity.ok(UtilsEntidadeToDTO.getCursoDTODeMatriculaEntidade
				(this.usuarioService.getCursoAluno(id_usuario)));
	}
	
	@GetMapping("{id_usuario}/cursos/finalizados")
	public ResponseEntity<Collection<CursoDTO>> getCursosFinalizado(@PathVariable long id_usuario){
		return ResponseEntity.ok(UtilsEntidadeToDTO.getCursoDTODeMatriculaEntidade
				(this.usuarioService.getCursoAlunoFinalizado(id_usuario)));
	}
	
	@GetMapping("{id_usuario}/cursos/pendente")
	public ResponseEntity<Collection<CursoDTO>> getCursosPendente(@PathVariable long id_usuario){
		return ResponseEntity.ok(UtilsEntidadeToDTO.getCursoDTODeMatriculaEntidade
				(this.usuarioService.getCursoAlunoPendente(id_usuario)));
	}
	private ResponseEntity<UsuarioDTO> valida(UsuarioEntidade user, HttpStatus status){
		if (user == null) return ResponseEntity.badRequest().build();
		else return ResponseEntity.status(status).body(new UsuarioDTO(user));
	}
}
