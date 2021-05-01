package com.example.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.models.entity.Compra;
import com.example.api.services.CompraService;

@RestController
@RequestMapping("/compra")
public class CompraController {

	@Autowired
	CompraService compraService;
	
	@GetMapping
	public ResponseEntity<List<Compra>> getAll(){
		return ResponseEntity.ok().body(compraService.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Compra> create(@RequestBody Compra compra){
		return ResponseEntity.ok().body(compraService.create(compra));
	}
	
}
