package com.example.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.api.models.Email;
import com.example.api.models.entity.Compra;
import com.example.api.models.entity.Product;
import com.example.api.models.entity.Usuario;
import com.example.api.repository.CompraRepository;

@Component
public class CompraService {
	
	@Autowired
	ProdutoService produtoService;
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	CompraRepository compraRepository;
	@Autowired
	EmailService emailService;
	
	public Compra create(Compra compra) {
		Product produtoContext = produtoService.findById(compra.getIdProduto());
		Usuario usuarioContext = usuarioService.findById(compra.getIdUsuario());
		if(produtoContext!=null && usuarioContext!=null && compra.getQuantidade()>0 && compra.getValorPagar()!=0 && compra.getQuantidade()<=produtoContext.getQuantidade()) {
			float valorPagar = calValuePay(produtoContext.getId(), compra.getQuantidade());
			if(valorPagar!=0) {
				compra.setValorPagar(valorPagar);
				if(this.canPurchase(compra.getValorPagar(),compra.getValorPago())) {
					compra.setTroco(compra.getValorPago()- compra.getValorPagar());
					if(produtoService.subQtd(compra.getQuantidade(),compra.getIdProduto())) {
						this.sendEmail(usuarioContext,produtoContext);
						return compraRepository.save(compra);
					};
					
				}
			}
		}
		return null;
	}
	
	private float calValuePay(String idProduct,int qtd) {
		Product product = produtoService.findById(idProduct);
		if(product!=null && qtd>0) {
			return product.getPreco()*qtd;
		}
		return 0.0f;
	}
	private boolean canPurchase(float valorPagar,float valorPago) {
		if(valorPago-valorPagar<0) {
			return false;
		}else {
			return true;
		}
	}
	private void sendEmail(Usuario usuario, Product produto) {
		Email mail = new Email();
		mail.setMailTo(usuario.getEmail());
		mail.setSender("local_server@teste.com");
		mail.setTitle("Nova Compra");
		mail.setMenssage("Você comprou um novo produto com o nome : "+produto.getNome()+" com preço de : "+produto.getPreco());
		emailService.sendEmail(mail);
	}
	
	public List<Compra> findAll() {
		return compraRepository.findAll();
	}
	
}
