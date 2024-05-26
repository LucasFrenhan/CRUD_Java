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
        model.addAttribute("produto",new Produto());
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

}
