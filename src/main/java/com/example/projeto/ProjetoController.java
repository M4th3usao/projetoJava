package com.example.projeto;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.model.Cliente;
import com.example.model.ClienteService;


@Controller
@ComponentScan("com.example.model")
public class ProjetoController {
	
	@Autowired
	private ApplicationContext context;
	
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
	public String conta( Model model) {
		model.addAttribute("cli", new Cliente(0, "", ""));
		return "conta";
	}
	
	@PostMapping("/conta")
	public String sucesso(@ModelAttribute Cliente cli) {
		ClienteService cs = context.getBean(ClienteService.class);
		cs.inserirCliente(cli);
		return "sucesso";
	}
			
	@GetMapping("/carrinho")
	public String carrinho() {
		return "carrinho";
	}
}