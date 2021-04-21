package com.coursemanager.api.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coursemanager.domain.model.CursoEntidade;
import com.coursemanager.domain.service.MatriculaService;

@RestController 
@RequestMapping("/usuario/{id_usuario}/matricula")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MatriculaController {
	@Autowired
	private MatriculaService matriculaService;
	
	@PostMapping("/curso/{id_curso}")
	public ResponseEntity<CursoEntidade> matriculaAluno(@PathVariable long id_usuario, 
			@PathVariable long id_curso) throws Exception{
		return ResponseEntity.ok(this.matriculaService.matriculaAluno(id_usuario, id_curso));
	}
	
	@GetMapping("/cursos/matriculados")
	public ResponseEntity<Set<CursoEntidade>> cursosMatriculadosPorAluno(@PathVariable long id_usuario){
		return ResponseEntity.ok(this.matriculaService.cursosMatriculadosPorAluno(id_usuario));
	}
}
