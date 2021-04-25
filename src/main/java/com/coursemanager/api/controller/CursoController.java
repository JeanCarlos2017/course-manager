package com.coursemanager.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.coursemanager.domain.model.CursoEntidade;
import com.coursemanager.domain.service.CursoService;

@RestController 
@RequestMapping("/usuario/{id_usuario}/cursos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CursoController {
	@Autowired
	private CursoService cursoService;
	
	@GetMapping
	public ResponseEntity<List<CursoDTO>>listarCursos(){
		return ResponseEntity.ok( UtilsEntidadeToDTO.cursoEntidadeToDTO( this.cursoService.listarCursos() ));
	}
	
	@GetMapping("/buscaPorId/{id_curso}")
	public ResponseEntity<CursoDTO> buscaPorId(@PathVariable long id_curso){
		return ResponseEntity.ok( new CursoDTO( this.cursoService.buscaPorId(id_curso)) );
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<CursoDTO> cadastrarCurso(@Valid @RequestBody CursoEntidade curso){
		return ResponseEntity.status(HttpStatus.CREATED).body(
			  new CursoDTO(	this.cursoService.cadastraCurso(curso)) );
	}
	
	@GetMapping("{id_curso}/busca/alunos")
	public ResponseEntity<List<UsuarioDTO>> buscaAlunosCurso( @PathVariable long id_curso){
		return ResponseEntity.ok( UtilsEntidadeToDTO.getUsuarioDTODeMatriculaEntidade( this.cursoService.getMatricula(id_curso) ));
	}
	
	@PutMapping("/alterar")
	public ResponseEntity<CursoDTO> alterarCurso(@Valid @RequestBody CursoEntidade curso){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(
			  new CursoDTO(	this.cursoService.alteraCurso(curso)) );
	}

}
