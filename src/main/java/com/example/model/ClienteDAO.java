package com.example.model;

import java.util.List;
import java.util.Map;

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
	
	public Map<String, Object> getCliente(int id) {
		String sql = "SELECT * FROM cliente WHERE cliente.id = ?";
		Object[] obj = new Object[1];
		obj[0] = id;
		return jdbc.queryForMap(sql, obj);
	}
	
	public List<Map<String, Object>> buscarCliente(String nome){
		String sql = "SELECT * FROM cliente WHERE cliente.nome = ?";
		Object[] obj = new Object[1];
		obj[0] = nome;
		return jdbc.queryForList(sql, obj);
	}
	
	public List<Map<String, Object>> getClientes(){
		String sql = "SELECT * FROM cliente";
		List<Map<String, Object>> clientes = (List<Map<String, Object>>) jdbc.queryForList(sql);
		return clientes;
	}
	
	
	public void deleteCliente(int id) {
		String sql = "DELETE FROM cliente WHERE id = ?";
		Object[] obj = new Object[1];
		obj[0] = id;
		jdbc.update(sql, obj);
	}
	
	public void atualizarCliente(int id, Cliente conta) {
		String sql = "UPDATE cliente"
				+	"SET nome = ?, cpf = ?"
				+	"WHERE id = ?";
		Object[] obj = new Object[3];
		//primeiro
		obj[0] = conta.getNome();
		//segundo
		obj[1] = conta.getCpf();
		//terceiro
		obj[2] = id;
		jdbc.update(sql, obj);
		
		
	}
	

}
