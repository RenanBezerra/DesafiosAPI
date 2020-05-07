package com.gft.desafios.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gft.desafios.domain.model.Produto;
import com.gft.desafios.domain.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Page<Produto> getProdutos(Pageable pageable){
		return produtoRepository.findAll(pageable);
	}
	
	public Page<Produto> findProdutoByIdGreaterThan(Long id,Pageable pageable){
		return produtoRepository.findProdutoByIdGreaterThan(id,pageable);
	}
	
	public Produto saveProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public void excuirProduto(Long produto) {
		produtoRepository.deleteById(produto);
	}
	
	public Optional<Produto> buscaOuFalha(Long produtoId){
		return produtoRepository.findById(produtoId);
	}

	
	
}
