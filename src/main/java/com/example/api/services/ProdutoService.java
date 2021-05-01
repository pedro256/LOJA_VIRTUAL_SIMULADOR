package com.example.api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.api.models.entity.Product;
import com.example.api.repository.ProductRepository;


@Component
public class ProdutoService {
	
	@Autowired
	ProductRepository productRepository;
	
	public ProdutoService() {
		
	}
	
	public Product create(Product produto) {
		return productRepository.save(produto);
	}
	public Product update(Product _produto, String id) {
		Product produto = this.findById(id);
		if(produto!=null) {
			if(!_produto.getNome().isEmpty()) {
				produto.setNome(_produto.getNome());
			}
			if(_produto.getPreco()>0) {
				produto.setPreco(_produto.getPreco());
			}
			if(_produto.getQuantidade()>0) {
				produto.setQuantidade(_produto.getQuantidade());
			}
			_produto.setUpdatedAt(LocalDateTime.now());
			return productRepository.save(_produto);
		}
		return null;
	}
	public boolean updateName(String name, String id) {
		Product product = this.findById(id);
		if(product!=null && !name.isEmpty()) {
			product.setNome(name);
			product.setUpdatedAt(LocalDateTime.now());
			productRepository.save(product);
			return true;
		}
		return false;
	}
	public boolean subQtd(int qtd, String id) {
		Product product = this.findById(id);
		if(product!=null && qtd>0) {
			int value = product.getQuantidade()-qtd;
			if(value<0) {
				return false;
			}else {
				product.setQuantidade(value);
				product.setUpdatedAt(LocalDateTime.now());
				productRepository.save(product);
				return true;
			}
			
		}
		return false;
	}
	public boolean updateQtd(int qtd, String id) {
		Product product = this.findById(id);
		if(product!=null && qtd>=0) {
			product.setQuantidade(qtd);
			product.setUpdatedAt(LocalDateTime.now());
			productRepository.save(product);
			return true;
		}
		return false;
	}
	public boolean updatePreco(float preco, String id) {
		Product product = this.findById(id);
		if(product!=null && preco>0) {
			product.setPreco(preco);
			product.setUpdatedAt(LocalDateTime.now());
			productRepository.save(product);
			return true;
		}
		return false;
	}
	public Product findById(String id) {
		Optional<Product> result = productRepository.findById(id);
		return result.get();
	}
	public List<Product> findAll(){
		return productRepository.findAll();
	}
	public boolean delete(String id) {
		Product response  = this.findById(id);
		if(response!=null) {
			productRepository.delete(response);
			return true;
		}else {
			return false;
		}
	}
	
	//find by qtd, name, preco, codigo
	
	

}
