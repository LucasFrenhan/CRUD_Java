package com.trabalho.lucas.controller;

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

import com.trabalho.lucas.model.Produto;
import com.trabalho.lucas.model.ProdutoService;

@Controller
@ComponentScan("com.trabalho.lucas.model")
public class IndexController {
    
    @Autowired
	private ApplicationContext context;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/cadastrar")
    public String cadastrar(Model model){
        model.addAttribute("produto", new Produto());
        return "cadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(Model model, @ModelAttribute Produto produto){
        ProdutoService cs = context.getBean(ProdutoService.class);
		cs.inserirProduto(produto);
		return "sucesso";
    }

    @GetMapping("/listar")
    public String listar(Model model){
        ProdutoService cs = context.getBean(ProdutoService.class);
        List<Map<String,Object>> lista = cs.listarProdutos();
        model.addAttribute("lista", lista);
        return "listar";
    }

    @GetMapping("/atualizar/{id}")
    public String atualizar(@PathVariable("id") int id, Model model) {
        ProdutoService cs = context.getBean(ProdutoService.class);
        Map<String, Object> cli = cs.obterProduto(id).get(0);
        String nome = (String)cli.get("nome");
        String descricao = (String)cli.get("descricao");  
        double preco = (double)cli.get("preco");       
        model.addAttribute("produto", new Produto(nome, descricao, preco));
        model.addAttribute("nome", nome);
        model.addAttribute("descricao", descricao);
        model.addAttribute("preco", preco);
        return "atualizar";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable("id") int id, Model model, @ModelAttribute Produto produto) {
        ProdutoService cs = context.getBean(ProdutoService.class);
        cs.atualizarProduto(id, produto);
        return "redirect:/listar";
    }

    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable("id") int id) {
        ProdutoService cs = context.getBean(ProdutoService.class);
        cs.apagarProduto(id);
        return "redirect:/listar";
    }

}
