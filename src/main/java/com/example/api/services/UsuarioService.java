package com.example.api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.api.models.NovaSenha;
import com.example.api.models.entity.Usuario;
import com.example.api.repository.UsuarioRepository;
import com.example.api.util.StringUtil;

@Component
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario create(Usuario usuario) {
		String cryptoSenha = StringUtil.Cripto(usuario.getSenha());
		usuario.setSenha(cryptoSenha);
		return usuarioRepository.save(usuario);	
	}
	public List<Usuario> findAll(){
		return usuarioRepository.findAll();
	}
	public Usuario findById(String id) {
		Optional<Usuario> response =  usuarioRepository.findById(id);
		if(response.isPresent()) {
			return response.get();
		}
		return null;
	}

	public Usuario update(Usuario usuario,String id) {
		Usuario _usuario = this.findById(id);
		if(_usuario!=null) {
			
			if(usuario.getCpf()!=null) {
				_usuario.setCpf(usuario.getCpf());
			}
			
			if(usuario.getNome()!=null) {
				_usuario.setNome(usuario.getNome());
			}
			
			if(usuario.getTelefone()!=null) {
				_usuario.setTelefone(usuario.getTelefone());
			}
			_usuario.setUpdatedAt(LocalDateTime.now());
			usuarioRepository.save(_usuario);
		}
		return _usuario;
	}
	public boolean updateSenha(NovaSenha inSenha) {
		Usuario response = this.findById(inSenha.getIdUsuario());
		if(response!=null) {
			String senhaCrypto = StringUtil.Cripto(inSenha.getSenhaAtual());
			if(senhaCrypto.equals(response.getSenha())) {
				response.setSenha(StringUtil.Cripto(inSenha.getSenhaNova()));
				response.setUpdatedAt(LocalDateTime.now());
				usuarioRepository.save(response);
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	public boolean delete(String id) {
		Usuario response = this.findById(id);
		if(response!=null) {
			usuarioRepository.delete(response);
			return true;
		}else {
			return false;
		}
	}

}
