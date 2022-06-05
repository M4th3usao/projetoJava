package com.example.projeto;




import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/buscar/nome")
	public String buscarNome(@ModelAttribute Cliente cli,  
							 Model model) {
		ClienteService cs = context.getBean(ClienteService.class);
		Map<String, Object> reg = (Map<String, Object>) cs.buscarCliNome(cli.getNome()).get(0);
		return "redirect:/perfil/" + reg.get("id");
	}
	
	@GetMapping("/buscar")
	public String buscarNome( Model model) {
		model.addAttribute("cli", new Cliente());
		return "buscarNome";
	}
			
	@GetMapping("/conta")
	public String conta( Model model) {
		model.addAttribute("cli", new Cliente(0, "", ""));
		return "conta";
	}
	
	@PostMapping("/conta")
	public String sucesso(@ModelAttribute Cliente cli, 
						  Model model) {
		ClienteService cs = context.getBean(ClienteService.class);
		cs.inserirCliente(cli);
		return "sucesso";
	}
			
	@GetMapping("/carrinho")
	public String carrinho() {
		return "carrinho";
	}
	
	@GetMapping("/perfil/{id}")
	public String getPerfil(@PathVariable("id") int id,
							Model model) {
		ClienteService cs = context.getBean(ClienteService.class);
		Map<String, Object> mapa = cs.getCliente(id);
		model.addAttribute("nome", mapa.get("nome"));
		model.addAttribute("cpf", mapa.get("cpf"));
		model.addAttribute("id",id);
		return "perfilCliente";
	}
	
	@GetMapping("/clientes")
	public String listar(Model model) {
		ClienteService cdao = context.getBean(ClienteService.class);
		List<Map<String, Object>> clientes = cdao.getClientes();
		model.addAttribute("clientes", clientes);
		return "listaClientes";
	}
	
	
	@PostMapping("/apagar/cliente/{id}")
	public String apagarCliente(@PathVariable("id") int id) {
		ClienteService cdao = context.getBean(ClienteService.class);
		cdao.deleteCliente(id);
		return "redirect:/clientes";
	}
	
	@GetMapping("/upd/{id}")
	public String formAtualizar(@PathVariable("id") int id, Model model){
		ClienteService cdao = context.getBean(ClienteService.class);
		Map<String, Object> regs = cdao.getCliente(id);
		Cliente cli = new Cliente(id, regs.get("nome").toString(),regs.get("cpf").toString());
		model.addAttribute("conta", cli);
		model.addAttribute("id", id);
		return "atualizarConta";
	}
	
	@PostMapping("/upd/{id}")
	public String atualizarCliente(@PathVariable("id") int id,
									Model model,
									@ModelAttribute Cliente cli) {
		ClienteService cdao = context.getBean(ClienteService.class);
		cdao.atualizarCliente(id, cli);
		return "redirect:/clientes";
	}
	
	
}