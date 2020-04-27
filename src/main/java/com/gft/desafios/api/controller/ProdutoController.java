package com.gft.desafios.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.desafios.domain.model.Produto;
import com.gft.desafios.domain.repository.ProdutoRepository;

@RestController
@RequestMapping("/lista")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public List<Produto> buscar(){
		return produtoRepository.findAll();
	}
}
