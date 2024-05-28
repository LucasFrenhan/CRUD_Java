package com.trabalho.lucas.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {   

    @Autowired
    ProdutoDAO pDao;

    public void inserirProduto(Produto p) {
		pDao.inserirProduto(p);
	}

    public List<Map<String, Object>> listarProdutos() {
		return pDao.listarProdutos();
	}

    public List<Map<String, Object>> obterProduto(int id){
      return pDao.obterProduto(id);
    }

    public void atualizarProduto(int id, Produto produto){
      pDao.atualizarProduto(id, produto);
    }

    public void apagarProduto(int id){
      pDao.apagarProduto(id);
    }

}
