package com.coursemanager.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coursemanager.domain.model.CursoEntidade;

public interface CursoRepositorio extends JpaRepository<CursoEntidade, Long> {
	List<CursoEntidade> findByNomeIgnoreCase(String nome);
	Optional<CursoEntidade> existsByNome(String nome); 
}
