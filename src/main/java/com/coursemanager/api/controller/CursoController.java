package com.coursemanager.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coursemanager.domain.model.CursoEntidade;
import com.coursemanager.domain.service.CursoService;

@RestController 
@RequestMapping("/usuario/{id_usuario}/cursos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CursoController {
	@Autowired
	private CursoService cursoService;
	
	@GetMapping
	public ResponseEntity<List<CursoEntidade>>listarCursos(){
		return ResponseEntity.ok(this.cursoService.listarCursos());
	}
	
	@GetMapping("/buscaPorId/{id_curso}")
	public ResponseEntity<CursoEntidade> buscaPorId(long id_curso){
		return ResponseEntity.ok(this.cursoService.buscaPorId(id_curso));
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<CursoEntidade> cadastrarCurso(@Valid @RequestBody CursoEntidade curso){
		return ResponseEntity.status(HttpStatus.CREATED).body(this.cursoService.cadastraCurso(curso));
	}
	


}
