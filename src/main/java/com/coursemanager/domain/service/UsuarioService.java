package com.coursemanager.domain.service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.coursemanager.domain.exception.CadastroException;
import com.coursemanager.domain.exception.EntidadeNaoEncontradaException;
import com.coursemanager.domain.model.UsuarioEntidade;
import com.coursemanager.domain.model.UsuarioLogin;
import com.coursemanager.domain.repository.UsuarioRepositorio;


@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepositorio usuarioRepository;
	
	
	private boolean validaNome(String nome) {
		//verifica se já existe um usuário com esse nome
		Optional<UsuarioEntidade> encontrou= usuarioRepository.findByNome(nome);
		if(encontrou.isEmpty()) return true;
		else throw new EntidadeNaoEncontradaException("Nome de usuário já está em uso, por favor escolha outro");
		
	}

	public UsuarioEntidade cadastraUsuario(UsuarioEntidade usuario) throws Exception {
		//verifico se o emial já existe 
		if(this.usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
			throw new CadastroException("Email já existente, por favor tente outro!");
		}
		// nome sao válidos 
		if(this.validaNome(usuario.getNome())) {
			//criptografa a senha do usuário 
			BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
			String senhaEncoder= encoder.encode(usuario.getSenha());
			usuario.setSenha(senhaEncoder);
			//por fim salva o usuário
			return usuarioRepository.save(usuario);
		}else return null;
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
	
	public UsuarioEntidade alteraUsuario(UsuarioEntidade user, long id_usuario) throws Exception {
		Optional<UsuarioEntidade> encontrou= usuarioRepository.findByNome(user.getNome());
		//para garantir que será passado o id usuário
		user.setId_usuario(id_usuario);
		if(encontrou.isPresent() && encontrou.get().getId_usuario() != user.getId_usuario()) {
			//está tentando mudar o nome para um usuário que já existe 
			throw new CadastroException("nome de usuário já existente, por favor tente outro!");
		}
		//verifica email
		encontrou= usuarioRepository.findByEmail(user.getEmail());
		if(encontrou.isPresent() && encontrou.get().getId_usuario() != user.getId_usuario()) {
			//está tentando mudar o email para um email que já existe em outro usuário
			throw new CadastroException("E-mail já existente, por favor tente outro!");
		}
		
		//passou nas verificações então começo o processo para salvar o usuário 
		//criptografa a senha do usuário 
		BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
		String senhaEncoder= encoder.encode(user.getSenha());
		user.setSenha(senhaEncoder);
		//por fim salva o usuário
		return this.usuarioRepository.save(user);
	}
	
	public void deleteUsuario(long id) {
		Optional<UsuarioEntidade> user= this.usuarioRepository.findById(id);
		if(user.isEmpty()) throw new EntidadeNaoEncontradaException("id de usuário não encontrado");
		else this.usuarioRepository.delete(user.get());
	}
	

	
}