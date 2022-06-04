package com.example.model;

import org.springframework.beans.factory.annotation.Autowired;

public class ClienteService {
	
	@Autowired
	ClienteDAO cdao;
	
	public void inserirCliente(Cliente c) {
		cdao.inserirCliente(c);
	}

}
