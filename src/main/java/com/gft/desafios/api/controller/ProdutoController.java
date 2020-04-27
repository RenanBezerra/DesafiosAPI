package com.gft.desafios.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.desafios.domain.model.Produto;
import com.gft.desafios.domain.repository.ProdutoRepository;

@RestController
@RequestMapping("/listaProdutos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public List<Produto> buscar(){
		return produtoRepository.findAll();

	}
	
	@GetMapping("/{ProdutoId}")
	public Optional<Produto> buscaPorId(@PathVariable Long ProdutoId){
		return produtoRepository.findById(ProdutoId);
	}
	
	@PostMapping
	public Produto adicionar(@RequestBody Produto produto) {
		Produto salvar = new Produto();
		salvar= produtoRepository.save(produto);
		
		return salvar;
	}
	
	@PutMapping("/{ProdutoId}")
	public Produto atualiza(@PathVariable Long ProdutoId, @RequestBody Produto produto){
		Produto atualizar = new Produto();
		atualizar= produtoRepository.save(produto);
		
		return atualizar;
	}
	
	@DeleteMapping("/{ProdutoId}")
	public void excluir(@PathVariable Long ProdutoId) {

		produtoRepository.deleteById(ProdutoId);
	
	}
}

