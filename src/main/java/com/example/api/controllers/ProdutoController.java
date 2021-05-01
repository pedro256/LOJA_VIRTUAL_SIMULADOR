package com.example.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.models.entity.Product;
import com.example.api.services.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity<List<Product>> getAll(){
		return ResponseEntity.ok().body(produtoService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getOne(@PathVariable String id){
		Product produto = produtoService.findById(id);
		if(produto!=null) {
			return ResponseEntity.ok().body(produto);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Product> create(@RequestBody Product nProduto){
		return ResponseEntity.ok().body(produtoService.create(nProduto));
	}
	
	@PutMapping
	public ResponseEntity<Product> update(@RequestBody Product product){
		produtoService.update(product, product.getId());
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Product> delete(@PathVariable String id){
		if(produtoService.delete(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	
}

