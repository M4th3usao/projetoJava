package com.example.projeto;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@ComponentScan("com.sistema.model")
public class ProjetoController {
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
			
	@GetMapping("/produtos")
	public String produtos() {
		return "produtos";
	}
			
	@GetMapping("/sobre")
	public String sobre() {
		return "sobre";
	}
			
	@GetMapping("/contatos")
	public String contatos() {
		return "contatos";
	}
			
	@GetMapping("/conta")
	public String conta() {
		return "conta";
	}
			
	@GetMapping("/carrinho")
	public String carrinho() {
		return "carrinho";
	}
}