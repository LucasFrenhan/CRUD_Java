package com.trabalho.lucas.model;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class ProdutoDAO {
   
	@Autowired
	DataSource dataSource;
	
	JdbcTemplate jdbc;
	
	@PostConstruct
	private void initialize() {
		jdbc = new JdbcTemplate(dataSource);
	}
	
	public void inserirProduto(Produto produto) {
		String sql = "INSERT INTO produto(nome, descricao, preco)" +
	                 " VALUES (?,?,?)";
		Object[] obj = new Object[3];
		obj[0] = produto.getNome();
		obj[1] = produto.getDescricao();
        obj[2] = produto.getPreco();
		jdbc.update(sql, obj);
	}

    public List<Map<String, Object>> listarProdutos() {
    	String sql = "SELECT * FROM produto ORDER BY id";
    	return jdbc.queryForList(sql);
    }

    public List<Map<String, Object>> obterProduto(int id){
        String sql = "SELECT * FROM produto WHERE id = ?";
		Object[] obj = new Object[1];
		obj[0] = id;
        return jdbc.queryForList(sql, obj);
    }

	public void atualizarProduto(int id, Produto produto){
		String sql = "UPDATE produto SET nome = ?," + 
					 " descricao = ?," + 
					 " preco = ? WHERE id = ?";
		Object[] obj = new Object[4];
		obj[0] = produto.getNome();
		obj[1] = produto.getDescricao();
		obj[2] = produto.getPreco();
		obj[3] = id;
		jdbc.update(sql, obj);
	}

	public void apagarProduto(int id){
		String sql = "DELETE FROM produto WHERE id = ?";
		Object[] obj = new Object[1];
		obj[0] = id;
		jdbc.update(sql, obj);
	}

}
