package com.coursemanager.domain.service;

import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.coursemanager.domain.exception.CadastroException;
import com.coursemanager.domain.exception.EntidadeNaoEncontradaException;
import com.coursemanager.domain.model.MatriculaEntidade;
import com.coursemanager.domain.model.UsuarioEntidade;
import com.coursemanager.domain.model.UsuarioLogin;
import com.coursemanager.domain.repository.UsuarioRepositorio;


@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepositorio usuarioRepository;
	
	public UsuarioEntidade cadastraUsuario(UsuarioEntidade usuario) throws Exception {
		if(this.usuarioRepository.existsByEmail(usuario.getEmail())) {
			throw new CadastroException("Email já existente, por favor tente outro!");
		}
		if(!this.usuarioRepository.existsByNome(usuario.getNome())) {
			//criptografa a senha do usuário 
			BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
			String senhaEncoder= encoder.encode(usuario.getSenha());
			usuario.setSenha(senhaEncoder);
			return usuarioRepository.save(usuario);
		}else throw new CadastroException("Nome já existente, por favor tente outro!");
	}

	public UsuarioLogin login(UsuarioLogin userParametro) {
		BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
		Optional<UsuarioEntidade> usuario= usuarioRepository.findByNome(userParametro.getNome());
		if(usuario.isPresent()) {
			//verifica se a senha do usuário é igual a senha do banco, faz essa verificação considerando a criptografia
			if(encoder.matches(userParametro.getSenha(), usuario.get().getSenha())) {
				//gero o token do usuário
				String auth= userParametro.getNome() + ":"+ userParametro.getSenha();
				byte[] encondeAuth= Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader= "Basic "+new String(encondeAuth);
				
				return new UsuarioLogin(usuario.get(), authHeader);			
			}else {
				throw new EntidadeNaoEncontradaException("Senha incorreta");
			}
		}else {
			throw new EntidadeNaoEncontradaException("Nome de usuário não encontrado, "
					+ "por favor verifique se o mesmo está correto");
		}
	}
    
	public List<UsuarioEntidade> listaUsuario(){
		return this.usuarioRepository.findAll();
	}

	public UsuarioEntidade getById(long id) {
		Optional<UsuarioEntidade> user= this.usuarioRepository.findById(id);
		if(user.isPresent()) return user.get();
		else throw new EntidadeNaoEncontradaException("id_usuário de usuário não encontrado");
	}
	
	public UsuarioEntidade alteraUsuario(UsuarioEntidade user) throws Exception {
		Optional<UsuarioEntidade> encontrou= usuarioRepository.findByNome(user.getNome());
		if(encontrou.isPresent() && encontrou.get().getId_usuario() != user.getId_usuario()) {
			throw new CadastroException("nome de usuário já existente, por favor tente outro!");
		}
		encontrou= usuarioRepository.findByEmail(user.getEmail());
		if(encontrou.isPresent() && encontrou.get().getId_usuario() != user.getId_usuario()) {
			throw new CadastroException("E-mail já existente, por favor tente outro!");
		}
		
		BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
		String senhaEncoder= encoder.encode(user.getSenha());
		user.setSenha(senhaEncoder);
		return this.usuarioRepository.save(user);
	}
	
	public void deleteUsuario(long id) {
		Optional<UsuarioEntidade> user= this.usuarioRepository.findById(id);
		if(user.isEmpty()) throw new EntidadeNaoEncontradaException("id de usuário não encontrado");
		else this.usuarioRepository.delete(user.get());
	}

	public Set<MatriculaEntidade> getCursoAluno(long id_usuario) {
		UsuarioEntidade user= this.getById(id_usuario);
		return user.getCursos();
	}
	
	public List<MatriculaEntidade> getCursoAlunoFinalizado(long id_usuario) {
		UsuarioEntidade user= this.getById(id_usuario);
		return user.getCursos()
				.stream()
				.filter( matricula -> matricula.isFinalizado())
				.collect(Collectors.toList());
	}

	public Collection<MatriculaEntidade> getCursoAlunoPendente(long id_usuario) {
		UsuarioEntidade user= this.getById(id_usuario);
		return user.getCursos()
				.stream()
				.filter( matricula -> !matricula.isFinalizado())
				.collect(Collectors.toList());
	}
}