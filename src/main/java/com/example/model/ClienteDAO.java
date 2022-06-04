package com.example.model;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteDAO {
	
	@Autowired
	DataSource dataSource;
	
	JdbcTemplate jdbc;
	
	@PostConstruct
	private void initialize() {
		jdbc = new JdbcTemplate(dataSource);
	}
	
	public void inserirCliente(Cliente conta) {
		String sql = "INSERT INTO cliente(nome, cpf)" +
					"VALUES (?,?)";
		Object[] obj = new Object[2];
		//primeiro ?
		obj[0] = conta.getNome();
		//segundo ?
		obj[1] = conta.getCpf();
		jdbc.update(sql, obj);
	}

}
