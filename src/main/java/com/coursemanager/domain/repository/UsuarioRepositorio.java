package com.coursemanager.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coursemanager.domain.model.UsuarioEntidade;



@Repository
public interface UsuarioRepositorio extends JpaRepository<UsuarioEntidade, Long> {

	Optional<UsuarioEntidade> findByNome(String userName);
	Optional<UsuarioEntidade> findByEmail(String userName);
	boolean existsByEmail(String email);
	boolean existsByNome(String nome);
}
