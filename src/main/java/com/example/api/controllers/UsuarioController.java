package com.example.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.models.NovaSenha;
import com.example.api.models.entity.Usuario;
import com.example.api.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService _usuarioService) {
		this.usuarioService = _usuarioService;
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getAll(){
		return ResponseEntity.ok().body(usuarioService.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getOne(@PathVariable String id){
		Usuario usuario = usuarioService.findById(id);
		if(usuario!=null) {
			return ResponseEntity.ok().body(usuario);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Usuario> create(@RequestBody Usuario nUsuario){
		return ResponseEntity.ok().body(usuarioService.create(nUsuario));
	}
	
	@PutMapping
	public ResponseEntity<Usuario> update(@RequestBody Usuario usuario){
		usuarioService.update(usuario, usuario.getId());
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> delete(@PathVariable String id){
		if(usuarioService.delete(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PatchMapping("/senha")
	public ResponseEntity<Usuario> updateSenha(@RequestBody NovaSenha senhaInsert){
		if(usuarioService.updateSenha(senhaInsert)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
